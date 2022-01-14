package com.app.ekspedisimlg.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.ekspedisimlg.R
import com.app.ekspedisimlg.adapter.PesananAdapter
import com.app.ekspedisimlg.databinding.FragmentListPesananBinding
import com.app.ekspedisimlg.helpers.PreferenceHelper
import com.app.ekspedisimlg.helpers.PreferenceHelper.api_token
import com.app.ekspedisimlg.helpers.PreferenceHelper.role
import com.app.ekspedisimlg.model.PesananModel
import com.app.ekspedisimlg.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class ListPesananFragment : Fragment() {
    // define item view
    lateinit var rvListPesanan: RecyclerView

    // define view binding
    private var _binding: FragmentListPesananBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListPesananBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // define recyclerView
        rvListPesanan = binding.recyclerviewListpesanan
    }

    private fun getPesanan() {
        try {
            val prefs = PreferenceHelper.customPreference(requireContext(), "userdata")
            ApiService.endpoint.getPesanan(prefs.api_token.toString(), "pengirim")
                .enqueue(object : Callback<PesananModel> {
                    override fun onResponse(
                        call: Call<PesananModel>,
                        response: Response<PesananModel>
                    ) {
                        Log.d("Req Response", response.body().toString())
                        val data = response.body()?.data
                        if (response.body()?.status == "success" && data != null) {
                            // set adapter and layout manager
                            rvListPesanan.adapter = PesananAdapter(data)
                            rvListPesanan.layoutManager = LinearLayoutManager(requireContext())
                        } else {
                            Toast.makeText(requireContext(), "Tidak ada pesanan.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<PesananModel>, t: Throwable) {
                        Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                    }

                })
        } catch (e: Exception) {
            Log.d("catch error", e.message.toString())
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun getInstance(): ListPesananFragment = ListPesananFragment()
    }
}
