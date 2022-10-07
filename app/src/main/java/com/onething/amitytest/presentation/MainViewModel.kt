package com.onething.amitytest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.onething.amitytest.common.ViewState
import com.onething.amitytest.domain.model.Todo
import com.onething.amitytest.domain.usecase.GetTodoUseCase
import com.onething.amitytest.domain.usecase.SaveTodoListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  private val getTodoUseCase: GetTodoUseCase,
  private val saveTodoListUseCase: SaveTodoListUseCase
) : ViewModel() {

  private var _data = MutableStateFlow<ViewState<List<Todo>>>(ViewState.IDLE)
  val data : StateFlow<ViewState<List<Todo>>>
    get() = _data

  private var _error = MutableSharedFlow<Throwable?>()
  val error: SharedFlow<Throwable?>
    get() = _error

  init {
    getTodoList()
    fetchTodoList()
  }

  private fun getTodoList() {
    getTodoUseCase()
      .onStart {
        _data.value = ViewState.Loading
      }
      .onEach {
        if (it.isEmpty()) {
          _data.value = ViewState.Empty
        } else {
          _data.value = ViewState.Success(it)
        }
      }
      .catch {
        _data.value = ViewState.Error(it)
      }.launchIn(viewModelScope)
  }

  private fun fetchTodoList() {
    viewModelScope.launch {
      saveTodoListUseCase()
        .suspendEither({
          _data.value = ViewState.Error(it)
          _error.emit(it)
        }, {
          _error.emit(null)
        })
    }
  }

  fun refresh() {
    fetchTodoList()
  }

}