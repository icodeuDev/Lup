package com.icodeu.lupdemo

import android.app.Application
import com.icodeu.lup.Lup
import com.icodeu.lup.exception.ExceptionHandler
import org.xml.sax.ErrorHandler


class LupDemoApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val lup = Lup.Builder(this)
            .showDefaultDialog("Oopss!","https://forms.gle/2P4NXJZWMAfKhg538")
//            .disableDialog()
//            .addOnException { thread, throwable ->
//                doSomething()
//                throwable.printStackTrace()
//            }
            .addOnException(object : ExceptionHandler{
                override fun doOnExceptionListener(thread: Thread, throwable: Throwable) {
                    doSomething()
                }

            })
//            .restartAfterCrash(SecondActivity::class.java)

            .build()

        lup.start()
    }

    private fun doSomething() {
        println("OOPSS")
    }

}