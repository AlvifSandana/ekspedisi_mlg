package com.app.ekspedisimlg.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.app.ekspedisimlg.R
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    lateinit var buttonListTarif: CardView
    lateinit var buttonTracking: CardView
    lateinit var buttonLokasiKantor: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // define views from xml
        buttonLokasiKantor = btn_lokasikantor
        buttonTracking = btn_tracking
        // set button listener
        buttonListTarif.setOnClickListener {
            gotoFragment(ListTarifFragment())
        }
    }

    private fun gotoFragment(fragment: Fragment) {
        // define fragment transaction
        val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        // replace root layout with new fragment
        transaction.replace(R.id.rootFragment, fragment)
        transaction.addToBackStack(null)
        // commit
        transaction.commit()
    }

    companion object {
        fun getInstance(): HomeFragment = HomeFragment()
    }
}