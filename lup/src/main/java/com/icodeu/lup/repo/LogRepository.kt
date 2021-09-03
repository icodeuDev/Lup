package com.icodeu.lup.repo

interface LogRepository {
    fun write(stacktrace: String, deviceInfo: String)
}