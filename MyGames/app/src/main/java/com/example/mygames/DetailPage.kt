package com.example.mygames

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView


class DetailPage : AppCompatActivity() {

    private lateinit var backButton: Button
    private lateinit var favButton: Button
    private lateinit var gameImage: ImageView
    private lateinit var desc: TextView
    private lateinit var gameName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_page)
        bindViews()
        initListeners(this)
    }

    fun bindViews() {
        backButton = findViewById(R.id.game_pg_button)
        favButton = findViewById(R.id.fav_pg_button)
        if (isFaved()){favButton.text = "Favourıted"}

        gameImage = findViewById(R.id.gameImage)
        gameImage.setImageResource(intent.extras?.get("gameImgSrc") as Int)

        desc = findViewById(R.id.description)
        desc.text = intent.extras?.get("gameDescription").toString()

        gameName = findViewById(R.id.gameName)
        gameName.text = intent.extras?.get("gameName").toString()

    }

    fun initListeners(context: Context){

        favButton.setOnClickListener {
            if(!isFaved()){
                favButton.text = "Favourıted"
                favGameList.add( gameList[intent.extras?.get("itemIndex") as Int])
            }
        }

        backButton.setOnClickListener {
            val newIntent = Intent(context, MainActivity::class.java)
            context.startActivity(newIntent)
        }
    }

    // Check the is game favourited
    fun isFaved(): Boolean {
        return favGameList.contains(gameList[intent.extras?.get("itemIndex") as Int])
    }
}