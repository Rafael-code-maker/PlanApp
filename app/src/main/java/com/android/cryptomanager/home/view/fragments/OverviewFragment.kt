package com.android.cryptomanager.home.view.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.cryptomanager.databinding.OverviewFragmentBinding
import com.android.cryptomanager.home.presentation.EntradasViewModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.math.RoundingMode
import java.text.DecimalFormat


class OverviewFragment : Fragment() {

    private var _binding: OverviewFragmentBinding? = null
    private val binding get() = _binding!!

    private val entradasViewModel by viewModel<EntradasViewModel>()

    var pieChart: PieChart? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = OverviewFragmentBinding.inflate(layoutInflater)
        pieChart = binding.pieChartView
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val decimalFormat = DecimalFormat("#,###.###")
        decimalFormat.roundingMode = RoundingMode.CEILING
        initPieChart()
        showPieChart(entradasViewModel.somaEntradas(),entradasViewModel.somaSaidas())
    }

    private fun initPieChart() {
        //using percentage as values instead of amount
        pieChart!!.setUsePercentValues(true)

        //remove the description label on the lower left corner, default true if not set
        pieChart!!.description.isEnabled = false
        pieChart!!.legend.isEnabled = false
        //enabling the user to rotate the chart, default true
        pieChart!!.isRotationEnabled = true
        //adding friction when rotating the pie chart
        pieChart!!.dragDecelerationFrictionCoef = 0.9f
        //setting the first entry start from right hand side, default starting from top
        pieChart!!.rotationAngle = 0f

        //highlight the entry when it is tapped, default true if not set
        pieChart!!.isHighlightPerTapEnabled = true
        //adding animation so the entries pop up from 0 degree
        pieChart!!.animateY(1400, Easing.EaseInOutQuad)
        //setting the color of the hole in the middle, default white
        pieChart!!.setHoleColor(Color.parseColor("#FFFFFF"))
    }

    private fun showPieChart(bitcoin: Double, ethereum: Double) {
        val pieEntries: ArrayList<PieEntry> = ArrayList()
        val label = ""

        binding.valueBtc.text = bitcoin.toString()
        binding.valueEth.text = ethereum.toString()
        binding.totalActualCotation.text = (bitcoin - ethereum).toString()
        binding.coinPrice.text = bitcoin.toString()

        val decimalFormat = DecimalFormat("#,###.###")
        decimalFormat.roundingMode = RoundingMode.CEILING

        //initializing data
        val typeAmountMap: MutableMap<String, Double> = HashMap()
        typeAmountMap["Bitcoin"] = bitcoin
        typeAmountMap["Ethereum"] = ethereum

        //initializing colors for the entries
        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.parseColor("#FF8C00"))
        colors.add(Color.parseColor("#ffbc40"))
        colors.add(Color.parseColor("#FFA500"))

        //input data and fit data into pie chart entry
        for (type in typeAmountMap.keys) {
            pieEntries.add(PieEntry(typeAmountMap[type]!!.toFloat()))
        }

        //collecting the entries with label name
        val pieDataSet = PieDataSet(pieEntries, label)
        //setting text size of the value
        pieDataSet.valueTextSize = 12f
        //providing color list for coloring different entries
        pieDataSet.colors = colors
        //
        pieDataSet.valueTextColor = Color.WHITE
        //grouping the data set from entry to chart
        val pieData = PieData(pieDataSet)
        //showing the value of the entries, default true if not set
        pieData.setDrawValues(false)
        pieData.setValueFormatter(PercentFormatter(pieChart))
        pieChart!!.data = pieData
        pieChart!!.invalidate()
    }
}
