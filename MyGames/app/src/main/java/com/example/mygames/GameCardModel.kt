package com.example.mygames

class GameCardModel (image: Int, title: String, rating: Int, scoredBy: String, category: String, desc: String){
    val image: Int
    val title: String
    val rating: Int
    val scoredBy: String
    val category: String
    val desc: String
    var isClicked: Boolean = false

    init {
        this.image = image
        this.title = title
        this.rating = rating
        this.scoredBy = scoredBy
        this.category = category
        this.desc = desc
    }
}