package com.ethan.disneycodechallenge.feature.guest_selection.state

import com.ethan.disneycodechallenge.domain.model.GuestModel

sealed class GuestViewState {

    object Loading : GuestViewState()

    object SaveGuestsSucceed : GuestViewState()

    class EnableContinueButton(val enabled: Boolean) : GuestViewState()

    object ReservationNeededAlert : GuestViewState()

    object ContinueToConfirmation : GuestViewState()

    object ContinueToConflict : GuestViewState()

    class GetGuestsSucceed(
        val guestsHaveReservations: List<GuestModel>,
        val guestsNeedReservations: List<GuestModel>
    ) : GuestViewState()

    class Error(val throwable: Throwable?) : GuestViewState()
}