package com.ethan.disneycodechallenge.data

import com.ethan.disneycodechallenge.data.local.LocalStorage
import com.ethan.disneycodechallenge.domain.model.GuestModel
import com.ethan.disneycodechallenge.domain.repository.GuestRepository
import javax.inject.Inject

class GuestRepositoryImpl @Inject constructor(
    private val guestLocalStorage: LocalStorage,
) : GuestRepository {

    override suspend fun saveGuests(jsonData: String): Result<Boolean> {
        return try {
            Result.success(guestLocalStorage.saveLatestGuests(jsonData))
        } catch (ex: Exception) {
            Result.failure(exception = ex)
        }
    }

    override suspend fun getGuests(): Result<List<GuestModel>> {
        return try {
            Result.success(guestLocalStorage.getGuestList())
        } catch (ex: Exception) {
            Result.failure(exception = ex)
        }
    }
}
