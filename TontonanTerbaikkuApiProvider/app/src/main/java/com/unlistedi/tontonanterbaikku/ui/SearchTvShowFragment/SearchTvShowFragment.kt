package com.unlistedi.tontonanterbaikku.ui.SearchTvShowFragment

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
import com.unlistedi.tontonanterbaikku.TvShowAdapter
import kotlinx.android.synthetic.main.fragment_movies.view.*
import kotlinx.android.synthetic.main.search_tv_show_fragment.view.*

class SearchTvShowFragment : Fragment() {

    private lateinit var tvShowAdapter : TvShowAdapter
    private lateinit var context : Activity
    private lateinit var searchTvShowViewModel: SearchTvShowViewModel

    companion object {
        fun newInstance() =
            SearchTvShowFragment()
    }

    private lateinit var viewModel: SearchTvShowViewModel

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.search_tv_show_fragment, container, false)

        searchTvShowViewModel = SearchTvShowViewModel(context.application)

        view?.loadingScreen?.visibility = View.VISIBLE

        searchTvShowViewModel.tontonans.observe(this, getTvShowSearch)

        tvShowAdapter = TvShowAdapter(context)
        searchTvShowViewModel.setTvShowSearch(context.intent.getStringExtra("query"))
        tvShowAdapter.setData(tvShowAdapter.tontonans)

        view.rvTvShowListSearch.layoutManager = LinearLayoutManager(context)
        view.rvTvShowListSearch.adapter = tvShowAdapter

        return view
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private val getTvShowSearch = Observer<ArrayList<Tontonan>>{
        tvShowAdapter.setData(it)
        view?.loadingScreen?.visibility = View.GONE
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        context = activity
    }
}
