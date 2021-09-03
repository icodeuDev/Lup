package com.icodeu.lup

import android.content.Context
import android.util.Log
import com.icodeu.lup.exception.ExceptionHandler
import com.icodeu.lup.repo.LogRepositoryImpl
import com.icodeu.lup.utils.getInfosAboutDevice

class Lup private constructor(
    private val context: Context,
    private val dialog: Boolean,
    private val message: String,
    private val reportUrl: String?,
    private val destination: Class<*>?,
    private val exceptionHandler: ExceptionHandler?

) {

    private lateinit var exceptionHandlerInstance: Thread.UncaughtExceptionHandler
    private lateinit var logRepositoryImpl: LogRepositoryImpl
    private lateinit var lubExceptionFactory: LubFactory
    private val tempException: Thread.UncaughtExceptionHandler? =
        Thread.getDefaultUncaughtExceptionHandler()


    fun start() {
        logRepositoryImpl = LogRepositoryImpl(context)
        lubExceptionFactory = LubFactory.getInstance()
        defineExceptionInstance()
        exceptionHandler()
    }


    private fun defineExceptionInstance() {
        exceptionHandlerInstance =
            Thread.UncaughtExceptionHandler { thread, throwable ->
                val deviceInfo = context.getInfosAboutDevice()
                try {
                    logRepositoryImpl.logDaoImpl.write(
                        deviceInfo = deviceInfo,
                        stacktrace = throwable.stackTraceToString()
                    )

                    val actions = mutableListOf<ExceptionHandler>()
                    if (actions.size > 0) {
                        actions.clear()
                    }
                    exceptionHandler?.doOnExceptionListener(thread, throwable)
                    Log.e("Lup", throwable.stackTraceToString())

                    if (dialog) {
                        actions.add(
                            lubExceptionFactory.getShowDialogInstance(
                                context = context,
                                reportUrl = reportUrl,
                                message = message
                            )
                        )
                    }
                    if (destination != null) {
                        actions.add(lubExceptionFactory.getRestartAppInstance(context, destination))
                    }

                    actions.forEach {
                        it.doOnExceptionListener(thread, throwable)
                    }
                    tempException?.uncaughtException(thread, throwable)
                } catch (e: Exception) {
                    //Don't throw exception here! it will cause recursive loop forever
                    Log.e("Lup", e.stackTraceToString())
                    logRepositoryImpl.logDaoImpl.write(
                        deviceInfo = deviceInfo,
                        stacktrace = throwable.stackTraceToString()
                    )

                    System.exit(1)
                }
            }
    }


    private fun exceptionHandler() {
//        if (Thread.getDefaultUncaughtExceptionHandler() !is exceptionHandlerInstance) {
        Thread.setDefaultUncaughtExceptionHandler(exceptionHandlerInstance)
//        }
        println("exceptionHandler: exception is set to (${Thread.getDefaultUncaughtExceptionHandler()})")
    }


    class Builder(private val context: Context) {
        private var destination: Class<*>? = null
        private var defaultDialog: Boolean = true
        private var message: String = ""
        private var reportUrl: String? = null
        private var exceptionHandler: ExceptionHandler? = null

        fun restartAfterCrash(destination: Class<*>) = apply {
            this.destination = destination
        }

        fun disableDialog() = apply { this.defaultDialog = false }

        fun showDefaultDialog(message: String, reportUrl: String) =
            apply {
                this.defaultDialog = true
                this.message = message
                this.reportUrl = reportUrl
            }

        fun addOnException(action: ExceptionHandler) =
            apply {
                this.exceptionHandler = action
            }

        fun build() = Lup(
            context = context,
            dialog = defaultDialog,
            message = message,
            reportUrl = reportUrl,
            destination = destination,
            exceptionHandler = exceptionHandler
        )
    }


}