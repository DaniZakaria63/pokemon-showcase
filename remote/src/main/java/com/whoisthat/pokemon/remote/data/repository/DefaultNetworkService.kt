package com.whoisthat.pokemon.remote.data.repository

import com.whoisthat.pokemon.remote.data.source.NetworkEndpoint
import com.whoisthat.pokemon.remote.data.source.NetworkService
import javax.inject.Inject

class DefaultNetworkService @Inject constructor(
    networkEndpoint: NetworkEndpoint
) : NetworkService {

}