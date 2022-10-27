package com.unlistedi.aplikasifavorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.unlistedi.aplikasifavorite.db.MoviesHelper
import kotlinx.android.synthetic.main.activity_detail_tontonan.*
import kotlinx.android.synthetic.main.layout_item_row.ivPoster
import kotlinx.android.synthetic.main.layout_item_row.tvDeskripsi

class DetailTontonanActivity : AppCompatActivity() {

    lateinit var moviesHelper : MoviesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tontonan)

        val tontonan = intent.getParcelableExtra<Tontonan>("Dataku")
        tvJudul.text = tontonan.nama
        tvRilis.text = tontonan.rilis
        tvSkor.text = tontonan.score
        tvDeskripsi.text = tontonan.deskripsi

        moviesHelper = MoviesHelper(applicationContext)

        val cursorMovies = moviesHelper.queryById(tontonan)
        if (cursorMovies.count > 0){
            ivFavoriteDetail.setImageResource(R.drawable.ic_favorite_ping_24dp)
        }

        Glide.with(ivPoster)
            .load("https://image.tmdb.org/t/p/w200" + tontonan.poster)
            .apply(RequestOptions().placeholder(R.drawable.ic_movie_black).error(R.drawable.ic_movie_black))
            .into(ivPoster)
    }
}
