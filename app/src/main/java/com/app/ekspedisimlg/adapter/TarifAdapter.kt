package com.app.ekspedisimlg.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.ekspedisimlg.R
import com.app.ekspedisimlg.model.ListTarifModel
import kotlinx.android.synthetic.main.tarif_list_item.view.*

class TarifAdapter(private val data: ArrayList<ListTarifModel.Result>): RecyclerView.Adapter<TarifAdapter.TarifViewHolder>() {
    class TarifViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        // define itemView from xml
        val txt_lokasi = itemView.item_lokasi
        val txt_tarif = itemView.item_tarif
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarifViewHolder {
        // create itemView layout inflater
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tarif_list_item,
            parent, false
        )
        // return itemView
        return TarifViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TarifViewHolder, position: Int) {
        // set current item of result response tarif data
        val currentItem = data[position]
        // set value to the holder view
        holder.txt_lokasi.text =  currentItem.tujuan_pengiriman
        holder.txt_tarif.text = "Rp ${currentItem.tarif}"
    }

    override fun getItemCount() = data.size
}