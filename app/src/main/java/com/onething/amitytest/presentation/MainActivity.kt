package com.onething.amitytest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.onething.amitytest.R
import com.onething.amitytest.R.layout
import com.onething.amitytest.common.ViewState
import com.onething.amitytest.common.ViewState.Empty
import com.onething.amitytest.common.ViewState.IDLE
import com.onething.amitytest.common.ViewState.Loading
import com.onething.amitytest.common.ViewState.Success
import com.onething.amitytest.databinding.ActivityMainBinding
import com.onething.amitytest.presentation.adapter.TodoListAdapter
import com.onething.amitytest.utils.launchAndRepeatWithViewLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private lateinit var viewBinding: ActivityMainBinding

  private val viewModel by viewModels<MainViewModel>()

  private lateinit var mAdapter: TodoListAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewBinding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(viewBinding.root)

    setupUI()

    listenDataState()

  }

  private fun setupUI() {

    mAdapter = TodoListAdapter()
    viewBinding.rvTodo.layoutManager = LinearLayoutManager(this)
    viewBinding.rvTodo.adapter = mAdapter

    viewBinding.swipeRefresh.isRefreshing = true
    viewBinding.swipeRefresh.setOnRefreshListener {
      viewModel.refresh()
    }

  }

  private fun listenDataState() {

    launchAndRepeatWithViewLifecycle {
      viewModel.data.collectLatest { state ->
        viewBinding.viewEmpty.root.isVisible = state is Empty
        when (state) {
          Empty -> {
            viewBinding.swipeRefresh.isRefreshing = false
          }
          is ViewState.Error -> {
            viewBinding.swipeRefresh.isRefreshing = false
          }
          IDLE -> {}
          Loading -> {
            viewBinding.swipeRefresh.isRefreshing = true
          }
          is Success -> {
            viewBinding.swipeRefresh.isRefreshing = false
            mAdapter.update(state.data)
          }
        }
      }
    }

    launchAndRepeatWithViewLifecycle {
      viewModel.error.collectLatest { error ->
        viewBinding.swipeRefresh.isRefreshing = false
        if (error != null) {
          Snackbar.make(viewBinding.root, error.localizedMessage, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.action_retry) {
              viewModel.refresh()
            }
            .show()
        }
      }
    }

  }

}