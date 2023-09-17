package com.whoisthat.pokemon.local.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.whoisthat.pokemon.local.domain.PokemonEntity
import kotlinx.coroutines.flow.Flow

/* tableName= pokemon */
@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon")
    fun getAllPokemonData(): List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPokemonData(pokemon: List<PokemonEntity>)
}