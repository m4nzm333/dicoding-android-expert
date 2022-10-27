package com.unlistedi.tontonanterbaikkuapi

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.unlistedi.tontonanterbaikkuapi.db.DatabaseContract
import com.unlistedi.tontonanterbaikkuapi.db.TvShowHelper
import kotlinx.android.synthetic.main.layout_item_row.view.*

class TvShowFavoriteAdapter(private val context : Context, private val itemOnClickListener: (Int) -> Unit) : RecyclerView.Adapter<TvShowFavoriteAdapter.MovieHolder>() {

    var tontonans: ArrayList<Tontonan> = arrayListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): MovieHolder {
        return MovieHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.layout_item_row,
                viewGroup,
                false
            )
        )
    }

    fun setData(tontonans: ArrayList<Tontonan>){
        this.tontonans.clear()
        this.tontonans.addAll(tontonans)
        this.notifyDataSetChanged()
    }

    fun removeAt(position: Int){
        this.tontonans.removeAt(position)
        this.notifyItemRemoved(position)
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int = tontonans.size


    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bindTvShow(tontonans[position], itemOnClickListener)
    }

    class MovieHolder(private val view: View) : RecyclerView.ViewHolder(view){
        private val tvMovieNama = this.view.tvNama
        private val tvDeskripsi = this.view.tvDeskripsi
        private val ivPoster = this.view.ivPoster
        private val btnSelengkapnya = this.view.btnSelengkapnya
        private val ivFavorite = this.view.ivFavorite

        fun bindTvShow(tontonan: Tontonan, itemLongClickListener: (Int) -> Unit){
            tvMovieNama.text = tontonan.nama
            if(tontonan.deskripsi ==  ""){
                tvDeskripsi.text = "Deskripsi belum ditambahkan."
            } else {
                tvDeskripsi.text = tontonan.deskripsi
            }
            Glide.with(view.context)
                .load("https://image.tmdb.org/t/p/w200" + tontonan.poster)
                .apply(RequestOptions().placeholder(R.drawable.ic_refresh_black_24dp).error(R.drawable.ic_error_black_24dp))
                .into(ivPoster)
            btnSelengkapnya.setOnClickListener{
                val intent = Intent(view.context, DetailTontonanActivity::class.java)
                intent.putExtra("Dataku", tontonan)
                view.context.startActivity(intent)
            }
            val tvShowHelper = TvShowHelper(view.context)
            tvShowHelper.open()
            val resultQuery = tvShowHelper.queryById(tontonan.id)
            if (resultQuery.count > 0){
                ivFavorite.setImageResource(R.drawable.ic_favorite_ping_24dp)
                ivFavorite.setOnClickListener {
                    Toast.makeText(view.context, tontonan.nama + view.context.resources.getString(R.string.was_removed), Toast.LENGTH_SHORT ).show()
                    tvShowHelper.open()
                    tvShowHelper.deleteById(tontonan.id)
                    tvShowHelper.close()
                    ivFavorite.setImageResource(R.drawable.ic_favorite_grey_24dp)
                    itemLongClickListener(adapterPosition)
                }
            } else {
                ivFavorite.setImageResource(R.drawable.ic_favorite_grey_24dp)
                ivFavorite.setOnClickListener {
                    val values = ContentValues()
                    values.put(DatabaseContract.TvShowFavoriteColumns._ID, tontonan.id)
                    Toast.makeText(view.context, tontonan.nama + view.context.resources.getString(R.string.was_added), Toast.LENGTH_SHORT ).show()
                    tvShowHelper.open()
                    tvShowHelper.insert(values)
                    ivFavorite.setImageResource(R.drawable.ic_favorite_ping_24dp)
                }
            }
        }
    }
}



