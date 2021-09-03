package com.icodeu.lup.repo

import android.annotation.SuppressLint
import android.content.Context
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class LogDaoImpl(val context: Context): LogDao {

    private var file: File
    private var file_path: File
init {
    file =  context.getExternalFilesDir("/lup")!!
    if (!file.exists()){
        file.mkdir()
    }
    file_path = File(file.toString() +"/" + java.io.File.separator +"LupLog.txt")
    if (!file_path.exists()){
        file_path.createNewFile()
    }

    file_path = File(file_path.toString())
    file_path.createNewFile()
}
    @SuppressLint("SimpleDateFormat")
    override fun write(stacktrace: String, deviceInfo: String) {
        val dateTime = SimpleDateFormat("dd-MM-yyy HH:mm:ss z").format(Calendar.getInstance().time)
        val logText = """
{
Date : $dateTime,
Stack Trace :
${stacktrace},
Device Information :
$deviceInfo
},
        """.trimIndent()
        file_path.appendText(logText)
    }

}