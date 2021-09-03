package com.icodeu.lup

import android.content.Context
import com.icodeu.lup.exception.RestartApplication
import com.icodeu.lup.exception.ShowDefaultDialog

class LubFactory private constructor() {

    private lateinit var restartApplication: RestartApplication
    private lateinit var showDefaultDialog: ShowDefaultDialog

    companion object {
        private lateinit var lubExceptionFactory: LubFactory
        fun getInstance(): LubFactory {
            if (!this::lubExceptionFactory.isInitialized) {
                lubExceptionFactory = LubFactory()
            }
            return lubExceptionFactory
        }
    }

    internal fun getRestartAppInstance(
        context: Context,
        destination: Class<*>
    ): RestartApplication {
        if (!this::restartApplication.isInitialized) {
            restartApplication = RestartApplication(context, destination)
        }
        return restartApplication
    }


    internal fun getShowDialogInstance(
        context: Context,
        message: String,
        reportUrl: String?
    ): ShowDefaultDialog {
        if (!this::showDefaultDialog.isInitialized) {
            showDefaultDialog = ShowDefaultDialog(context, message, reportUrl )
        }
        return showDefaultDialog
    }


}