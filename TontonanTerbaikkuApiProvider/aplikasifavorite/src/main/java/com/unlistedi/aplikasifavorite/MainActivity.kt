package com.unlistedi.aplikasifavorite

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.unlistedi.aplikasifavorite.ui.MoviesFavorite.MoviesFavoriteViewModel
import com.unlistedi.aplikasifavorite.ui.MoviesFavoriteAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MoviesFavoriteViewModel
    private lateinit var movieFavoriteAdapter : MoviesFavoriteAdapter

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = MoviesFavoriteViewModel(application)
        viewModel.tontonans.observe(this, getMoviesFavorite)

        movieFavoriteAdapter = MoviesFavoriteAdapter(applicationContext){
                position -> onItemClickListener(position)
        }

        supportActionBar?.title = "Film Favorit Costumer"
        viewModel.setMoviesFavorite()
        movieFavoriteAdapter.setData(viewModel.movies)

        rvMoviesFavorite.layoutManager = LinearLayoutManager(applicationContext)
        rvMoviesFavorite.adapter = movieFavoriteAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_without_search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_change_settings) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private val getMoviesFavorite = Observer<ArrayList<Tontonan>>{
        movieFavoriteAdapter.setData(it)
    }

    fun onItemClickListener(position: Int) {
        movieFavoriteAdapter.removeAt(position)
    }
}
