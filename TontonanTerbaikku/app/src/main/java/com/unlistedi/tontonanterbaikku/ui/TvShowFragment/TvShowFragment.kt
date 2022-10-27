package com.unlistedi.tontonanterbaikku.ui.TvShowFragment


import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unlistedi.tontonanterbaikku.R
import com.unlistedi.tontonanterbaikku.Tontonan
import com.unlistedi.tontonanterbaikku.TontonanAdapter

class TvShowFragment : Fragment() {

    var dataTvShowNama = arrayOf<String>()
    var dataTvShowRilis = arrayOf<String>()
    var dataTvShowSkor = arrayOf<String>()
    var dataTvShowDeskripsi = arrayOf<String>()
    var dataTvShowPoster = arrayOf<String>()
    lateinit var rvTvShowList : RecyclerView
    lateinit var tontonans : ArrayList<Tontonan>
    lateinit var context : Activity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tv_show, container, false)

        dataGetter()
        dataGenerator()

        rvTvShowList = view.findViewById(R.id.rvTvShowList)
        rvTvShowList.layoutManager = LinearLayoutManager(context)
        val tontonanAdapter = TontonanAdapter(tontonans, context)
        rvTvShowList.adapter = tontonanAdapter

        return view
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        if (activity != null) {
            context = activity
        }
    }

    fun dataGenerator() {
        tontonans = ArrayList()
        for (i in 0  until dataTvShowNama.size){
            val movie = Tontonan(
                dataTvShowNama.get(i),
                dataTvShowRilis[i],
                dataTvShowSkor[i],
                dataTvShowDeskripsi[i],
                resources.getIdentifier(dataTvShowPoster[i], "drawable", context.packageName)
            )
            tontonans.add(movie)
            Log.d("Main", i.toString())
        }

    }
    fun dataGetter() {
        dataTvShowNama = resources.getStringArray(R.array.dataTvShowNama)
        dataTvShowRilis = resources.getStringArray(R.array.dataTvShowRilis)
        dataTvShowSkor = resources.getStringArray(R.array.dataTvShowRating)
        dataTvShowDeskripsi = resources.getStringArray(R.array.dataTvShowDeskripsi)
        dataTvShowPoster = resources.getStringArray(R.array.dataTvShowPoster)
    }

}
