package com.example.dics.base

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.dics.R

abstract class BaseActivity : AppCompatActivity(R.layout.activity_base) {

    val goBtn: Button
        get() = findViewById(R.id.goBtn)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<TextView>(R.id.title).text = this.javaClass.simpleName
    }

    override fun onBackPressed() = Unit
}
