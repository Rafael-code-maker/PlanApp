package com.android.cryptomanager.home.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.cryptomanager.R
import com.android.cryptomanager.home.data.models.CryptoCard

class CryptoCardListAdapter(
    private val cryptoCard: List<CryptoCard>,
    private val listener: OnSelectOnClickListener,
) :
    RecyclerView.Adapter<CryptoCardListAdapter.CryptoCardViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CryptoCardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.criptomoeda_card_view, parent, false)
        return CryptoCardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CryptoCardViewHolder, position: Int) {
        holder.bind(cryptoCard[position])
        holder.itemView.setOnClickListener { listener.onSelect(position) }
    }

    override fun getItemCount(): Int {
        return cryptoCard.size
    }

    class CryptoCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardCoinImage: ImageView = itemView.findViewById(R.id.image_cripto)
        private val cardCoinTitle: TextView = itemView.findViewById(R.id.criptoName)
        private val cardCoinValue: TextView = itemView.findViewById(R.id.coin_price)
        private val cardInvestedValue: TextView = itemView.findViewById(R.id.invested_value)
        fun bind(cryptoCard: CryptoCard) {
            cardCoinImage.setImageResource(cryptoCard.image)
            cardCoinTitle.text = cryptoCard.coinTitle
            cardCoinValue.text = cryptoCard.coinValue
            cardInvestedValue.text = cryptoCard.investedValue
        }
    }

    interface OnSelectOnClickListener {
        fun onSelect(position: Int)
    }

}
