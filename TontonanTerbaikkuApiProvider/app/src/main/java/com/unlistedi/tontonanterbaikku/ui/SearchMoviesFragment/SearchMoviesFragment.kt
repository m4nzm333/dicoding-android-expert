package com.unlistedi.tontonanterbaikku.ui.SearchMoviesFragment

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.unlistedi.tontonanterbaikku.MoviesAdapter

import com.unlistedi.tontonanterbaikku.R
import com.unlistedi.tontonanterbaikku.Tontonan
import kotlinx.android.synthetic.main.fragment_movies.view.*
import kotlinx.android.synthetic.main.search_movies_fragment.view.*

class SearchMoviesFragment : Fragment() {
    private lateinit var movieAdapter : MoviesAdapter
    private lateinit var context : Activity
    private lateinit var searchMoviesViewModel: SearchMoviesViewModel

    companion object {
        fun newInstance() = SearchMoviesFragment()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.search_movies_fragment, container, false)

        searchMoviesViewModel = SearchMoviesViewModel(context.application)

        view?.loadingScreen?.visibility = View.VISIBLE

        searchMoviesViewModel.tontonans.observe(this, getMovies)

        movieAdapter = MoviesAdapter(context)
        searchMoviesViewModel.setSearchMovie(context.intent.getStringExtra("query"))
        movieAdapter.setData(searchMoviesViewModel.movies)

        view.rvMoviesListSearch.layoutManager = LinearLayoutManager(context)
        view.rvMoviesListSearch.adapter = movieAdapter

        return view
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private val getMovies = Observer<ArrayList<Tontonan>>{
        movieAdapter.setData(it)
        view?.loadingScreen?.visibility = View.GONE
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        context = activity
    }
}
