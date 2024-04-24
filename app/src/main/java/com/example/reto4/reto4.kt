package com.example.reto4

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.api.local_api
import com.example.bicentral.R
import com.example.bicentral.databinding.Reto3Binding
import com.example.bicentral.reto3_menu
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class reto4: AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: Reto3Binding
    lateinit var user: EditText
    lateinit var pass: EditText
    var buttonAccept: Button? = null

    var stringBuilderFinal: String? = null

    var imageurl: String? = null
    var name: String? = null
    var address: String? = null

    var usr: String? = null
    var passw: String? = null
    var flagLogin: Boolean? = false

    var BaseURL_Post = "http://www.balam-knights.com/"

    var BaseURL = "https://api.openweathermap.org/"
    var AppID = "2e65127e909e178d0af311a81f39948c"
    var lat = "15.7835"
    var lon = "90.2308"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = Reto3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()

        user = binding.edTU
        pass = binding.edTP
        buttonAccept = binding.loginButton

        buttonAccept?.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.loginButton -> {
                if (user.text.toString() != "" && pass.text.toString() != ""){

                    usr = user.text.toString()
                    passw = pass.text.toString()
                    flagLogin = true;

                    getLoginResult()
                }
                else
                    showToast("No contiene texto...")
            }
        }
    }

    private fun validaLogin(username: String, password: String){
        val sessionToken = local_api().validateUser("admin", "admin")

        if(sessionToken != null){
            val intent = Intent(this, reto3_menu::class.java).apply {
                if(imageurl != null && name != null && address != null){
                    putExtra("imageurl", imageurl)
                    putExtra("name", name)
                    putExtra("address", address)
                }
                else
                    putExtra("SESSION_TOKEN", sessionToken)
            }
            startActivity(intent)
        }else {
            showToast("Credenciales incorrectas...")
        }
    }

    private fun getClient(): OkHttpClient =
         OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .build()

    fun getLoginResult(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseURL_Post)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()

        val service = retrofit.create(balamKnightsService::class.java)

        if(flagLogin == true) {
            val dtoParametersDto = ParametersDto(usr.toString(), passw.toString())

            val call = service.getResultLogin(dtoParametersDto)

            call.enqueue(object : retrofit2.Callback<balamKnightsResponse> {

                override fun onResponse(call: retrofit2.Call<balamKnightsResponse>, response: retrofit2.Response<balamKnightsResponse>) {

                    if (response.code() == 200) {
                        val balamKnightsResp = response.body()!!

                        imageurl = balamKnightsResp.imageurl!!
                        name = balamKnightsResp.name!!
                        address = balamKnightsResp.address!!
                        validaLogin(user.text.toString(), pass.text.toString())
                    }
                    else
                        showToast("Error: " + response.code() + "Credenciales incorrectas")
                }

                override fun onFailure(call: retrofit2.Call<balamKnightsResponse>, t: Throwable) {
                    stringBuilderFinal = "Whitout data"
                    showToast("Error en llamada a método")
                }
            })
        }
    }

    fun getData(){
        val retrofit = Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(GsonConverterFactory.create()).build()

        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(lat, lon, AppID)

        call.enqueue(object : retrofit2.Callback<WeatherResponse> {

            override fun onResponse(call: retrofit2.Call<WeatherResponse>, response: retrofit2.Response<WeatherResponse>) {

                if (response.code() == 200) {
                    val weatherResponse = response.body()!!

                    val stringBuilder = "Country: " +
                            weatherResponse.sys!!.country +
                            "\n" +
                            "Temperature: " +
                            weatherResponse.main!!.temp +
                            "\n" +
                            "Temperature(Min): " +
                            weatherResponse.main!!.temp_min +
                            "\n" +
                            "Temperature(Max): " +
                            weatherResponse.main!!.temp_max +
                            "\n" +
                            "Humidity: " +
                            weatherResponse.main!!.humidity +
                            "\n" +
                            "Pressure: " +
                            weatherResponse.main!!.pressure

                    stringBuilderFinal = stringBuilder
                }
            }

            override fun onFailure(call: retrofit2.Call<WeatherResponse>, t: Throwable) {
                stringBuilderFinal = "Whitout data"
                showToast("Error en llamada a método")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
