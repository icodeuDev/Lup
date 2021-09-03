package com.icodeu.lup.repo

interface LogDao {
    fun write(stacktrace: String, deviceInfo: String)
}