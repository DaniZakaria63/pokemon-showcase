package com.whoisthat.pokemon.remote.mapper

import com.whoisthat.pokemon.domain.domain.TcgPlayer
import com.whoisthat.pokemon.remote.domain.TcgplayerModel

fun TcgplayerModel.toDomain(): TcgPlayer =
    TcgPlayer(url, updatedAt, prices?.toDomain())
