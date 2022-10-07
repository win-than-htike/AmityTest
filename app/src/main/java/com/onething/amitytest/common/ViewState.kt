package com.onething.amitytest.common

sealed class ViewState<out T> {
  object Loading : ViewState<Nothing>()
  object Empty : ViewState<Nothing>()
  data class Success<out R>(val data: R) : ViewState<R>()
  data class Error(val error: Throwable) : ViewState<Nothing>()
  object IDLE : ViewState<Nothing>()
}
