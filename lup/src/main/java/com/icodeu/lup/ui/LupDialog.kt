package com.icodeu.lup.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.icodeu.lup.databinding.LupDialogLayoutBinding
import com.icodeu.lup.repo.ErrorData
import com.icodeu.lup.utils.copyToClipboard


class LupDialog(private val errorData: ErrorData, val reportUri: Uri, val dialogInterface: LupDialogInterface) : Fragment() {
    private lateinit var binding: LupDialogLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LupDialogLayoutBinding.inflate(inflater)
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
            buttonCopy.root.setOnClickListener {
                requireContext().copyToClipboard(errorData.stacktrace)
            }
        }

    }
}