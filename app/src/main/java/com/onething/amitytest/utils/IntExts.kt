package com.onething.amitytest.utils

val Int?.orZero: Int
  get() = this ?: 0

val Int.isZero: Boolean
  get() = this == 0