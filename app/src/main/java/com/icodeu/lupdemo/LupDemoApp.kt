package com.icodeu.lupdemo

import android.app.Application
import com.icodeu.lup.Lup


class LupDemoApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val lup = Lup.Builder(this)
            .showDefaultDialog("message","https://lup.id/report")
//            .disableDialog()
            .addOnException { thread, throwable ->
                doSomething()
                throwable.printStackTrace()
            }
            .restartAfterCrash(SecondActivity::class.java)
            .build()

        lup.start()

    }

    private fun doSomething() {
        println("OOPSS")
    }

}