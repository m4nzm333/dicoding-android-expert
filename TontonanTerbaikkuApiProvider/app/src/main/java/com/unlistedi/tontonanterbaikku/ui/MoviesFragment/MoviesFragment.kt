package com.unlistedi.tontonanterbaikku.ui.MoviesFragment

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.unlistedi.tontonanterbaikku.MoviesAdapter
import com.unlistedi.tontonanterbaikku.R
import com.unlistedi.tontonanterbaikku.Tontonan
import kotlinx.android.synthetic.main.fragment_movies.view.*

class MoviesFragment : Fragment() {
    private lateinit var movieAdapter : MoviesAdapter
    private lateinit var context : Activity
    private lateinit var moviesViewModel: MoviesViewModel

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies, container, false)

        moviesViewModel = activity?.run {
            ViewModelProviders.of(this)[MoviesViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        view?.loadingScreen?.visibility = View.VISIBLE

        moviesViewModel.tontonans.observe(this, getMovies)


        movieAdapter = MoviesAdapter(context)
        moviesViewModel.setMovies()
        movieAdapter.setData(moviesViewModel.movies)

        view.rvMoviesList.layoutManager = LinearLayoutManager(context)
        view.rvMoviesList.adapter = movieAdapter


        return view
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private val getMovies = Observer<ArrayList<Tontonan>>{
        movieAdapter.setData(it)
        view?.loadingScreen?.visibility = View.GONE
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        if (activity != null) {
            context = activity
        }
    }
}
