package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.interfaces.ApiInterface
import com.example.myapplication.model.LoginDataPost
import com.example.myapplication.retrofit.RetrofitClient

class MainActivity : AppCompatActivity() {
    lateinit var txtData: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtData = findViewById(R.id.txtData)

        login()
    }

    fun login(){
        txtData.setText("Logging in ...")
        var retrofit = RetrofitClient.getInstance("https://securitycam.herokuapp.com/api/")
        var apiInterface = retrofit.create(ApiInterface::class.java)
        var loginDataPost = LoginDataPost(email = "client1@gmail.com", password = "20010506")
        lifecycleScope.launchWhenCreated {
            try {
                val response = apiInterface.login(loginDataPost)
                if (response.isSuccessful()) {
                    Toast.makeText(
                        this@MainActivity,
                        "Success :" +response.body().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                    txtData.setText("Success :" + (response.body()?.data.toString() ?: "null"))

                } else {
                    txtData.setText("Error "+response.toString())
                    Toast.makeText(
                        this@MainActivity,
                        response.errorBody().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }catch (Ex:Exception){
                Toast.makeText(
                    this@MainActivity,
                    "Error :" + Ex.localizedMessage,
                    Toast.LENGTH_LONG
                ).show()
                txtData.setText( "Error :" + Ex.localizedMessage)
            }
        }
    }
}