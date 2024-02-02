package com.example.apiconnect.api


sealed class Result<T>(
    val dta: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?) : Result<T>(data)
    class Error<T>(data: T? = null,message: String?) : Result<T>(data,message)
}