package com.onething.amitytest.utils

val Long?.orZero: Long
  get() = this ?: 0L

val Long.isZero: Boolean
  get() = this == 0L