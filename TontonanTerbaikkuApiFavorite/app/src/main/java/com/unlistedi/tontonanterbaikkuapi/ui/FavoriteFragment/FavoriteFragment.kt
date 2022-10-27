package com.unlistedi.tontonanterbaikkuapi.ui.FavoriteFragment


import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.unlistedi.tontonanterbaikkuapi.R
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {
    lateinit var myActivity: Activity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewpager_main.adapter  = FavoritePageAdapter(childFragmentManager)
        super.onViewCreated(view, savedInstanceState)
        tabs_main.setupWithViewPager(viewpager_main)
    }

    override fun onAttach(activity: Activity) {
        myActivity = activity
        super.onAttach(activity)
    }

}
