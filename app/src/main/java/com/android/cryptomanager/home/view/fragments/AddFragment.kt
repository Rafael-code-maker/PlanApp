package com.android.cryptomanager.home.view.fragments

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.android.cryptomanager.databinding.AddFragmentBinding
import com.android.cryptomanager.home.presentation.AddViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddFragment : Fragment() {

    private var _binding: AddFragmentBinding? = null
    private val binding get() = _binding!!

    private val addViewModel by viewModel<AddViewModel>()


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

        binding.plusView.setOnClickListener {
            showDialogAddEntries()
        }

        binding.minusView.setOnClickListener {
            showDialogRemoveEntries()
        }

        addViewModel.currentValueLive.observe(viewLifecycleOwner) {
            binding.valueInvested.text = it.toString()
        }

        addViewModel.currentQuantieLive.observe(viewLifecycleOwner) {
            binding.quantiesValue.text = it.toString()
        }

        addViewModel.bitcoinPrice.observe(viewLifecycleOwner) {
            binding.actualCoinValue.text = it
        }
    }

    fun showDialogAddEntries() {

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
        }

        builder.setNegativeButton("Cancelar") { dialog, _ -> dialog.cancel() }

        builder.show()
    }

    fun showDialogRemoveEntries() {

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
        }

        builder.setNegativeButton("Cancelar") { dialog, _ -> dialog.cancel() }

        builder.show()
    }
}

