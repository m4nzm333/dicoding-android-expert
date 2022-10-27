package com.unlistedi.tontonanterbaikku.ui.TvShowFragment

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
import com.unlistedi.tontonanterbaikku.R
import com.unlistedi.tontonanterbaikku.Tontonan
import com.unlistedi.tontonanterbaikku.TvShowAdapter
import kotlinx.android.synthetic.main.fragment_tv_show.view.*

class TvShowFragment : Fragment() {
    private lateinit var tvShowAdapter : TvShowAdapter
    private lateinit var context : Activity
    private lateinit var tvShowViewModel: TvShowViewModel

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tv_show, container, false)

        view?.loadingScreen?.visibility = View.VISIBLE

        tvShowViewModel = activity?.run {
            ViewModelProviders.of(this)[TvShowViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
        tvShowViewModel.tontonans.observe(this, getTvShow)

        tvShowAdapter = TvShowAdapter(context)
        tvShowViewModel.setTvShow()
        tvShowAdapter.setData(tvShowViewModel.tvshows)

        view.rvTvShowList.layoutManager = LinearLayoutManager(context)
        view.rvTvShowList.adapter = tvShowAdapter

        return view
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private val getTvShow = Observer<ArrayList<Tontonan>>{
        tvShowAdapter.setData(it)
        view?.loadingScreen?.visibility = View.GONE
    }
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        if (activity != null) {
            context = activity
        }
    }

}
