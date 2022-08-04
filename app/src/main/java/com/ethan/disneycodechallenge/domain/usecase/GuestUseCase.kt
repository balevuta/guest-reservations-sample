package com.ethan.disneycodechallenge.domain.usecase

import com.ethan.disneycodechallenge.domain.model.GuestModel

interface GuestUseCase {

    suspend fun saveGuests(jsonData: String): Result<Boolean>

    suspend fun getGuests(): Result<List<GuestModel>>
}
