package com.example.apiconnect.api

import com.example.apiconnect.model.RickAndMorty
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getCharacter():Flow<Result<RickAndMorty>>
}