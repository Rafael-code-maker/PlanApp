package com.android.cryptomanager.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.cryptomanager.databinding.IncomeListFragmentBinding
import com.android.cryptomanager.home.presentation.IncomeListViewModel
import com.android.cryptomanager.home.view.adapters.IncomeListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class IncomeListFragment : Fragment(), IncomeListAdapter.IncomeListener {
    private var _binding: IncomeListFragmentBinding? = null
    private val binding get() = _binding!!
    private val incomeListViewModel by viewModel<IncomeListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = IncomeListFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        incomeListViewModel.getIncomes()
        incomeListViewModel.incomesList.observe(viewLifecycleOwner){
            binding.incomesRecyclerView.adapter=IncomeListAdapter(it!!, this)
        }
    }

    override fun incomeSelectListener(position: Int) {
        findNavController().navigate(
            IncomeListFragmentDirections.actionIncomeListFragmentToIncomeFragment(position)
        )
    }
}