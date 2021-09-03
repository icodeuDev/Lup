package com.icodeu.lup.repo

import android.content.Context

class LogRepositoryImpl(val context: Context): LogRepository  {
    var logDaoImpl: LogDaoImpl = LogDaoImpl(context)

    override fun write(stacktrace: String, deviceInfo: String) {
        logDaoImpl.write(stacktrace,deviceInfo)
    }

}