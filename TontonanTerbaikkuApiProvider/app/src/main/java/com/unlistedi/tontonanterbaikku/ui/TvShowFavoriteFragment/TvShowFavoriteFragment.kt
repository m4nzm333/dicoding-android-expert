package com.unlistedi.tontonanterbaikku.ui.TvShowFavoriteFragment

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
import com.unlistedi.tontonanterbaikku.R
import com.unlistedi.tontonanterbaikku.Tontonan
import com.unlistedi.tontonanterbaikku.TvShowFavoriteAdapter
import kotlinx.android.synthetic.main.fragment_movies.view.*
import kotlinx.android.synthetic.main.tv_show_favorite_fragment.view.*

class TvShowFavoriteFragment : Fragment() {
    companion object {
        fun newInstance() = TvShowFavoriteFragment()
    }

    private lateinit var viewModel: TvShowFavoriteViewModel
    private lateinit var tvShowAdapter: TvShowFavoriteAdapter
    private lateinit var context : Activity

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.tv_show_favorite_fragment, container, false)

        viewModel = TvShowFavoriteViewModel(this.context.application)
        viewModel.tontonans.observe(this, getTvShowFavorite)

        tvShowAdapter = TvShowFavoriteAdapter(context){
                position -> onItemClickListener(position)
        }
        viewModel.setTvShowFavorite()
        tvShowAdapter.setData(viewModel.movies)

        view.rvTvShowFavorite.layoutManager = LinearLayoutManager(context)
        view.rvTvShowFavorite.adapter = tvShowAdapter

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = TvShowFavoriteViewModel(this.context.application)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private val getTvShowFavorite = Observer<ArrayList<Tontonan>>{
        tvShowAdapter.setData(it)
        view?.loadingScreen?.visibility = View.GONE
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        if (activity != null) {
            context = activity
        }
    }

    fun onItemClickListener(position: Int) {
        tvShowAdapter.removeAt(position)
    }


}
