package com.android.cryptomanager.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.cryptomanager.R
import com.android.cryptomanager.databinding.InvestimentosFragmentBinding
import com.android.cryptomanager.home.data.models.CryptoCard
import com.android.cryptomanager.home.view.adapters.CryptoCardListAdapter

class InvestimentosFragment : Fragment() {

    private var _binding: InvestimentosFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = InvestimentosFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CryptoCardListAdapter(
            cryptoCards(),
            object : CryptoCardListAdapter.OnSelectOnClickListener {
                override fun onSelect(crypto: CryptoCard) {
//                val direction =
//                    InvestimentosFragmentDirections.actionPixValueFragmentToConfirmationPixFragment(
//                        crypto
//                    )
//                findNavController().navigate(direction)
                }
            })

        binding.recyclerviewCriptomoedasMenu.adapter = adapter

    }

    private fun cryptoCards(): List<CryptoCard> {
        return listOf(
            CryptoCard(
                R.drawable.logo_bitcoin,
                getString(R.string.bitcoin),
                "R$ 300.000,00",
                "1,02 %",
                getString(R.string.vinte_quatro_horas),
                "R$ 5.000,00",
                "R$ 5.500,00"
            ),
            CryptoCard(
                R.drawable.logo_ethereum,
                getString(R.string.ethereum),
                "R$ 20.000,00",
                "3,04 %",
                getString(R.string.vinte_quatro_horas),
                "R$ 2.000,00",
                "R$ 2.500,00"
            ),
            CryptoCard(
                R.drawable.logo_chiliz,
                getString(R.string.chiliz),
                "R$ 2,20",
                "5,03 %",
                getString(R.string.vinte_quatro_horas),
                "R$ 4.000,00",
                "R$ 4.500,00"
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
