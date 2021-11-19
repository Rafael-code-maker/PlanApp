package com.android.cryptomanager.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.android.cryptomanager.databinding.ExpenditureFragmentBinding
import com.android.cryptomanager.home.presentation.ExpenditureViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExpenditureFragment: Fragment() {
    private var _binding: ExpenditureFragmentBinding? = null
    private val binding get() = _binding!!
    private val expenditureViewModel by viewModel<ExpenditureViewModel>()
    private val arguments by navArgs<ExpenditureFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = ExpenditureFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        expenditureViewModel.expenditure.observe(viewLifecycleOwner) {
            binding.dateValue.text=it.date
            binding.description.text=it.description
            binding.expenditurePriceValue.text=it.price
            binding.expenditureName.text=it.name
        }
        expenditureViewModel.getExpenditures(arguments.expenditurePosition)
    }
}
