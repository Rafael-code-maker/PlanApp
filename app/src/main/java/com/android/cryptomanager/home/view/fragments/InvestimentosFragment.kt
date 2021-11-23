package com.android.cryptomanager.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.cryptomanager.databinding.InvestimentosFragmentBinding
import com.android.cryptomanager.home.data.models.DespesasRendas
import com.android.cryptomanager.home.view.adapters.DespesasRendasListAdapter
import java.math.RoundingMode
import java.text.DecimalFormat

class InvestimentosFragment : Fragment() {

    private var _binding: InvestimentosFragmentBinding? = null
    private val binding get() = _binding!!

    val adapter = object : DespesasRendasListAdapter.OnSelectOnClickListener {
        override fun onSelect(position: Int) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = InvestimentosFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val decimalFormat = DecimalFormat("#,###.###")
        decimalFormat.roundingMode = RoundingMode.CEILING

        binding.lista.adapter = DespesasRendasListAdapter(listaDespesas(),adapter)
    }

    private fun listaDespesas(): List<DespesasRendas> {

        return listOf(
            DespesasRendas(
                1,
                "Despesa fixa",
            ),
            DespesasRendas(
                2,
                "Despesa avulsa",
            ),
            DespesasRendas(
                3,
                "Mensalidade",
            ),
            DespesasRendas(
                4,
                "Renda fixa",
            ),
            DespesasRendas(
                5,
                "Renda variável",
            ),
            DespesasRendas(
                6,
                "Renda extra",
            )
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
