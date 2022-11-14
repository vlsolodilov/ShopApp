package com.solodilov.shopapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.solodilov.shopapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_ShopApp)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}