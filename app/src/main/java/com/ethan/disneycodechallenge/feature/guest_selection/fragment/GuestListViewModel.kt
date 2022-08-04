package com.ethan.disneycodechallenge.feature.guest_selection.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ethan.disneycodechallenge.domain.model.GuestModel
import com.ethan.disneycodechallenge.domain.usecase.GuestUseCase
import com.ethan.disneycodechallenge.feature.guest_selection.state.GuestViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuestListViewModel @Inject constructor(
    private val guestUseCase: GuestUseCase,
) : ViewModel() {

    val detailViewState = MutableLiveData<GuestViewState>()

    private var listCheckedGuests = arrayListOf<GuestModel>()

    fun getGuests() {
        detailViewState.postValue(GuestViewState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            val result = guestUseCase.getGuests()
            when {
                result.isFailure -> {
                    detailViewState.postValue(GuestViewState.Error(throwable = result.exceptionOrNull()))
                }
                result.isSuccess -> {
                    result.getOrNull()?.let { guests ->
                        detailViewState.postValue(
                            GuestViewState.GetGuestsSucceed(
                                guestsHaveReservations = guests.filter { it.reservation },
                                guestsNeedReservations = guests.filter { !it.reservation }
                            )
                        )
                    }
                }
            }
        }
    }

    fun onGuestItemChecked(guestItem: GuestModel, checked: Boolean) {
        if (checked) {
            listCheckedGuests.add(guestItem)
        } else {
            listCheckedGuests.remove(guestItem)
        }
        detailViewState.postValue(GuestViewState.EnableContinueButton(enabled = listCheckedGuests.isNotEmpty()))
    }

    fun onContinueProcess() {
        if (listCheckedGuests.none { it.reservation }) {
            detailViewState.postValue(GuestViewState.ReservationNeededAlert)
        } else if (listCheckedGuests.none { !it.reservation }) {
            detailViewState.postValue(GuestViewState.ContinueToConfirmation)
        } else {
            detailViewState.postValue(GuestViewState.ContinueToConflict)
        }
    }
}