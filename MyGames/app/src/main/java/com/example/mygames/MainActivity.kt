package com.example.mygames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mygames.databinding.ActivityMainBinding
import kotlin.collections.ArrayList

var gameList: ArrayList<GameCardModel> = arrayListOf(
    GameCardModel(R.drawable.gta5, "Grand Theft Auto V", 96, "metacritic",
    "Action, shooter", "Rockstar Games went bigger, since their previous installment of the series. " +
            "You get the complicated and realistic world-building from Liberty City of GTA4 in the setting of lively " +
            "and diverse Los Santos, from an old fan favorite GTA San Andreas. 561 different vehicles (including every " +
            "transport you can operate)...."),
    GameCardModel(R.drawable.portal2, "Portal 2", 95, "metacritic", "Action, shooter",
        "Rockstar Games went bigger, since their previous installment of the series. You get the complicated and realistic " +
                "world-building from Liberty City of GTA4 in the setting of lively and diverse Los Santos, from an old fan favorite" +
                " GTA San Andreas. 561 different vehicles (including every transport you can operate)...."),
    GameCardModel(R.drawable.left4dead2, "Left 4 Dead 2", 89, "metacritic", "Action," +
            " shooter", "Rockstar Games went bigger, since their previous installment of the series. You get the " +
            "complicated and realistic world-building from Liberty City of GTA4 in the setting of lively and diverse Los Santos, " +
            "from an old fan favorite GTA San Andreas. 561 different vehicles (including every transport you can operate)...."),
    GameCardModel(R.drawable.witcher3, "The Witcher 3: Wild Hunt", 89, "metacritic", "Action, " +
            "shooter", "Rockstar Games went bigger, since their previous installment of the series. You get the complicated and realistic " +
            "world-building from Liberty City of GTA4 in the setting of lively and diverse Los Santos, from an old fan favorite GTA San Andreas." +
            " 561 different vehicles (including every transport you can operate)....")
)
var favGameList: ArrayList<GameCardModel> = ArrayList<GameCardModel>()
var tempList:ArrayList<GameCardModel> = ArrayList<GameCardModel>()

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Block adding gameList to tempList more than one
        if (tempList.size != gameList.size){
            tempList.addAll(gameList)
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Start activity with games fragment initially
        replaceFragment(Games())
        // Change the tabs
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.game_nav -> replaceFragment(Games())
                R.id.fav_nav -> replaceFragment(Favourites())
                else -> {
                }
            }
            true
        }
    }
    // Switch between fragments
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentView, fragment)
        fragmentTransaction.commit()
    }
}