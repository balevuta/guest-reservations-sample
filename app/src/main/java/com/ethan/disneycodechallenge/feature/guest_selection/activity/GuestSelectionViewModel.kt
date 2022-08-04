package com.ethan.disneycodechallenge.feature.guest_selection.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ethan.disneycodechallenge.domain.usecase.GuestUseCase
import com.ethan.disneycodechallenge.feature.guest_selection.state.GuestViewState
import com.ethan.disneycodechallenge.utils.SAMPLE_DATA_LONG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuestSelectionViewModel @Inject constructor(
    private val guestUseCase: GuestUseCase,
) : ViewModel() {

    val detailViewState = MutableLiveData<GuestViewState>()

    fun saveGuests() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = guestUseCase.saveGuests(SAMPLE_DATA_LONG)
            when {
                result.isFailure -> {
                    detailViewState.postValue(GuestViewState.Error(throwable = result.exceptionOrNull()))
                }
                result.isSuccess -> {
                    result.getOrNull()?.let {
                        detailViewState.postValue(GuestViewState.SaveGuestsSucceed)
                    }
                }
            }
        }
    }
}