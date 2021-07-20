package com.app.ekspedisimlg.fragments

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.app.ekspedisimlg.R
import com.app.ekspedisimlg.activities.MainActivity
import com.app.ekspedisimlg.helpers.PreferenceHelper.clearValues
import com.app.ekspedisimlg.helpers.PreferenceHelper.customPreference
import com.app.ekspedisimlg.helpers.PreferenceHelper.email
import com.app.ekspedisimlg.helpers.PreferenceHelper.name
import com.app.ekspedisimlg.helpers.PreferenceHelper.phone
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // define shared preference helper objext
        val prefs = customPreference(requireContext(), "userdata")
        // change value of textview
        fullname.text = prefs.name
        user_email.text = prefs.email
        user_phone.text = prefs.phone
        // on click listener
        group_about_logout.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            with(builder)
            {
                setTitle("Peringatan")
                setMessage("Anda yakin ingin logout ?")
                setPositiveButton("Ok", DialogInterface.OnClickListener(function = positiveButtonClick))
                show()
            }
        }
    }

    private val positiveButtonClick = { dialog: DialogInterface, which: Int ->
        clearPreference()
        restartActivity()
    }

    private fun restartActivity() {
        getActivity()?.recreate()
    }

    private fun clearPreference() {
        context?.getSharedPreferences("userdata", Context.MODE_PRIVATE)?.edit()?.clear()?.apply()
    }

    companion object {
        fun getInstance(): ProfileFragment = ProfileFragment()
    }

}