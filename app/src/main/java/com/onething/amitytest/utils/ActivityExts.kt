package com.onething.amitytest.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

inline fun AppCompatActivity.launchAndRepeatWithViewLifecycle(
  minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
  crossinline block: suspend CoroutineScope.() -> Unit
) {
  lifecycleScope.launch {
    lifecycle.repeatOnLifecycle(minActiveState) {
      block()
    }
  }
}

inline fun <reified T : Any> Activity.launchActivity(
  requestCode: Int = -1,
  options: Bundle? = null,
  noinline init: Intent.() -> Unit = {}
) {
  val intent = newIntent<T>(this)
  intent.init()
  startActivityForResult(intent, requestCode, options)
}

inline fun <reified T : Any> newIntent(context: Context): Intent =
  Intent(context, T::class.java)