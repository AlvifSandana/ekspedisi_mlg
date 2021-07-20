package com.app.ekspedisimlg.activities

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.app.ekspedisimlg.*
import com.app.ekspedisimlg.fragments.HomeFragment
import com.app.ekspedisimlg.fragments.HomeSupirFragment
import com.app.ekspedisimlg.fragments.NotifikasiFragment
import com.app.ekspedisimlg.fragments.ProfileFragment
import com.app.ekspedisimlg.helpers.PreferenceHelper.customPreference
import com.app.ekspedisimlg.helpers.PreferenceHelper.email
import com.app.ekspedisimlg.helpers.PreferenceHelper.password
import com.app.ekspedisimlg.helpers.PreferenceHelper.role
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val okButtonClick = { dialog: DialogInterface, which: Int ->
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // force disable night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // define menu
        val menu: Menu = bottom_navigation.menu
        // get menu item
        selectedMenu(menu.getItem(0))
        // set listener for selected menu item
        bottom_navigation.setOnNavigationItemSelectedListener { item: MenuItem ->
            selectedMenu(item)
            false
        }
    }

    override fun onStart() {
        super.onStart()
        // check if user not logged in, redirect to LoginActivity
        if (!checkLoginState()) {
            val builder = AlertDialog.Builder(this)
            with(builder){
                setTitle("Info")
                setMessage("Silahkan login untuk melanjutkan.")
                setPositiveButton("OK", DialogInterface.OnClickListener(function = okButtonClick))
                show()
            }
        }
    }

    // open and change fragment when user selected menu item
    private fun selectedFragment(fragment: Fragment) {
        // define fragment transaction
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        // replace content of fragment container with new fragment
        transaction.replace(R.id.rootFragment, fragment)
        // commit changes
        transaction.commit()
    }

    // menu handler
    private fun selectedMenu(item: MenuItem) {
        item.isChecked = true
        when (item.itemId) {
            R.id.home_page -> if (checkUserRole() == "pengirim") {
                selectedFragment(HomeFragment.getInstance())
            } else if (checkUserRole() == "supir") {
                selectedFragment(HomeSupirFragment.getInstance())
            }
            R.id.profil_page -> selectedFragment(ProfileFragment.getInstance())
            R.id.notifikasi_page -> selectedFragment(NotifikasiFragment.getInstance())
        }
    }

    // check login state
    private fun checkLoginState(): Boolean {
        val prefs = customPreference(this, "userdata")
        return !(prefs.email == "" && prefs.password == "")
    }

    // check user role
    private fun checkUserRole(): String {
        val prefs = customPreference(this, "userdata")
        return when (prefs.role) {
            "pengirim" -> {
                "pengirim"
            }
            "supir" -> {
                "supir"
            }
            else -> {
                "error"
            }
        }
    }

    fun printLog(msg: String) {
        Log.d("debug_log", msg)
    }
}
