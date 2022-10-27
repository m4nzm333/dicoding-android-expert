package com.unlistedi.tontonanterbaikku

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_item_row.view.*

class TontonanAdapter(
    private val tontonans: ArrayList<Tontonan>,
    private val context : Context) : RecyclerView.Adapter<MovieHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): MovieHolder {
        return MovieHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.layout_item_row,
                viewGroup,
                false
            ), context
        )
    }

    override fun getItemCount(): Int = tontonans.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bindMovies(tontonans[position])
    }
}

class MovieHolder(view: View, val context: Context) : RecyclerView.ViewHolder(view) {
    private val tvMovieNama = view.tvNama
    private val tvDeskripsi = view.tvDeskripsi
    private val ivPoster = view.ivPoster
    private val btnSelengkapnya = view.btnSelengkapnya
    fun bindMovies(tontonan: Tontonan) {
        tvMovieNama.text = tontonan.nama
        tvDeskripsi.text = tontonan.deskripsi
        ivPoster.setImageResource(tontonan.poster)
        btnSelengkapnya.setOnClickListener{
            val intent = Intent(context, DetailTontonanActivity::class.java)
            intent.putExtra("Dataku", tontonan)
            context.startActivity(intent)
        }
    }
}
