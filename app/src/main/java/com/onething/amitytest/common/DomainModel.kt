package com.onething.amitytest.common

interface DomainModel<T> {
    fun areItemsTheSame(item: T): Boolean
    fun areContentsTheSame(item: T): Boolean
}