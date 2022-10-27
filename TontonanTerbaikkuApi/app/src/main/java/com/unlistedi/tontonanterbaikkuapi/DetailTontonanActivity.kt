package com.unlistedi.tontonanterbaikkuapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailTontonanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tontonan)

        val tontonan = intent.getParcelableExtra<Tontonan>("Dataku")
        val tvJudul = findViewById<TextView>(R.id.tvJudul)
        val tvRilis = findViewById<TextView>(R.id.tvRilis)
        val tvDeskripsi = findViewById<TextView>(R.id.tvDeskripsi)
        val tvSkor = findViewById<TextView>(R.id.tvSkor)
        val ivPoster = findViewById<ImageView>(R.id.ivPoster)

        tvJudul.text = tontonan.nama
        tvRilis.text = tontonan.rilis
        tvSkor.text = tontonan.score
        tvDeskripsi.text = tontonan.deskripsi
        Glide.with(ivPoster)
            .load("https://image.tmdb.org/t/p/w200" + tontonan.poster)
            .apply(RequestOptions().placeholder(R.drawable.ic_movie_black).error(R.drawable.ic_movie_black))
            .into(ivPoster)
    }
}
