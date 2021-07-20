package com.app.ekspedisimlg.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.app.ekspedisimlg.R
import com.app.ekspedisimlg.helpers.PreferenceHelper.address
import com.app.ekspedisimlg.helpers.PreferenceHelper.api_token
import com.app.ekspedisimlg.helpers.PreferenceHelper.customPreference
import com.app.ekspedisimlg.helpers.PreferenceHelper.email
import com.app.ekspedisimlg.helpers.PreferenceHelper.name
import com.app.ekspedisimlg.helpers.PreferenceHelper.name_1
import com.app.ekspedisimlg.helpers.PreferenceHelper.phone
import com.app.ekspedisimlg.helpers.PreferenceHelper.role
import com.app.ekspedisimlg.model.LoginResponseModel
import com.app.ekspedisimlg.retrofit.ApiService
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var email: com.google.android.material.textfield.TextInputLayout
    lateinit var password: com.google.android.material.textfield.TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // define component from layout
        val email = txt_email
        val password = txt_password
        val button_login = btn_login
        val button_register = btn_to_reg

        button_login.setOnClickListener {
            doLogin(email.editText?.text.toString(), password.editText?.text.toString())
        }

        button_register.setOnClickListener {
            gotoRegisterActivity()
        }
    }

    private fun gotoRegisterActivity() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun doLogin(email: String, password: String) {
        // define custom shared preference
        val prefs = customPreference(this, "userdata")
        // login request with retrofit Api Service
        ApiService.endpoint.loginAll(email, password)
            .enqueue(object: Callback<LoginResponseModel>{
                override fun onResponse(
                    call: Call<LoginResponseModel>,
                    response: Response<LoginResponseModel>
                ) {
                    val status = response.body()?.status
                    val message = response.body()?.message
                    val resdata = response.body()?.data

                    Log.d("login_debug", response.body().toString())

                    if (status == "success" && message == "Berhasil login (pengirim)"){
                        prefs.name = resdata?.get(0)?.nama
                        prefs.address = resdata?.get(0)?.alamat
                        prefs.phone = resdata?.get(0)?.nomor_telpon
                        prefs.email = resdata?.get(0)?.email
                        prefs.api_token = resdata?.get(0)?.api_token
                        prefs.role = "pengirim"
                        gotoHomeActivity()
                    } else if (status == "success" && message == "Berhasil login (supir)"){
                        prefs.name = resdata?.get(0)?.nama
                        prefs.name_1 = resdata?.get(0)?.supir_cadangan
                        prefs.address = resdata?.get(0)?.alamat
                        prefs.phone = resdata?.get(0)?.nomor_telpon
                        prefs.email = resdata?.get(0)?.email
                        prefs.api_token = resdata?.get(0)?.api_token
                        prefs.role = "supir"
                        gotoHomeActivity()
                    } else if (status == "failed" || status == "error"){
                        Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Email atau password salah!", Toast.LENGTH_LONG).show()
                    Log.d("onFailure", t.toString())
                }

            })
    }

    private fun gotoHomeActivity(){
        val intent = Intent(this, MainActivity::class.java)
        finish()
        startActivity(intent)
    }
}