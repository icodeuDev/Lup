package com.icodeu.lupdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        buttonBack.setOnClickListener {
            startActivity(Intent(this@SecondActivity,MainActivity::class.java))
            finish()
        }
    }


}