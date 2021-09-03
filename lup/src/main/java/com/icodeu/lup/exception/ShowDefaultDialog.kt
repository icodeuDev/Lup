package com.icodeu.lup.exception

import android.content.Context
import com.icodeu.lup.repo.ErrorData
import com.icodeu.lup.ui.LupMainActivity
import com.icodeu.lup.utils.intentTo

class ShowDefaultDialog(val context: Context, val message: String, val reportUrl: String?) :
    ExceptionHandler {

    override fun doOnExceptionListener(thread: Thread, throwable: Throwable) {
        context.intentTo(LupMainActivity::class.java, ErrorData(message,throwable.stackTraceToString()), reportUrl)
    }
}