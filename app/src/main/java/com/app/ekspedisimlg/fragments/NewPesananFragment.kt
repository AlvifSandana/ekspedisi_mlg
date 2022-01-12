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
import com.app.ekspedisimlg.model.PesananResponseModel
import com.app.ekspedisimlg.retrofit.ApiService
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewPesananFragment : Fragment() {
    // define view item
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
        txt_berat_muatan = binding.txtBeratMuatan
        txt_catatan = binding.txtCatatan
        txt_jenis_muatan = binding.jenisMuatan
        txt_lokasi_kirim = binding.txtLokasiKirim
        txt_tanggal_muat = binding.tanggalMuat
        btn_daftar = binding.btnNewPesanan
        // set button onClick listener
        btn_daftar.setOnClickListener {
            // validate form
            if (txt_berat_muatan.editText?.text.toString() != "" && txt_jenis_muatan.editText?.text.toString() != "" && txt_lokasi_kirim.editText?.text.toString() != "" && txt_tanggal_muat.editText?.text.toString() != ""){
                createPesanan(txt_jenis_muatan.editText?.text.toString(), txt_berat_muatan.editText?.text.toString(), txt_tanggal_muat.editText?.text.toString(), txt_lokasi_kirim.editText?.text.toString(), txt_catatan.editText?.text.toString())
            } else {
                Toast.makeText(requireContext(), "Mohon isi form dengan lengkap!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun createPesanan(jenis_muatan: String, berat_muatan: String, tanggal_muat: String, lokasi_kirim: String, catatan_pengiriman: String){
        ApiService.endpoint.createPesanan(jenis_muatan, berat_muatan, tanggal_muat, lokasi_kirim, catatan_pengiriman)
            .enqueue(object: Callback<PesananResponseModel> {
                override fun onResponse(
                    call: Call<PesananResponseModel>,
                    response: Response<PesananResponseModel>
                ) {
                    Log.d("Req response", response.body().toString())
                    if (response.body()?.status == "success") {
                        Toast.makeText(
                            requireContext(),
                            "${response.body()?.status}: ${response.body()?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "${response.body()?.status}: ${response.body()?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<PesananResponseModel>, t: Throwable) {
                    Log.d("Req Error", t.message.toString())
                    Toast.makeText(
                        requireContext(),
                        "Error: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
    }

    companion object {
       fun getInstance(): NewPesananFragment = NewPesananFragment()
    }
}
