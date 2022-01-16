package com.app.ekspedisimlg.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentTransaction
import com.app.ekspedisimlg.R
import com.app.ekspedisimlg.databinding.FragmentHomeSupirBinding

class HomeSupirFragment : Fragment() {
    // define view binding
    private var _binding: FragmentHomeSupirBinding? = null
    private val binding get() = _binding!!

    // define view item
    lateinit var btn_tarif: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeSupirBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // bind view
        btn_tarif = binding.btnTarif
        // set onclick listener
        btn_tarif.setOnClickListener {
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
        fun getInstance(): HomeSupirFragment = HomeSupirFragment()
    }
}