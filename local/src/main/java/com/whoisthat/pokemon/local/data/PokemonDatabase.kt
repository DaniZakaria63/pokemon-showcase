package com.whoisthat.pokemon.local.data

import androidx.room.RoomDatabase

abstract class PokemonDatabase : RoomDatabase(){
    companion object{
        val NAME = "drifloon"
    }
}