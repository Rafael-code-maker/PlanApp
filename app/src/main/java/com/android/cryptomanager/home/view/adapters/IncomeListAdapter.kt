package com.android.cryptomanager.home.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.cryptomanager.R
import com.android.cryptomanager.home.data.models.Expenditure
import com.android.cryptomanager.home.data.models.Income
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView

class IncomeListAdapter(
    private var incomeList: List<Income>,
    private val incomesListener: IncomeListener
) : RecyclerView.Adapter<IncomeListAdapter.IncomesViewHolder>() {

    interface IncomeListener {
        fun incomeSelectListener(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.income_list_item, parent, false)
        return IncomesViewHolder(view)
    }

    override fun onBindViewHolder(holder:IncomesViewHolder, position: Int) {
        holder.bindListItem(incomeList[position], incomesListener, position)
    }

    override fun getItemCount(): Int {
        return incomeList.size
    }

    class IncomesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val incomeName =
            itemView.findViewById<MaterialTextView>(R.id.income_item_name)
        private val incomePrice =
            itemView.findViewById<MaterialTextView>(R.id.income_price_value)
        private val incomeCardView =
            itemView.findViewById<MaterialCardView>(R.id.income_item_card_view)

        fun bindListItem(
            incomes: Income, incomesListener: IncomeListener, position: Int
        ) {
            incomeName.text=incomes.name
            incomePrice.text=incomes.value
            incomeCardView.setOnClickListener {
                incomesListener.incomeSelectListener(position)
            }
        }
    }
}