package com.aldezu.aocapp

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.aldezu.aocapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        AOCPresenter.init(this)
        binding.aocTitle.apply {
            text("AOC Day 1")
            textColourRes(this@MainActivity, R.color.orange)
            textFontStyle(AOCTextView.Font.DINNEXT, AOCTextView.Style.MEDIUM)
            textFontSize(20)
            background(Color.DKGRAY)
            padding(20)
        }
        AOCPresenter.viewsToAdd(this).forEach {
            binding.aocLayout.addView(it)
        }
    }
}