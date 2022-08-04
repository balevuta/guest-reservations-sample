package com.ethan.disneycodechallenge.data.local

import android.content.SharedPreferences
import com.ethan.disneycodechallenge.domain.model.GuestModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type
import javax.inject.Inject

class LocalStorageImpl @Inject constructor(
    private val preferences: SharedPreferences,
    private val moshi: Moshi
) : LocalStorage {

    override fun getGuestList(): List<GuestModel> {
        val json: String = preferences.getString(KEY_GUEST_LIST_DATA, "").orEmpty()
        if (json.isEmpty()) {
            return listOf()
        }
        val type: Type = Types.newParameterizedType(
            MutableList::class.java,
            GuestModel::class.java
        )
        return try {
            val panelListAdapter: JsonAdapter<List<GuestModel>> = moshi.adapter(type)
            val result = panelListAdapter.fromJson(json)
            result.orEmpty()
        } catch (ex: Exception) {
            emptyList()
        }
    }

    override fun saveLatestGuests(jsonData: String): Boolean {
        edit { putString(KEY_GUEST_LIST_DATA, jsonData) }
        return true
    }

    private inline fun edit(block: SharedPreferences.Editor.() -> Unit) {
        with(preferences.edit()) {
            block()
            commit()
        }
    }

    companion object {
        private const val KEY_GUEST_LIST_DATA = "KEY_GUEST_LIST_DATA"
    }
}
