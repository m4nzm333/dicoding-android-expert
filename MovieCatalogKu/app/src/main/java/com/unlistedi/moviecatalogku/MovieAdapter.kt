package com.unlistedi.moviecatalogku

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity

class MovieAdapter(context : Context, movies : ArrayList<Movie>) : BaseAdapter() {
    var context = context
    var movies = movies
    private val inflater= LayoutInflater.from(context)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view : View? = convertView
        if(convertView == null){
            view = LayoutInflater.from(this.context).inflate(R.layout.item_movie, parent, false)
        }
        val viewHolder : ViewHolder? = view?.let { ViewHolder(it, this.context) }
        val movie = movies.get(position)
        viewHolder?.bind(movie)
        return view
    }

    override fun getItem(position: Int): Movie {
        return movies.get(position)
    }

    override fun getItemId(position: Int) : Long{
        return position.toLong()
    }

    override fun getCount(): Int {
        return movies.size
    }

    class ViewHolder {
        var tvNama : TextView
        var tvDeskripsi : TextView
        var ivPoster : ImageView
        var btnSelengkapnya : Button
        var context  : Context
        constructor(view: View, context : Context){
            this.context =  context
            tvNama = view.findViewById(R.id.tv_nama)
            tvDeskripsi = view.findViewById(R.id.tv_deskripsi)
            ivPoster = view.findViewById(R.id.iv_poster)
            btnSelengkapnya = view.findViewById(R.id.btn_selengkapnya)
        }
        fun bind(movie : Movie){
            tvNama.text = movie.nama
            tvDeskripsi.text = movie.deskripsi
            ivPoster.setImageResource(movie.poster)
            btnSelengkapnya.setOnClickListener{
                var intent = Intent(context, DetailMovieActivity::class.java)
                intent.putExtra("movie", movie)
                context.startActivity(intent)
            }
        }
    }

}