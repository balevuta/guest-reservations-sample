package com.ethan.disneycodechallenge.di

import com.ethan.disneycodechallenge.data.GuestRepositoryImpl
import com.ethan.disneycodechallenge.data.local.LocalStorage
import com.ethan.disneycodechallenge.data.local.LocalStorageImpl
import com.ethan.disneycodechallenge.domain.repository.GuestRepository
import com.ethan.disneycodechallenge.domain.usecase.GuestUseCase
import com.ethan.disneycodechallenge.domain.usecase.GuestUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class GuestBindingModule {

    @Binds
    abstract fun bindGuestUseCase(useCaseImpl: GuestUseCaseImpl): GuestUseCase

    @Binds
    abstract fun bindGuestRepository(
        guestRepositoryImpl: GuestRepositoryImpl
    ): GuestRepository

    @Binds
    abstract fun bindLocalStorage(storageImpl: LocalStorageImpl): LocalStorage
}
