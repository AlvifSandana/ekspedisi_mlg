package com.app.ekspedisimlg.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.ekspedisimlg.R
import com.app.ekspedisimlg.model.PesananModel
import kotlinx.android.synthetic.main.item_list_pesanan.view.*

class PesananAdapter(private val data: ArrayList<PesananModel.Result>): RecyclerView.Adapter<PesananAdapter.PesananViewHolder>() {
    class PesananViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        // define itemView from xml
        val jenis_muatan = itemView.jenis_muatan
        val berat_muatan = itemView.berat_muatan
        val tanggal_muat = itemView.tanggal_muat
        val lokasi_tujuan = itemView.lokasi_tujuan
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PesananViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_pesanan, parent, false)
        return PesananViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PesananViewHolder, position: Int) {
        // set current item of result response tarif data
        val currentItem = data[position]
        // set value to the holder view
        holder.jenis_muatan.text = currentItem.jenis_muatan
        holder.berat_muatan.text = currentItem.berat_muatan
        holder.tanggal_muat.text = currentItem.tanggal_muat
        holder.lokasi_tujuan.text = currentItem.lokasi_tujuan
    }

    override fun getItemCount() = data.size
}