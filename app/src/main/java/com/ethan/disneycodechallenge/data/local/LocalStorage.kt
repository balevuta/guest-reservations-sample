package com.ethan.disneycodechallenge.data.local

import com.ethan.disneycodechallenge.domain.model.GuestModel

interface LocalStorage {

    fun getGuestList(): List<GuestModel>

    fun saveLatestGuests(jsonData: String): Boolean
}
