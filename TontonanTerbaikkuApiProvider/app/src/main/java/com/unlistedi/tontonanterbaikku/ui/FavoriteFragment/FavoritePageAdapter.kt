package com.unlistedi.tontonanterbaikku.ui.FavoriteFragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.unlistedi.tontonanterbaikku.ui.MoviesFavorite.MoviesFavoriteFragment
import com.unlistedi.tontonanterbaikku.ui.TvShowFavoriteFragment.TvShowFavoriteFragment

class FavoritePageAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){

    private val pages = listOf(
        MoviesFavoriteFragment(),
        TvShowFavoriteFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Movies"
            1 -> "TV Show"
            else -> "Third Tab"
        }
    }
}