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
import com.app.ekspedisimlg.adapter.TarifAdapter
import com.app.ekspedisimlg.helpers.PreferenceHelper.api_token
import com.app.ekspedisimlg.helpers.PreferenceHelper.customPreference
import com.app.ekspedisimlg.model.ListTarifModel
import com.app.ekspedisimlg.retrofit.ApiService
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_list_tarif.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.ConnectException

class ListTarifFragment : Fragment() {

    lateinit var field_cari_tarif: TextInputLayout
    lateinit var rvTarif: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_tarif, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // define views from xml
        field_cari_tarif = txt_cari_tarif
        rvTarif = recycleview_tarif
        // get data from api
        getTarif()
    }

    private fun getTarif() {
        try {
            val prefs = customPreference(requireContext(), "userdata")
            ApiService.endpoint.getListTarif(prefs.api_token.toString(), "pengirim")
                .enqueue(object : Callback<ListTarifModel> {
                    override fun onResponse(
                        call: Call<ListTarifModel>,
                        response: Response<ListTarifModel>
                    ) {
                        val status = response.body()?.status
                        val data = response.body()?.data
                        Log.d("responseData", data.toString())
                        if ( status == "success" && data != null) {
                            // set adapter and layout manager for rvTarif
                            rvTarif.adapter = TarifAdapter(data)
                            rvTarif.layoutManager = LinearLayoutManager(requireContext())
                        }
                    }

                    override fun onFailure(call: Call<ListTarifModel>, t: Throwable) {
                        Toast.makeText(requireContext(), t.toString(), Toast.LENGTH_LONG).show()
                    }

                })
        } catch (e: ConnectException) {
            Toast.makeText(requireContext(), "Failed to retrieve data from server.", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        fun getInstance(): ListTarifFragment = ListTarifFragment()
    }
}