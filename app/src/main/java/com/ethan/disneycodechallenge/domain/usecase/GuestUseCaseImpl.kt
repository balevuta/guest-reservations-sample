package com.ethan.disneycodechallenge.domain.usecase

import com.ethan.disneycodechallenge.domain.model.GuestModel
import com.ethan.disneycodechallenge.domain.repository.GuestRepository
import javax.inject.Inject

class GuestUseCaseImpl @Inject constructor(
    private val repository: GuestRepository
) : GuestUseCase {

    override suspend fun saveGuests(jsonData: String): Result<Boolean> {
        return repository.saveGuests(jsonData)
    }

    override suspend fun getGuests(): Result<List<GuestModel>> {
        return repository.getGuests()
    }
}
