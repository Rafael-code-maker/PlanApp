package com.android.cryptomanager.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.android.cryptomanager.databinding.ExpendituresFragmentBinding
import com.android.cryptomanager.databinding.IncomeFragmentBinding
import com.android.cryptomanager.home.presentation.ExpendituresViewModel
import com.android.cryptomanager.home.presentation.IncomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class IncomeFragment: Fragment() {
    private var _binding: IncomeFragmentBinding? = null
    private val binding get() = _binding!!
    private val incomeViewModel by viewModel<IncomeViewModel>()
    private val arguments by navArgs<IncomeFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = IncomeFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        incomeViewModel.income.observe(viewLifecycleOwner) {
            binding.dateValue.text=it.date
            binding.descriptionValue.text=it.description
            binding.incomeDetailsPriceValue.text=it.value
            binding.incomeName.text=it.name
        }
        incomeViewModel.getIncome(arguments.position)
    }
}