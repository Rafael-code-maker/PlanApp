package com.android.cryptomanager.home.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.cryptomanager.R
import com.android.cryptomanager.home.data.models.Expenditure
import com.google.android.material.textview.MaterialTextView

class ExpendituresAdapter (
    private var expenditureList: List<Expenditure>,
    private val expenditureListener: ExpenditureListener
) :RecyclerView.Adapter<ExpendituresAdapter.ExpendituresViewHolder>() {

    interface ExpenditureListener {
        fun expenditureSelectListener(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpendituresViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.expenditure_item, parent, false)
        return ExpendituresViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpendituresViewHolder, position: Int) {
        holder.bindListItem(expenditureList[position], expenditureListener, position)
    }

    override fun getItemCount(): Int {
        return expenditureList.size
    }

    class ExpendituresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val expenditureName =
            itemView.findViewById<MaterialTextView>(R.id.expenditure_item_name)
        private val expenditurePrice =
            itemView.findViewById<MaterialTextView>(R.id.expenditure_item_name)
        private val expenditureCardView =
            itemView.findViewById<MaterialTextView>(R.id.expenditure_item_card_view)

        fun bindListItem(
            expenditure: Expenditure, expenditureListener: ExpenditureListener, position: Int
        ) {
            expenditureName.text=expenditure.name
            expenditurePrice.text=expenditure.price
            expenditureCardView.setOnClickListener {
                expenditureListener.expenditureSelectListener(position)
            }
        }
    }
}
