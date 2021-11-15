package com.android.cryptomanager.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.cryptomanager.R
import com.android.cryptomanager.databinding.InvestimentosFragmentBinding
import com.android.cryptomanager.home.data.models.CryptoCard
import com.google.firebase.auth.FirebaseAuth
import java.math.RoundingMode
import java.text.DecimalFormat

class InvestimentosFragment : Fragment() {

    private var _binding: InvestimentosFragmentBinding? = null
    private val binding get() = _binding!!

    var bitcoinPrice: Double = 0.0
    var ethereumPrice: Double = 0.0
    var chilizPrice: Double = 0.0

    var bitcoinInvested: Double = 0.0
    var ethereumInvested: Double = 0.0
    var chilizInvested: Double = 0.0

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
    }

    private fun cryptoCards(): List<CryptoCard> {

        val decimalFormat = DecimalFormat("#,###.###")
        decimalFormat.roundingMode = RoundingMode.CEILING

        return listOf(
            CryptoCard(
                R.drawable.controle_de_gastos_ic,
                getString(R.string.bitcoin),
                "R$ " + decimalFormat.format(bitcoinPrice),
                "Investido : R$ " + decimalFormat.format(bitcoinInvested),
            ),
            CryptoCard(
                R.drawable.logo_ethereum,
                getString(R.string.ethereum),
                "R$ " + decimalFormat.format(ethereumPrice),
                "Investido : R$ " + decimalFormat.format(ethereumInvested),
            ),
            CryptoCard(
                R.drawable.logo_chiliz,
                getString(R.string.chiliz),
                "R$ " + decimalFormat.format(chilizPrice),
                "Investido : R$ " + decimalFormat.format(chilizInvested),
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
