package com.app.ekspedisimlg.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.app.ekspedisimlg.R
import com.app.ekspedisimlg.helpers.PreferenceHelper.customPreference
import com.app.ekspedisimlg.model.RegisterPengirimModel
import com.app.ekspedisimlg.model.RegisterResponseModel
import com.app.ekspedisimlg.model.RegisterSupirModel
import com.app.ekspedisimlg.model.RegisterSupirResponseModel
import com.app.ekspedisimlg.retrofit.ApiService
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    lateinit var firstname: com.google.android.material.textfield.TextInputLayout
    lateinit var lastname: com.google.android.material.textfield.TextInputLayout
    lateinit var email: com.google.android.material.textfield.TextInputLayout
    lateinit var password: com.google.android.material.textfield.TextInputLayout
    lateinit var confirmpassword: com.google.android.material.textfield.TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // define all field from xml layout
        firstname = txt_first_name
        lastname = txt_last_name
        email = txt_reg_email
        password = txt_reg_password
        confirmpassword = txt_reg_confirm_password
        var role = ""
        val button_reg = btn_register

        // on button checked listener for toggle button
        toggle_btn_group.addOnButtonCheckedListener { toggle, checkedId, isChecked ->
            if (isChecked) {
                if (checkedId == R.id.toggle_btn_pengirim) {
                    role = "pengirim"
                } else if (checkedId == R.id.toggle_btn_sopir) {
                    role = "supir"
                }
            }
        }

        // on click listener for register button
        button_reg.setOnClickListener {
            doRegister(
                nama = "${firstname.editText?.text.toString()} ${lastname.editText?.text.toString()}",
                email = email.editText?.text.toString(),
                password = password.editText?.text.toString(),
                confirm_password = confirmpassword.editText?.text.toString(),
                role = role
            )
        }

    }

    /**
     * Handle register process
     *
     * @param nama String
     * @param email String
     * @param password String
     * @param confirm_password String
     */
    private fun doRegister(
        nama: String,
        email: String,
        password: String,
        confirm_password: String,
        role: String
    ) {
        // instance of shared preference helper
        val prefs = customPreference(this, "userdata")

        // define pengirim registration data
        val datapengirim =
            RegisterPengirimModel(nama, alamat_pengirim = "-", nomor_telpon = "-", email, password)

        // define supir registration data
        val datasupir = RegisterSupirModel(
            nama,
            nama_supircadang = "-",
            alamat_supir = "-",
            nomor_telpon = "-",
            email,
            password
        )

        Log.d("regData", "supir: ${datasupir}\npengirim: ${datapengirim}")
        // password validation
        if (password == confirm_password) {
            // check when role = pengirim or supir
            when (role) {
                "pengirim" -> ApiService.endpoint.regPengirim(
                    datapengirim.nama_pengirim,
                    datapengirim.alamat_pengirim,
                    datapengirim.nomor_telpon,
                    datapengirim.email,
                    datapengirim.password
                )
                    .enqueue(object : Callback<RegisterResponseModel> {
                        override fun onResponse(
                            call: Call<RegisterResponseModel>,
                            response: Response<RegisterResponseModel>
                        ) {
                            Log.d("Res Register", response.body().toString());
                            if (response.body()?.status == "success") {
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "${response.body()?.message}\nSilahkan login untuk melanjutkan.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "${response.body()?.status}: ${response.body()?.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<RegisterResponseModel>, t: Throwable) {
                            Log.d("onFailure", t.toString())
                            Toast.makeText(this@RegisterActivity, t.toString(), Toast.LENGTH_LONG)
                                .show()
                        }
                    })

                "supir" -> ApiService.endpoint.regSupir(
                    datasupir.nama_supir,
                    datasupir.nama_supircadang,
                    datasupir.alamat_supir,
                    datasupir.nomor_telpon,
                    datasupir.email,
                    datasupir.password
                )
                    .enqueue(object : Callback<RegisterSupirResponseModel> {
                        override fun onResponse(
                            call: Call<RegisterSupirResponseModel>,
                            response: Response<RegisterSupirResponseModel>
                        ) {
                            Log.d("Res Register", response.body().toString());
                            if (response.body()?.status == "success") {
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "${response.body()?.message}\nSilahkan login untuk melanjutkan.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "${response.body()?.status}: ${response.body()?.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        override fun onFailure(
                            call: Call<RegisterSupirResponseModel>,
                            t: Throwable
                        ) {
                            Log.d("onFailure", t.toString())
                            Toast.makeText(this@RegisterActivity, t.toString(), Toast.LENGTH_LONG)
                                .show()
                        }
                    })
            }
        } else {
            Toast.makeText(this, "Mohon lengkapi data registrasi.", Toast.LENGTH_LONG).show()
        }
    }
}