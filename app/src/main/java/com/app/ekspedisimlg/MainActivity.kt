package com.app.ekspedisimlg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.app.ekspedisimlg.model.ListTarifModel
import com.app.ekspedisimlg.retrofit.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    override fun onStart() {
        super.onStart()
        getData()
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
            R.id.notifikasi_page -> selectedFragment(NotifikasiFragment.getInstance())
        }
    }

    fun getData(){
        ApiService.endpoint.getListTarif()
            .enqueue(object : Callback<ListTarifModel>{
                override fun onResponse(
                    call: Call<ListTarifModel>,
                    response: Response<ListTarifModel>
                ) {
                    showData(response.body()!!)
                }

                override fun onFailure(call: Call<ListTarifModel>, t: Throwable) {
                    printLog(t.toString())
                }

            })
    }

    fun showData(data: ListTarifModel){
        val results = data.status
        printLog(results)
    }

    fun printLog(msg: String){
        Log.d("data", msg)
    }
}
