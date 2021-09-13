package com.android.cryptomanager.home.view.fragments

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.android.cryptomanager.databinding.AddFragmentBinding
import com.android.cryptomanager.home.presentation.AddViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.math.RoundingMode
import java.text.DecimalFormat


class AddFragment : Fragment() {

    private var _binding: AddFragmentBinding? = null
    private val binding get() = _binding!!
    private val arguments by navArgs<AddFragmentArgs>()
    private val addViewModel: AddViewModel by viewModel {
        parametersOf(arguments.crypto)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = AddFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val decimalFormat = DecimalFormat("#,###.###")
        decimalFormat.roundingMode = RoundingMode.CEILING

        binding.criptoName.title = arguments.crypto.coinTitle

        binding.plusView.setOnClickListener {
            showDialogAddEntries()
        }

        binding.minusView.setOnClickListener {
            showDialogRemoveEntries()
        }

        addViewModel.currentValueLive.observe(viewLifecycleOwner) {
            binding.valueInvested.text = decimalFormat.format(it)
            addViewModel.calculaPrecoMedio()
            addViewModel.calculoParcial()
            addViewModel.calculoTotal()
        }

        addViewModel.currentQuantieLive.observe(viewLifecycleOwner) {
            binding.quantiesValue.text = decimalFormat.format(it)
        }

        addViewModel.coinPrice.observe(viewLifecycleOwner) {
            binding.actualCoinValue.text = decimalFormat.format(it.toDouble())
            addViewModel.calculaPrecoMedio()
            addViewModel.calculoParcial()
            addViewModel.calculoTotal()
        }

        addViewModel.mediumPrice.observe(viewLifecycleOwner) {
            binding.mediumPriceValue.text = decimalFormat.format(it.toDouble())
        }

        addViewModel.parcialPrice.observe(viewLifecycleOwner) {
            binding.parcialPrice.text = decimalFormat.format(it.toDouble())
        }

        addViewModel.totalPrice.observe(viewLifecycleOwner) {
            binding.totalPrice.text = decimalFormat.format(it.toDouble())
        }

    }

    private fun showDialogAddEntries() {

        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.setTitle("Adicionar")

        val value = EditText(context)
        val quantity = EditText(context)

        value.hint = "Valor da compra"
        quantity.hint = "Quantidade de moedas comprada"

        value.inputType = InputType.TYPE_CLASS_NUMBER
        quantity.inputType = InputType.TYPE_CLASS_NUMBER

        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL

        layout.addView(value)
        layout.addView(quantity)

        builder.setView(layout)

        builder.setPositiveButton("Adicionar") { _, _ ->
            var valor = value.text.toString()
            var quantidade = quantity.text.toString()

            when (valor) {
                "" -> {
                    valor = "0.0"
                }
            }
            when (quantidade) {
                "" -> {
                    quantidade = "0.0"
                }
            }

            addViewModel.insertNewValue(valor, quantidade)
            addViewModel.calculaPrecoMedio()
            addViewModel.calculoParcial()
            addViewModel.calculoTotal()
        }

        builder.setNegativeButton("Cancelar") { dialog, _ -> dialog.cancel() }

        builder.show()
    }

    private fun showDialogRemoveEntries() {

        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.setTitle("Remover")

        val value = EditText(context)
        val quantity = EditText(context)

        value.hint = "Valor a ser removido"
        quantity.hint = "Quantidade de moedas vendidas"

        value.inputType = InputType.TYPE_CLASS_NUMBER
        quantity.inputType = InputType.TYPE_CLASS_NUMBER

        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL

        layout.addView(value)
        layout.addView(quantity)

        builder.setView(layout)

        builder.setPositiveButton("Remover") { _, _ ->
            var valor = value.text.toString()
            var quantidade = quantity.text.toString()

            when (valor) {
                "" -> {
                    valor = "0.0"
                }
            }
            when (quantidade) {
                "" -> {
                    quantidade = "0.0"
                }
            }

            addViewModel.removeValue(valor, quantidade)
            addViewModel.calculaPrecoMedio()
            addViewModel.calculoParcial()
            addViewModel.calculoTotal()
        }

        builder.setNegativeButton("Cancelar") { dialog, _ -> dialog.cancel() }

        builder.show()
    }
}

