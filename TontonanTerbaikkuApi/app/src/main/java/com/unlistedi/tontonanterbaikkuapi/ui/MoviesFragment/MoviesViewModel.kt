package com.unlistedi.tontonanterbaikkuapi.ui.MoviesFragment

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

class MoviesViewModel(application: Application) : AndroidViewModel(application) {
    private val API_KEY = "0e591f36a123d98c31f062724d86e10e"
    private val URL = "https://api.themoviedb.org/3/discover/movie?api_key="
    val movies : ArrayList<Tontonan> = arrayListOf()

    var tontonans = MutableLiveData<ArrayList<Tontonan>>()

    private val requestQueue = Volley.newRequestQueue(application)
    private val contextApplication = application
    @RequiresApi(Build.VERSION_CODES.N)
    private var language = contextApplication.resources.configuration.locales[0].toLanguageTag()

    @RequiresApi(Build.VERSION_CODES.N)
    fun setMovies(){
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
                movies.clear()
                for (i in 0 until resultJson.length()){
                    val itemJsonObject = resultJson.getJSONObject(i)
                    movies.add(
                        Tontonan(
                            itemJsonObject.getString("original_title"),
                            itemJsonObject.getString("release_date"),
                            itemJsonObject.getString("vote_average"),
                            itemJsonObject.getString("overview"),
                            itemJsonObject.getString("poster_path")
                        )
                    )
                }
                this.tontonans.postValue(movies)
                Log.d("MoviesViewModel", tontonans.toString())
                Log.d("MoviesViewModel", response)
            },
            Response.ErrorListener { error ->
                Log.e("MoviesViewModelError", error.toString())
            }
        )
        this.requestQueue.add(stringRequest)
    }
}