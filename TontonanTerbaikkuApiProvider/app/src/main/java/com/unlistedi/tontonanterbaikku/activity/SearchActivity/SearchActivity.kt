package com.unlistedi.tontonanterbaikku.activity.SearchActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import com.unlistedi.tontonanterbaikku.R
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_favorite.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        var query = intent.getStringExtra("query")

        supportActionBar?.title = applicationContext.resources.getString(R.string.search) + " \"$query\""
        viewpager_search.adapter = SearchPageAdapter(supportFragmentManager)
        tabs_search.setupWithViewPager(viewpager_main)
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
}
