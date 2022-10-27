package com.unlistedi.tontonanterbaikku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

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
        ivPoster.setImageResource(tontonan.poster)
    }
}
