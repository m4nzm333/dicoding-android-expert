package com.unlistedi.tontonanterbaikku.ui.SearchTvShowFragment

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.unlistedi.tontonanterbaikku.Tontonan
import org.json.JSONObject

class SearchTvShowViewModel(val activity: Application) : ViewModel() {
    private val API_KEY = "0e591f36a123d98c31f062724d86e10e"
    private val URL = "https://api.themoviedb.org/3/search/tv?api_key="
    val tvshows : ArrayList<Tontonan> = arrayListOf()

    var tontonans = MutableLiveData<ArrayList<Tontonan>>()

    private val requestQueue = Volley.newRequestQueue(activity)
    private val contextApplication = activity
    @RequiresApi(Build.VERSION_CODES.N)
    private var language = contextApplication.resources.configuration.locales[0].toLanguageTag()

    @RequiresApi(Build.VERSION_CODES.N)
    fun setTvShowSearch(query : String){
        when(contextApplication.resources.configuration.locales[0].toLanguageTag()){
            "en-US" -> language = "en-US"
            "id-ID" -> language = "id-ID"
        }
        val URL_REQUEST = "$URL$API_KEY&language=$language&query=$query"
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
                            itemJsonObject.getString("id"),
                            itemJsonObject.getString("original_name"),
                            itemJsonObject.getString("vote_average"),
                            itemJsonObject.getString("vote_average"),
                            itemJsonObject.getString("overview"),
                            itemJsonObject.getString("poster_path")
                        )
                    )
                }
                this.tontonans.postValue(tvshows)
            },
            Response.ErrorListener { error ->
                Log.e("MoviesViewModelError", error.toString())
            }
        )
        this.requestQueue.add(stringRequest)
    }
}
