package com.app.ekspedisimlg.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentTransaction
import com.app.ekspedisimlg.R
import com.app.ekspedisimlg.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    // define view item
    lateinit var buttonListTarif: CardView
    lateinit var buttonTracking: CardView
    lateinit var buttonLokasiKantor: CardView

    // define view binding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // define views from xml
        buttonLokasiKantor = binding.btnLokasikantor
        buttonTracking = binding.btnTracking
        buttonListTarif = binding.btnTarif
        // set button listener
        buttonLokasiKantor.setOnClickListener {
            gotoFragment(LokasiFragment())
        }
        buttonListTarif.setOnClickListener {
            gotoFragment(CekTarifFragment())
        }
        buttonTracking.setOnClickListener {
            gotoFragment(TrackingFragment())
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        fun getInstance(): HomeFragment = HomeFragment()
    }
}