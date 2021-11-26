package com.android.cryptomanager.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.cryptomanager.databinding.EntradasFragmentBinding
import com.android.cryptomanager.databinding.SaidasFragmentBinding
import com.android.cryptomanager.home.data.models.Expenditure
import com.android.cryptomanager.home.presentation.SaidasViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SaidasFragment : Fragment() {
    private var _binding: SaidasFragmentBinding? = null
    private val binding get() = _binding!!

    private val saidasViewModel by viewModel<SaidasViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SaidasFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveButtonExit.setOnClickListener {
            saidasViewModel.saveExpenditure(
                Expenditure(
                    binding.exitName.text.toString(),
                    binding.exitValue.text.toString(),
                    binding.exitDate.text.toString(),
                    binding.exitDescription.text.toString()
                )
            )
        }
        saidasViewModel.loading.observe(viewLifecycleOwner){
            binding.loading.isVisible=it
        }
        saidasViewModel.insertionFinished.observe(viewLifecycleOwner) {
            findNavController().navigateUp()
        }
    }

}