//package com.android.cryptomanager.home.view.fragments
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.EditText
//import androidx.fragment.app.Fragment
//import androidx.navigation.fragment.navArgs
//import com.android.cryptomanager.R
//import com.android.cryptomanager.databinding.AddFragmentBinding
//import com.google.android.material.dialog.MaterialAlertDialogBuilder
//import java.math.RoundingMode
//import java.text.DecimalFormat
//
//class AddFragment : Fragment() {
//
//    private var _binding: AddFragmentBinding? = null
//    private val binding get() = _binding!!
//    private val arguments by navArgs<AddFragmentArgs>()
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View {
//        _binding = AddFragmentBinding.inflate(layoutInflater)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val decimalFormat = DecimalFormat("#,###.###")
//        decimalFormat.roundingMode = RoundingMode.CEILING
//
//        val quantieFormat = DecimalFormat("#,###.######")
//        decimalFormat.roundingMode = RoundingMode.CEILING
//
//        binding.criptoName.setNavigationOnClickListener {
//            activity?.onBackPressed()
//        }
//
//        binding.criptoName.title = arguments.crypto.coinTitle
//
//        binding.plusView.setOnClickListener {
//            showDialogAddEntries()
//        }
//
//        binding.minusView.setOnClickListener {
//            showDialogRemoveEntries()
//        }
//
//    }
//
//    private fun showDialogAddEntries() {
//
//        val builder = MaterialAlertDialogBuilder(requireContext())
//
//        val dialogView: View = LayoutInflater.from(context)
//            .inflate(R.layout.alert_dialog, null, false)
//
//        builder.setView(dialogView)
//
//        builder.setPositiveButton("Adicionar") { _, _ ->
//            var valor = dialogView.findViewById<EditText>(R.id.entrie_value)?.text.toString()
//            var quantidade = dialogView.findViewById<EditText>(R.id.quantie_value)?.text.toString()
//
//            when (valor) {
//                "" -> {
//                    valor = "0.0"
//                }
//            }
//
//            when (quantidade) {
//                "" -> {
//                    quantidade = "0.0"
//                }
//            }
//
//        }
//
//        builder.setNegativeButton("Cancelar") { dialog, _ -> dialog.cancel() }
//
//        builder.show()
//    }
//
//    private fun showDialogRemoveEntries() {
//
//        val builder = MaterialAlertDialogBuilder(requireContext())
//
//        val dialogView: View = LayoutInflater.from(context)
//            .inflate(R.layout.alert_dialog_remove, null, false)
//
//        builder.setView(dialogView)
//
//        builder.setPositiveButton("Remover") { _, _ ->
//            var valor = dialogView.findViewById<EditText>(R.id.entrie_value_remove)?.text.toString()
//            var quantidade =
//                dialogView.findViewById<EditText>(R.id.quantie_value_remove)?.text.toString()
//
//            when (valor) {
//                "" -> {
//                    valor = "0.0"
//                }
//            }
//            when (quantidade) {
//                "" -> {
//                    quantidade = "0.0"
//                }
//            }
//        }
//
//        builder.setNegativeButton("Cancelar") { dialog, _ -> dialog.cancel() }
//
//        builder.show()
//    }
//}
//
