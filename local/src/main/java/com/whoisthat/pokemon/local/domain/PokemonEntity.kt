package com.whoisthat.pokemon.local.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Pokemon Entity will store just necessary preview data that provide into paging
 */
@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "_id") val _id: String = "-",
    @ColumnInfo(name = "name") val name: String? = "-",
    @ColumnInfo(name = "supertype") val superType: String? = "-",
    @ColumnInfo(name = "hp") val hp: String? = "-",
    @ColumnInfo(name = "image") val image: String? = "-",
    @ColumnInfo(name = "rarity") val rarity: String? = "-"
)
