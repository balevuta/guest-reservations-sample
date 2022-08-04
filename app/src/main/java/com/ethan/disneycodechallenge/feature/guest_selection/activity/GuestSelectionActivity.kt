package com.ethan.disneycodechallenge.feature.guest_selection.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ethan.disneycodechallenge.R
import com.ethan.disneycodechallenge.databinding.ActivityGuestSelectionBinding
import com.ethan.disneycodechallenge.feature.guest_selection.fragment.GuestListFragment
import com.ethan.disneycodechallenge.feature.guest_selection.state.GuestViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuestSelectionActivity : AppCompatActivity() {

    private val viewModel by viewModels<GuestSelectionViewModel>()

    private lateinit var viewBinding: ActivityGuestSelectionBinding

    private val guestListFragment: GuestListFragment by lazy {
        GuestListFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityGuestSelectionBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, guestListFragment)
                .commitAllowingStateLoss()
        }

        viewModel.saveGuests()

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.detailViewState.observe(this) {
            when (it) {
                is GuestViewState.Error -> {
                    Toast.makeText(this, it.throwable?.message, Toast.LENGTH_SHORT)
                        .show()
                }
                is GuestViewState.SaveGuestsSucceed -> {
                    guestListFragment.loadData()
                }
                else -> Unit
            }
        }
    }
}