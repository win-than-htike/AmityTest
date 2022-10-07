package com.onething.amitytest.utils

import android.view.View

fun View.enable() {
  this.isEnabled = true
}

fun View.visible() {
  this.visibility = View.VISIBLE
}

fun View.invisible() {
  this.visibility = View.INVISIBLE
}

fun View.gone() {
  this.visibility = View.GONE
}