package com.unlistedi.moviecatalogku

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        var movie = intent.getParcelableExtra<Movie>("movie");
        var tvNama =  findViewById<TextView>(R.id.tvNamaMovie)
        var tvDeskripsi =  findViewById<TextView>(R.id.tvDescription)
        var tvRilis =  findViewById<TextView>(R.id.tvRilis)
        var tvRating =  findViewById<TextView>(R.id.tvRating)
        var ivPoster =  findViewById<ImageView>(R.id.ivPoster)

        tvNama.text = movie.nama
        tvRilis.text = movie.rilis
        tvRating.text = movie.score
        tvDeskripsi.text = movie.deskripsi
        ivPoster.setImageResource(movie.poster)
    }
}
