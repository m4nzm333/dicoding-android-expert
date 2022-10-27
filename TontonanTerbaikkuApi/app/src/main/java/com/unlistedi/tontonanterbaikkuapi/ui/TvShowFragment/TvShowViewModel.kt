package com.unlistedi.tontonanterbaikkuapi.ui.TvShowFragment

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.unlistedi.tontonanterbaikkuapi.Tontonan
import org.json.JSONObject

class TvShowViewModel(application: Application) : AndroidViewModel(application) {
    private val API_KEY = "0e591f36a123d98c31f062724d86e10e"
    private val URL = "https://api.themoviedb.org/3/discover/tv?api_key="
    val tvshows : ArrayList<Tontonan> = arrayListOf()

    var tontonans = MutableLiveData<ArrayList<Tontonan>>()

    private val requestQueue = Volley.newRequestQueue(application)
    private val contextApplication = application
    @RequiresApi(Build.VERSION_CODES.N)
    private var language = contextApplication.resources.configuration.locales[0].toLanguageTag()

    @RequiresApi(Build.VERSION_CODES.N)
    fun setTvShow(){
        when(contextApplication.resources.configuration.locales[0].toLanguageTag()){
            "en-US" -> language = "en-US"
            "id-ID" -> language = "id-ID"
        }
        val URL_REQUEST = "$URL$API_KEY&language=$language"
        val stringRequest = StringRequest(
            Request.Method.GET,
            URL_REQUEST,
            Response.Listener<String> { response ->
                val jsonResponse = JSONObject(response)
                val resultJson = jsonResponse.getJSONArray("results")
                tvshows.clear()
                for (i in 0 until resultJson.length()){
                    val itemJsonObject = resultJson.getJSONObject(i)
                    tvshows.add(
                        Tontonan(
                            itemJsonObject.getString("original_name"),
                            itemJsonObject.getString("first_air_date"),
                            itemJsonObject.getString("vote_average"),
                            itemJsonObject.getString("overview"),
                            itemJsonObject.getString("poster_path")
                        )
                    )
                }
                this.tontonans.postValue(tvshows)
                Log.d("TvShowViewModel", tontonans.toString())
                Log.d("TvShowViewModel", response)
            },
            Response.ErrorListener { error ->
                Log.e("MoviesViewModelError", error.toString())
            }
        )
        this.requestQueue.add(stringRequest)
    }
}