package com.example.gamebacklog.ui.add

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Gamebacklog

import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_add.*

const val EXTRA_GAMEBACKLOG = "EXTRA_GAMEBACKLOG"

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initView()
    }

    private fun initView() {
        btnAdd.setOnClickListener { onSaveClick() }

    }

    private fun onSaveClick() {
        if (etTitle.text.toString().isNotBlank()
            && etPlatform.text.toString().isNotBlank()
            && etDay.text.toString().isNotBlank()
            && etMonth.text.toString().isNotBlank()
            && etYear.text.toString().isNotBlank()
        ) {
            val gamebacklog = Gamebacklog(
                etTitle.text.toString(),
                etPlatform.text.toString(),
                etDay.text.toString(),
                etMonth.text.toString(),
                etYear.text.toString()
            )
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_GAMEBACKLOG, gamebacklog)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        } else {
            Toast.makeText(
                this, "Please fill in everything!"
                , Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

