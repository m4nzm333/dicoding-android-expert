package com.unlistedi.moviecatalogku

import android.content.res.Resources
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.unlistedi.moviecatalogku.R.array.data_name
import java.sql.Array
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var data_nama = arrayOf<String>()
    var data_deskripsi = arrayOf<String>()
    var data_score = arrayOf<String>()
    var data_rilis = arrayOf<String>()
    var data_poster = arrayOf<String>()
    lateinit var listView : ListView
    lateinit var movieAdapter : MovieAdapter
    lateinit var movies : ArrayList<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataGetter()
        dataGenerator()
        listView = findViewById(R.id.lv_movie)
        listView.adapter = movieAdapter
    }

    fun dataGenerator() {
        movies = ArrayList()
        for (i in 0  until data_nama.size){
            var movie = Movie(data_nama.get(i), data_rilis.get(i), data_score.get(i), data_deskripsi.get(i), resources.getIdentifier(data_poster.get(i), "drawable", packageName))
            movies.add(movie)
        }
        movieAdapter = MovieAdapter(this, movies)
    }
    fun dataGetter() {
        data_nama = resources.getStringArray(R.array.data_name)
        data_rilis = resources.getStringArray(R.array.data_rilis)
        data_score = resources.getStringArray(R.array.data_score)
        data_deskripsi = resources.getStringArray(R.array.data_deskripsi)
        data_poster = resources.getStringArray(R.array.data_poster)
    }
}
