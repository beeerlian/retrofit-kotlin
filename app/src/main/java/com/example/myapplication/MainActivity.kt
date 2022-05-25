package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.interfaces.ApiInterface
import com.example.myapplication.model.AllUserResponse
import com.example.myapplication.model.LoginDataPost
import com.example.myapplication.retrofit.RetrofitClient
import com.example.myapplication.session.SessionManager
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var txtData: TextView
    lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtData = findViewById(R.id.txtData)

        sessionManager = SessionManager(this)

        runServices()


    }

    fun runServices(){
        lifecycleScope.launchWhenCreated {
            val ftoken = sessionManager.fetchAuthToken();
            if(ftoken.isNullOrEmpty()){
                login()
            }else{
                getUsers()
            }
        }
    }

    fun getUsers(){
        txtData.setText("Getting Users")
        var retrofit = RetrofitClient.getInstance("https://securitycam.herokuapp.com/api/")
        var apiInterface = retrofit.create(ApiInterface::class.java)

        lifecycleScope.launchWhenCreated {
            try {
                val ftoken = sessionManager.fetchAuthToken();
                if(ftoken.isNullOrEmpty()){
                    throw Exception("cannot fetch token")
                }
                val response = apiInterface.getUsers(ftoken)
                if (response.isSuccessful()) {
                    txtData.setText("Success :" + (response.body()?.data.toString() ?: "null"))

                } else {
                    txtData.setText("Error "+response.toString())
                }
            }catch (Ex:Exception){
                txtData.setText( "Error :" + Ex.localizedMessage)
            }
        }
    }

    fun login(){
        var retrofit = RetrofitClient.getInstance("https://securitycam.herokuapp.com/api/")
        var apiInterface = retrofit.create(ApiInterface::class.java)
        txtData.setText("Logging in ...")
        var loginDataPost = LoginDataPost(email = "client1@gmail.com", password = "20010506")
        lifecycleScope.launchWhenCreated {
            try {
                val response = apiInterface.login(loginDataPost)
                if (response.isSuccessful()) {
                    sessionManager.saveAuthToken(response.body()?.data?.accessToken.toString())
                    txtData.setText("Success :" + (response.body()?.data.toString() ?: "null"))
                    getUsers()
                } else {
                    txtData.setText("Error "+response.toString())
                }
            }catch (Ex:Exception){
                txtData.setText( "Error :" + Ex.localizedMessage)
            }
        }
    }
}