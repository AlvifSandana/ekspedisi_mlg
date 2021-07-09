package com.app.ekspedisimlg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // force disable night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // define menu
        var menu: Menu = bottom_navigation.menu
        // get menu item
        selectedMenu(menu.getItem(0))
        // set listener for selected menu item
        bottom_navigation.setOnNavigationItemSelectedListener {
            item: MenuItem -> selectedMenu(item)
            false
        }
    }

    // open and change fragment when user selected menu item
    fun selectedFragment(fragment: Fragment){
        var transaction: FragmentTransaction? = supportFragmentManager.beginTransaction()
        transaction?.replace(R.id.rootFragment, fragment)
        transaction?.commit()
    }

    // menu handler
    private fun selectedMenu(item: MenuItem){
        item.isChecked = true
        when(item.itemId){

        }
    }
}