package com.unlistedi.tontonanterbaikku.ui.MoviesFavorite

import android.app.Application
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.unlistedi.tontonanterbaikku.R
import com.unlistedi.tontonanterbaikku.Tontonan
import com.unlistedi.tontonanterbaikku.db.MoviesHelper
import org.json.JSONObject

class MoviesFavoriteViewModel(application: Application) : ViewModel() {
    private val API_KEY = "?api_key=0e591f36a123d98c31f062724d86e10e"
    private val URL = "https://api.themoviedb.org/3/movie/"
    val movies : ArrayList<Tontonan> = arrayListOf()

    var tontonans = MutableLiveData<ArrayList<Tontonan>>()

    private val requestQueue = Volley.newRequestQueue(application)
    private val contextApplication = application
    @RequiresApi(Build.VERSION_CODES.N)
    private var language = contextApplication.resources.configuration.locales[0].toLanguageTag()

    @RequiresApi(Build.VERSION_CODES.N)
    fun setMoviesFavorite(){
        when(contextApplication.resources.configuration.locales[0].toLanguageTag()){
            "en-US" -> language = "en-US"
            "id-ID" -> language = "id-ID"
        }

        // Ambil Data dari SQL lite
        val moviesHelper = MoviesHelper(contextApplication)
        moviesHelper.open()
        val cursor = moviesHelper.queryAll()
        if (cursor.count == 0){
            Toast.makeText(contextApplication, contextApplication.resources.getString(R.string.was_empty_movies), Toast.LENGTH_SHORT).show()
        }
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast){
                val URL_REQUEST = "$URL${cursor.getString(cursor.getColumnIndex("_id"))}$API_KEY&language=$language"
                val stringRequest = StringRequest(
                    Request.Method.GET,
                    URL_REQUEST,
                    Response.Listener<String> { response ->
                        val jsonResponse = JSONObject(response)
                        movies.add(
                            Tontonan(
                                jsonResponse.getString("id"),
                                jsonResponse.getString("original_title"),
                                jsonResponse.getString("release_date"),
                                jsonResponse.getString("vote_average"),
                                jsonResponse.getString("overview"),
                                jsonResponse.getString("poster_path")
                            )
                        )
                        this.tontonans.postValue(movies)
                    },
                    Response.ErrorListener { error ->
                        Log.e("MoviesViewModelError", error.toString())
                    }
                )
                this.requestQueue.add(stringRequest)
                cursor.moveToNext()
            }
        }
    }
}
