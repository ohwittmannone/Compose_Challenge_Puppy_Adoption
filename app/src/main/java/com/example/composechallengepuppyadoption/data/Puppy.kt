package com.example.composechallengepuppyadoption.data

import androidx.annotation.DrawableRes
import com.example.composechallengepuppyadoption.R

data class Puppy(
    val name: String,
    @DrawableRes val image: Int,
    val description: String
)

val listOfPuppies = listOf(
    Puppy("Monty", R.drawable.monty, "A classic good boy"),
    Puppy("Romeo", R.drawable.romeo, "A heart breaker"),
    Puppy("Thibo", R.drawable.thibo, "Bruther of goodest boy"),
    Puppy("Wolfgang", R.drawable.wolfgang, "goodest boy")
)