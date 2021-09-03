package com.icodeu.lup.exception

fun interface ExceptionHandler{
    fun doOnExceptionListener(thread: Thread, throwable: Throwable)
}
