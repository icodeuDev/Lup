package com.icodeu.lup.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.icodeu.lup.R
import com.icodeu.lup.databinding.LupActivityMainBinding
import com.icodeu.lup.repo.ErrorData

class LupMainActivity() : AppCompatActivity(), DialogInterface {
    companion object {
        const val INTENT_ERROR_KEY = "data"
        const val INTENT_URI_KEY = "uri"
    }

    private lateinit var binding: LupActivityMainBinding
    private var errorData: ErrorData? = null
    lateinit var myView: LupMainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LupActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)
        errorData = intent.extras?.getParcelable<ErrorData>(INTENT_ERROR_KEY)
        val uri = intent.extras?.getString(INTENT_URI_KEY, "") ?: ""
        val fragment = LupDialog(errorData ?: ErrorData(), uri.toUri(),this)

        binding.apply {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()
        }
    }

    override fun onReportClicked() {
        finish()
    }
}