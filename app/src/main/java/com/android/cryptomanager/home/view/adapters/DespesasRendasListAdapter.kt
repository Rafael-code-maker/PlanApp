package com.android.cryptomanager.home.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.cryptomanager.R
import com.android.cryptomanager.home.data.models.DespesasRendas

class DespesasRendasListAdapter(
    private val despesasRendas: List<DespesasRendas>,
    private val listener: OnSelectOnClickListener,
) :
    RecyclerView.Adapter<DespesasRendasListAdapter.DespesasRendasViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): DespesasRendasViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.despesas_rendas_item, parent, false)
        return DespesasRendasViewHolder(view)
    }

    override fun onBindViewHolder(holder: DespesasRendasViewHolder, position: Int) {
        holder.bind(despesasRendas[position])
        holder.itemView.setOnClickListener { listener.onSelect(position) }
    }

    override fun getItemCount(): Int {
        return despesasRendas.size
    }

    class DespesasRendasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.name)
        fun bind(despesasRendas: DespesasRendas) {
            name.text = despesasRendas.name
        }
    }

    interface OnSelectOnClickListener {
        fun onSelect(position: Int)
    }

}
