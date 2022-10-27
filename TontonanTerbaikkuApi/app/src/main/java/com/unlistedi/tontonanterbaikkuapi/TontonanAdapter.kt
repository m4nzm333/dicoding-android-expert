package com.unlistedi.tontonanterbaikkuapi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_item_row.view.*

class TontonanAdapter(private val context : Context) : RecyclerView.Adapter<MovieHolder>() {

    var tontonans: ArrayList<Tontonan> = arrayListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): MovieHolder {
        return MovieHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.layout_item_row,
                viewGroup,
                false
            ), context
        )
    }

    fun setData(tontonans: ArrayList<Tontonan>){
        this.tontonans.clear()
        this.tontonans.addAll(tontonans)
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int = tontonans.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bindMovies(tontonans[position])
    }
}

class MovieHolder(private val view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
    private val tvMovieNama = this.view.tvNama
    private val tvDeskripsi = this.view.tvDeskripsi
    private val ivPoster = this.view.ivPoster
    private val btnSelengkapnya = this.view.btnSelengkapnya
    fun bindMovies(tontonan: Tontonan) {
        tvMovieNama.text = tontonan.nama
        if(tontonan.deskripsi ==  ""){
            tvDeskripsi.text = "Deskripsi belum ditambahkan."
        } else {
            tvDeskripsi.text = tontonan.deskripsi
        }
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w200" + tontonan.poster)
            .apply(RequestOptions().placeholder(R.drawable.ic_refresh_black_24dp).error(R.drawable.ic_error_black_24dp))
            .into(ivPoster)
        btnSelengkapnya.setOnClickListener{
            val intent = Intent(context, DetailTontonanActivity::class.java)
            intent.putExtra("Dataku", tontonan)
            context.startActivity(intent)
        }
    }
}
