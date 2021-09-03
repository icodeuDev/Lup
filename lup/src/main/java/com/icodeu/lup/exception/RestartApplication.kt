package com.icodeu.lup.exception

import android.content.Context
import com.icodeu.lup.utils.restartApp


class RestartApplication(val context: Context, val destination: Class<*> ): ExceptionHandler {
    companion object{
        internal const val DELAY = 1
    }

    override fun doOnExceptionListener(thread: Thread, throwable: Throwable) {
        context.restartApp(DELAY, destination)
    }
}