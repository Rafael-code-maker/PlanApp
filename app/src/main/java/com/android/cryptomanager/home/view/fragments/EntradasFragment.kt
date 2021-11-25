package com.android.cryptomanager.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.cryptomanager.databinding.EntradasFragmentBinding
import com.android.cryptomanager.home.data.models.Income
import com.android.cryptomanager.home.presentation.EntradasViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EntradasFragment : Fragment() {
    private var _binding: EntradasFragmentBinding? = null
    private val binding get() = _binding!!

    private val entradasViewModel by viewModel<EntradasViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = EntradasFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveButtonEntrie.setOnClickListener {
            entradasViewModel.saveEntrie(
                Income(
                    binding.exitName.text.toString(),
                    binding.exitValue.text.toString(),
                    binding.exitDate.text.toString(),
                    binding.exitDescription.text.toString()
                )
            )
            findNavController().navigateUp()
        }
    }

}