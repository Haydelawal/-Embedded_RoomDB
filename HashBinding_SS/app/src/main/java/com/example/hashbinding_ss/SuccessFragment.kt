package com.example.hashbinding_ss

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.hashbinding_ss.databinding.FragmentHomeBinding
import com.example.hashbinding_ss.databinding.FragmentSuccessBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SuccessFragment : Fragment() {

    private val args: SuccessFragmentArgs by navArgs()

    private var _binding: FragmentSuccessBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSuccessBinding.inflate(inflater, container, false)

        binding.hashTextView.text = args.hash

        binding.copyButton.setOnClickListener {
            onCopyClicked()
        }



        return binding.root


    }

    private fun onCopyClicked() {
        lifecycleScope.launch {
            copyToClipBoard(args.hash)
            applyAnimations()
        }
    }

    private fun copyToClipBoard(hash: String) {

        val clipBoardManager = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        val clipData = ClipData.newPlainText("Encrypted Text", hash)

        clipBoardManager.setPrimaryClip(clipData)

    }

    private suspend fun applyAnimations() {
        binding.include.messageBckgnd.animate().translationY(80f).duration = 200
        binding.include.messageTextView.animate().translationY(80f).duration = 200

        delay(2000L)

        binding.include.messageBckgnd.animate().translationY(-80f).duration = 500
        binding.include.messageTextView.animate().translationY(-80f).duration = 500
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}