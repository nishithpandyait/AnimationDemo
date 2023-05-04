package com.example.animationdemo

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import com.example.animationdemo.databinding.ActivityTopupBinding


class TopupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityTopupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setResult(RESULT_OK)

        supportActionBar?.title = resources.getString(R.string.top_up)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_previous_blue)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        binding.btnDone.setOnClickListener() {
            finish()
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}