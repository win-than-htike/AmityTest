package com.onething.amitytest.utils

val Double?.orZero: Double
  get() = this ?: 0.0

val Double.isZero: Boolean
  get() = this == 0.0