package com.icodeu.lup.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.icodeu.lup.repo.ErrorData
import com.rohman.lup.databinding.DialogLayoutBinding

class LupDialog(private val errorData: ErrorData, val reportUri: Uri, val dialogInterface: DialogInterface) : Fragment() {
    private lateinit var binding: DialogLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogLayoutBinding.inflate(inflater)
        binding.lifecycleOwner = requireActivity()
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            errorTitle.text = errorData.title
            errorText.text = errorData.stacktrace
            buttonReport.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, reportUri))
                dialogInterface.onReportClicked()
            }
        }

    }
}