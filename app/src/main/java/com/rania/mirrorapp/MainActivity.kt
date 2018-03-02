package com.rania.mirrorapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import com.codetroopers.betterpickers.datepicker.DatePickerBuilder
import com.rania.mirrorapp.db.DBHandler
import com.rania.mirrorapp.model.EntryModel
import com.rania.mirrorapp.model.Priority
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerAdapter: EntryAdapter

    private val dbHandler = DBHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupLayoutManager(recyclerView)
        setupRecyclerAdapter(recyclerView)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val dpb = DatePickerBuilder()
                    .setFragmentManager(supportFragmentManager)
                    .setStyleResId(R.style.BetterPickersDialogFragment_Light)
                    .addDatePickerDialogHandler({ _, year, month, day ->
                        val entry = EntryModel.Builder()
                                .withNameOfPerson("Random Person xxx")
                                .withPriority(Priority.NORMAL)
                                .withDayOfMonth(day)
                                .withMonth(month + 1)
                                .withYear(year)
                                .build()
                        dbHandler.insertEntry(entry)
                        recyclerAdapter.dataset.add(entry)
                        recyclerAdapter.notifyDataSetChanged()
                    })
                    .setYearOptional(false)
            dpb.show()
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
        dbHandler.insertEntry(EntryModel.Builder()
                .withNameOfPerson("Tibor")
                .withPriority(Priority.APP_USER)
                .withDayOfMonth(6)
                .withMonth(9)
                .withYear(1980)
                .build()
        )
        dbHandler.insertEntry(EntryModel.Builder()
                .withNameOfPerson("Blanka")
                .withPriority(Priority.FAVORITE)
                .withDayOfMonth(10)
                .withMonth(2)
                .withYear(1977)
                .build()
        )
        dbHandler.insertEntry(EntryModel.Builder()
                .withNameOfPerson("Random Person 1")
                .withPriority(Priority.NORMAL)
                .withDayOfMonth(5)
                .withMonth(9)
                .withYear(1980)
                .build()
        )
        dbHandler.insertEntry(EntryModel.Builder()
                .withNameOfPerson("Random Person 2")
                .withPriority(Priority.NORMAL)
                .withDayOfMonth(25)
                .withMonth(12)
                .withYear(1983)
                .build()
        )
        recyclerAdapter = EntryAdapter(this, dbHandler.getAll())
        recyclerView.adapter = recyclerAdapter
    }
}
