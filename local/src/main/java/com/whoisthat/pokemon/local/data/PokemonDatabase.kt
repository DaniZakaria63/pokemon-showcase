package com.whoisthat.pokemon.local.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.whoisthat.pokemon.local.data.dao.PokemonDao
import com.whoisthat.pokemon.local.domain.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PokemonDatabase : RoomDatabase(){
    abstract fun pokemonDao(): PokemonDao

    companion object{
        val NAME = "drifloon"
    }
}