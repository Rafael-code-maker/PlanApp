package com.android.cryptomanager.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.cryptomanager.databinding.ExpendituresFragmentBinding
import com.android.cryptomanager.home.presentation.ExpendituresViewModel
import com.android.cryptomanager.home.view.adapters.ExpendituresAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExpendituresFragment : Fragment(), ExpendituresAdapter.ExpenditureListener {
    private var _binding: ExpendituresFragmentBinding? = null
    private val binding get() = _binding!!
    private val expenditureViewModel by viewModel<ExpendituresViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = ExpendituresFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        expenditureViewModel.getExpenditures()
        expenditureViewModel.expenditureList.observe(viewLifecycleOwner){
            binding.expendituresRecyclerView.adapter=ExpendituresAdapter(it!!, this)
        }
    }

    override fun expenditureSelectListener(position: Int) {
        findNavController().navigate(
            ExpendituresFragmentDirections.
                actionExpendituresFragment2ToExpenditureFragment2(position)
        )
    }
}
