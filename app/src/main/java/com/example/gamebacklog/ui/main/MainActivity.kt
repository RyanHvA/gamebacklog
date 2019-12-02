package com.example.gamebacklog.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Gamebacklog
import com.example.gamebacklog.ui.GamebacklogAdapter
import com.example.gamebacklog.ui.add.AddActivity
import com.example.gamebacklog.ui.add.EXTRA_GAMEBACKLOG
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val gamebacklogs = arrayListOf<Gamebacklog>()
    private val gamebacklogAdapter = GamebacklogAdapter(gamebacklogs)
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViews()
        initViewModel()

    }

    private fun initViews() {
        rvContent.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvContent.adapter = gamebacklogAdapter

        rvContent.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )

        btnAdd.setOnClickListener {
            onAddClick()
        }

        createItemTouchHelper().attachToRecyclerView(rvContent)
    }

    private fun onAddClick() {
        val intent = Intent(this, AddActivity::class.java)
        startActivityForResult(intent, ADD_REMINDER_REQUEST_CODE)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        // Observe games from the view model, update the list when the data is changed
        viewModel.gamebacklog.observe(this, Observer{ gamebacklogs ->
            this@MainActivity.gamebacklogs.clear()
            this@MainActivity.gamebacklogs.addAll(gamebacklogs)
            gamebacklogAdapter.notifyDataSetChanged()
        })
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
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_REMINDER_REQUEST_CODE -> {
                    val gamebacklog = data!!.getParcelableExtra<Gamebacklog>(EXTRA_GAMEBACKLOG)
                    viewModel.insertGamebacklog(gamebacklog)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    /**
     * Create a touch helper to recognize when a user swipes an item from a recycler view.
     * An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
     * and uses callbacks to signal when a user is performing these actions.
     */
    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val deletedGame = gamebacklogs.removeAt(position)
                viewModel.deleteGamebacklog(deletedGame)
            }
        }
        return ItemTouchHelper(callback)
    }

    companion object {
        const val ADD_REMINDER_REQUEST_CODE = 100
    }
}
