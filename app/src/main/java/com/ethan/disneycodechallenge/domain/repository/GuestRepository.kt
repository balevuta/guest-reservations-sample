package com.ethan.disneycodechallenge.domain.repository

import com.ethan.disneycodechallenge.domain.model.GuestModel

interface GuestRepository {

    suspend fun saveGuests(jsonData: String): Result<Boolean>

    suspend fun getGuests(): Result<List<GuestModel>>
}
