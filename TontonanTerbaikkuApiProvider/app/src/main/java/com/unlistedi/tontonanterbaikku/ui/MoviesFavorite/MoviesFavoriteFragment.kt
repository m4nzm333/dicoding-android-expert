package com.unlistedi.tontonanterbaikku.ui.MoviesFavorite

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
import com.unlistedi.tontonanterbaikku.MoviesFavoriteAdapter
import com.unlistedi.tontonanterbaikku.R
import com.unlistedi.tontonanterbaikku.Tontonan
import kotlinx.android.synthetic.main.fragment_movies.view.*
import kotlinx.android.synthetic.main.movies_favorite_fragment.view.*


class MoviesFavoriteFragment : Fragment() {
    companion object {
        fun newInstance() = MoviesFavoriteFragment()
    }

    private lateinit var movieFavoriteAdapter : MoviesFavoriteAdapter
    private lateinit var context : Activity
    private lateinit var viewModel: MoviesFavoriteViewModel

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.movies_favorite_fragment, container, false)

        viewModel = MoviesFavoriteViewModel(this.context.application)
        viewModel.tontonans.observe(this, getMoviesFavorite)

        movieFavoriteAdapter = MoviesFavoriteAdapter(context){
                position -> onItemClickListener(position)
        }

        viewModel.setMoviesFavorite()
        movieFavoriteAdapter.setData(viewModel.movies)

        view.rvMoviesFavorite.layoutManager = LinearLayoutManager(context)
        view.rvMoviesFavorite.adapter = movieFavoriteAdapter

        return view
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = MoviesFavoriteViewModel(this.context.application)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private val getMoviesFavorite = Observer<ArrayList<Tontonan>>{
        movieFavoriteAdapter.setData(it)
        view?.loadingScreen?.visibility = View.GONE
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        if (activity != null) {
            context = activity
        }
    }

    fun onItemClickListener(position: Int) {
        movieFavoriteAdapter.removeAt(position)
    }

}
