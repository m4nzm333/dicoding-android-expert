package com.unlistedi.tontonanterbaikku.ui.MoviesFragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unlistedi.tontonanterbaikku.TontonanAdapter
import com.unlistedi.tontonanterbaikku.R
import com.unlistedi.tontonanterbaikku.Tontonan

class MoviesFragment : Fragment() {
    private var dataMoviesNama = arrayOf<String>()
    private var dataMoviesRilis = arrayOf<String>()
    private var dataMoviesSkor = arrayOf<String>()
    private var dataMoviesDeskripsi = arrayOf<String>()
    private var dataMoviesPoster = arrayOf<String>()
    private lateinit var rvMoviesList : RecyclerView
    private lateinit var tontonans : ArrayList<Tontonan>
    private lateinit var context : Activity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies, container, false)
        dataGetter()
        dataGenerator()

        rvMoviesList = view.findViewById(R.id.rvMoviesList)
        rvMoviesList.layoutManager = LinearLayoutManager(context)
        val movieAdapter = TontonanAdapter(tontonans, context)
        rvMoviesList.adapter = movieAdapter

        return view
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        if (activity != null) {
            context = activity
        }
    }

    fun dataGenerator() {
        tontonans = ArrayList()
        for (i in 0  until dataMoviesNama.size){
            val movie = Tontonan(
                dataMoviesNama.get(i),
                dataMoviesRilis[i],
                dataMoviesSkor[i],
                dataMoviesDeskripsi[i],
                resources.getIdentifier(dataMoviesPoster[i], "drawable", context.packageName)
            )
            tontonans.add(movie)
        }
    }
    fun dataGetter() {
        dataMoviesNama = resources.getStringArray(R.array.dataMoviesNama)
        dataMoviesRilis = resources.getStringArray(R.array.dataMoviesRilis)
        dataMoviesSkor = resources.getStringArray(R.array.dataMoviesSkor)
        dataMoviesDeskripsi = resources.getStringArray(R.array.dataMoviesDeskripsi)
        dataMoviesPoster = resources.getStringArray(R.array.dataMoviesPoster)
    }
}
