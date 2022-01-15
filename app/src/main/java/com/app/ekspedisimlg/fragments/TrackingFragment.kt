package com.app.ekspedisimlg.fragments

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.app.ekspedisimlg.R
import com.app.ekspedisimlg.databinding.FragmentTrackingBinding
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.config.Configuration.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [TrackingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TrackingFragment : Fragment() {
    private var idMuatan: String? = null
    // define view binding
    private var _binding: FragmentTrackingBinding? = null
    private val binding get() = _binding!!
    // map
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private lateinit var map: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idMuatan = it.getString(ARG_PARAM1)
        }
        getInstance().load(requireContext(), PreferenceManager.getDefaultSharedPreferences(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTrackingBinding.inflate(inflater, container, false)
        val view = binding.root
        // initiate map
        map = binding.trackingMapView
        // set map tile source
        map.setTileSource(TileSourceFactory.MAPNIK)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // map controller
        val mapController = map.controller
        mapController.setZoom(17.5)
        // set start point
        val startPoint = GeoPoint(-8.308812,114.2869448);
        mapController.setCenter(startPoint);
        // set marker
        val marker = Marker(map)
        marker.position = GeoPoint(-8.308812,114.2869448)
        marker.icon = ContextCompat.getDrawable(requireContext(), R.drawable.marker_default)
        marker.title = "Init Marker"
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        map.overlays.add(marker)
        map.invalidate()
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            TrackingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}