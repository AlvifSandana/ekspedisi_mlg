package com.app.ekspedisimlg.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.app.ekspedisimlg.R
import com.app.ekspedisimlg.databinding.FragmentNewPesananBinding
import com.app.ekspedisimlg.helpers.PreferenceHelper
import com.app.ekspedisimlg.helpers.PreferenceHelper.api_token
import com.app.ekspedisimlg.helpers.PreferenceHelper.idUser
import com.app.ekspedisimlg.model.PesananResponseModel
import com.app.ekspedisimlg.retrofit.ApiService
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewPesananFragment : Fragment() {
    // define view item
    lateinit var txt_nama_pengirim: TextInputLayout
    lateinit var txt_alamat_pengirim: TextInputLayout
    lateinit var txt_jenis_muatan: TextInputLayout
    lateinit var txt_tanggal_muat: TextInputLayout
    lateinit var txt_lokasi_kirim: TextInputLayout
    lateinit var txt_catatan: TextInputLayout
    lateinit var txt_berat_muatan: TextInputLayout
    lateinit var btn_daftar: Button

    // define view binding
    private var _binding: FragmentNewPesananBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewPesananBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // set view binding
        txt_nama_pengirim = binding.txtNamaPengirim
        txt_alamat_pengirim = binding.txtAlamatPengirim
        txt_berat_muatan = binding.txtBeratMuatan
        txt_catatan = binding.txtCatatan
        txt_jenis_muatan = binding.jenisMuatan
        txt_lokasi_kirim = binding.txtLokasiKirim
        txt_tanggal_muat = binding.tanggalMuat
        btn_daftar = binding.btnNewPesanan
        // set button onClick listener
        btn_daftar.setOnClickListener {
            // validate form
            if (txt_berat_muatan.editText?.text.toString() != "" && txt_jenis_muatan.editText?.text.toString() != "" && txt_lokasi_kirim.editText?.text.toString() != "" && txt_tanggal_muat.editText?.text.toString() != "") {
                createPesanan(
                    txt_jenis_muatan.editText?.text.toString(),
                    txt_berat_muatan.editText?.text.toString(),
                    txt_tanggal_muat.editText?.text.toString(),
                    txt_lokasi_kirim.editText?.text.toString(),
                    txt_catatan.editText?.text.toString()
                )
            } else {
                Toast.makeText(
                    requireContext(),
                    "Mohon isi form dengan lengkap!",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }

    /**
     * method new pesanan
     */
    private fun createPesanan(
        jenis_muatan: String,
        berat_muatan: String,
        tanggal_muat: String,
        lokasi_kirim: String,
        catatan_pengiriman: String
    ) {
        // Shared Preference, mengambil data logged in user
        val prefs = PreferenceHelper.customPreference(requireContext(), "userdata")
        // panggil endpoint function createPesanan
        ApiService.endpoint.createPesanan(
            prefs.api_token.toString(),
            "pengirim",
            prefs.idUser.toString(),
            jenis_muatan = jenis_muatan,
            berat_muatan = berat_muatan,
            tanggal = tanggal_muat,
            lokasi_kirim = lokasi_kirim,
            catatan = catatan_pengiriman
        )
            .enqueue(object : Callback<PesananResponseModel> {
                override fun onResponse(
                    call: Call<PesananResponseModel>,
                    response: Response<PesananResponseModel>
                ) {
                    Log.d("req_response", response.body().toString())
                    if (response.body()?.status == "success") {
                        // tampilkan pesan toast
                        Toast.makeText(
                            requireContext(),
                            "${response.body()?.status}: ${response.body()?.message}",
                            Toast.LENGTH_LONG
                        ).show()
                        clearForm()
                    } else {
                        // tampilkan pesan toast
                        Toast.makeText(
                            requireContext(),
                            "${response.body()?.status}: ${response.body()?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<PesananResponseModel>, t: Throwable) {
                    Log.d("Req Error", t.message.toString())
                    // tampilkan pesan toast
                    Toast.makeText(
                        requireContext(),
                        "Error: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
    }

    /**
     * method clear form
     */
    private fun clearForm(){
        txt_nama_pengirim.editText?.setText("")
        txt_alamat_pengirim.editText?.setText("")
        txt_berat_muatan.editText?.setText("")
        txt_catatan.editText?.setText("")
        txt_jenis_muatan.editText?.setText("")
        txt_lokasi_kirim.editText?.setText("")
        txt_tanggal_muat.editText?.setText("")
    }

    companion object {
        fun getInstance(): NewPesananFragment = NewPesananFragment()
    }
}
