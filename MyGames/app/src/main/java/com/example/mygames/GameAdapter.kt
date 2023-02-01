package com.example.mygames

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


class GameAdapter(
    val context: Context,
    games: ArrayList<GameCardModel>
    ): RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private val games: ArrayList<GameCardModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.game_card, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameAdapter.GameViewHolder, position: Int) {
        val model: GameCardModel = games[position]
        holder.gamePic.setImageResource(model.image)
        holder.title.setText(model.title)
        holder.score.setText(model.rating.toString())
        holder.scoredBy.setText(model.scoredBy)
        holder.category.setText(model.category)
        // Change background color when game is clicked
        if (gameList.elementAt(position).isClicked){
            holder.gameCard.setBackgroundColor(ContextCompat.getColor(context, R.color.backColor))
        }
    }

    override fun getItemCount(): Int {
        return games.size
    }

    // View holder class for initializing of your views such as TextView and Imageview.
    inner class GameViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val gamePic: ImageView = itemView.findViewById(R.id.gameImg)
        val title: TextView = itemView.findViewById(R.id.title)
        val score: TextView = itemView.findViewById(R.id.some_id)
        val scoredBy: TextView = itemView.findViewById(R.id.metacritic)
        val category: TextView = itemView.findViewById(R.id.number)
        val gameCard: ConstraintLayout = itemView.findViewById(R.id.cardConstraint)

        init {
            itemView.setOnClickListener {
                games[adapterPosition].isClicked = true
                val intent = Intent(context, DetailPage::class.java)
                intent.putExtra("itemIndex", adapterPosition)
                intent.putExtra("gameImgSrc", games[adapterPosition].image)
                intent.putExtra("gameDescription", games[adapterPosition].desc)
                intent.putExtra("gameName", games[adapterPosition].title)
                context.startActivity(intent)
            }
        }
    }

    // Constructor
    init {
        this.games = games
    }
}