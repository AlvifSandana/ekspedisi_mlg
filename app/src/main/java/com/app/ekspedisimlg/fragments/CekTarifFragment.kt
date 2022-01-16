package com.app.ekspedisimlg.fragments

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.app.ekspedisimlg.R
import com.app.ekspedisimlg.activities.LoginActivity
import com.app.ekspedisimlg.databinding.FragmentCekTarifBinding
import com.google.android.material.textfield.TextInputLayout
import java.lang.Exception

class CekTarifFragment : Fragment() {
    // define view binding
    private var _binding: FragmentCekTarifBinding? = null
    private val binding get() = _binding!!

    // define view item
    lateinit var txt_lokasi_asal: TextInputLayout
    lateinit var txt_lokasi_tujuan: TextInputLayout
    lateinit var txt_jenis_muatan: TextInputLayout
    lateinit var txt_berat_muatan: TextInputLayout
    lateinit var btn_cek: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCekTarifBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // define view item
        txt_lokasi_asal = binding.lokasiAsal
        txt_lokasi_tujuan = binding.lokasiTujuan
        txt_jenis_muatan = binding.jenisMuatan
        txt_berat_muatan = binding.beratMuatan
        btn_cek = binding.btnCek
        // set onclick listener untuk button
        btn_cek.setOnClickListener {
            // ambil nilai berat muatan dan hitung tarif
            val tarif = cekTarif(txt_berat_muatan.editText?.text.toString())
            // buat builder Alert Dialog (popup) dan tampilkan popup
            val builder = AlertDialog.Builder(requireContext())
            with(builder){
                setTitle("Info")
                setMessage("Perkiraan tarif sebesar Rp${tarif}")
                setPositiveButton("OK", DialogInterface.OnClickListener(function = okButtonClick))
                show()
            }
        }
    }

    private fun cekTarif(berat_muatan: String): Int {
        // tarif
        var tarif = 0
        try {
            // set harga per kilo
            val harga_per_kg = 1500
            // hitung tarif
            tarif = berat_muatan.toInt() * harga_per_kg
            return tarif
        } catch (e: Exception) {
            // tampilkan pesan error
            Toast.makeText(requireContext(), "Mohon isi data dengan benar!", Toast.LENGTH_SHORT)
                .show()
            return tarif
        }
    }

    private val okButtonClick = { dialog: DialogInterface, which: Int ->

    }

    companion object {
        fun getInstance(): CekTarifFragment = CekTarifFragment()
    }
}