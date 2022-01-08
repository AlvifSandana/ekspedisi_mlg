package com.app.ekspedisimlg.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.ekspedisimlg.R


class InvoiceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_invoice, container, false)
    }

    companion object {
        fun getInstance(): InvoiceFragment = InvoiceFragment()
    }
}