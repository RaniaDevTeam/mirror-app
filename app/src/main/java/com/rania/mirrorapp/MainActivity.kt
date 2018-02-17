package com.rania.mirrorapp

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.rania.mirrorapp.bdays.BirthdayEntryAdapter
import com.rania.mirrorapp.db.DBHandler
import com.rania.mirrorapp.model.BirthdayModel
import com.rania.mirrorapp.model.Priority
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerAdapter: BirthdayEntryAdapter

    private val dbHandler = DBHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupLayoutManager(recyclerView)
        setupRecyclerAdapter(recyclerView)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Yeah, right.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when(item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupLayoutManager(recyclerView: RecyclerView) {
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
    }

    private fun setupRecyclerAdapter(recyclerView: RecyclerView) {
        dbHandler.cleanUp()
        dbHandler.insertBirthdayEntry(BirthdayModel.Builder()
                .withNameOfPerson("Tibor")
                .withPriority(Priority.APP_USER)
                .withDayOfMonth(6)
                .withMonth(9)
                .withYear(1980)
                .build()
        )
        dbHandler.insertBirthdayEntry(BirthdayModel.Builder()
                .withNameOfPerson("Blanka")
                .withPriority(Priority.FAVORITE)
                .withDayOfMonth(10)
                .withMonth(2)
                .withYear(1977)
                .build()
        )
        dbHandler.insertBirthdayEntry(BirthdayModel.Builder()
                .withNameOfPerson("Random Person 1")
                .withPriority(Priority.NORMAL)
                .withDayOfMonth(5)
                .withMonth(9)
                .withYear(1980)
                .build()
        )
        dbHandler.insertBirthdayEntry(BirthdayModel.Builder()
                .withNameOfPerson("Random Person 2")
                .withPriority(Priority.NORMAL)
                .withDayOfMonth(25)
                .withMonth(12)
                .withYear(1983)
                .build()
        )
        recyclerAdapter = BirthdayEntryAdapter(this, dbHandler.retrieveAll())
        recyclerView.adapter = recyclerAdapter
    }
}
