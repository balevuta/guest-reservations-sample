package com.ethan.disneycodechallenge.feature.guest_selection.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ethan.disneycodechallenge.R
import com.ethan.disneycodechallenge.databinding.FragmentGuestListBinding
import com.ethan.disneycodechallenge.feature.confirmation.ConfirmationActivity
import com.ethan.disneycodechallenge.feature.conflict.ConflictActivity
import com.ethan.disneycodechallenge.feature.guest_selection.adapter.GuestAdapter
import com.ethan.disneycodechallenge.feature.guest_selection.state.GuestViewState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuestListFragment : Fragment() {

    private val viewModel by viewModels<GuestListViewModel>()

    private val guestHaveReservationAdapter: GuestAdapter by lazy {
        GuestAdapter(itemCheckboxCallback = { item, checked ->
            viewModel.onGuestItemChecked(item, checked)
        })
    }

    private val guestNeedReservationAdapter: GuestAdapter by lazy {
        GuestAdapter(itemCheckboxCallback = { item, checked ->
            viewModel.onGuestItemChecked(item, checked)
        })
    }

    private var _viewBinding: FragmentGuestListBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentGuestListBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setupObservers()
    }

    fun loadData() {
        viewModel.getGuests()
    }

    private fun setupObservers() {
        viewModel.detailViewState.observe(viewLifecycleOwner) {
            when (it) {
                is GuestViewState.Error -> {
                    Toast.makeText(requireContext(), it.throwable?.message, Toast.LENGTH_SHORT)
                        .show()
                }
                is GuestViewState.GetGuestsSucceed -> {
                    guestHaveReservationAdapter.submitList(it.guestsHaveReservations)
                    guestNeedReservationAdapter.submitList(it.guestsNeedReservations)
                }
                is GuestViewState.EnableContinueButton -> {
                    viewBinding.buttonContinue.isEnabled = it.enabled
                }
                is GuestViewState.ReservationNeededAlert -> {
                    Snackbar.make(
                        viewBinding.tvGuestMessageRule,
                        getString(R.string.message_reservation_needed_alert),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                is GuestViewState.ContinueToConfirmation -> {
                    startActivity(Intent(requireActivity(), ConfirmationActivity::class.java))
                }
                is GuestViewState.ContinueToConflict -> {
                    startActivity(Intent(requireActivity(), ConflictActivity::class.java))
                }
                else -> Unit
            }
        }
    }

    private fun initViews() {
        viewBinding.rvHaveReservationGuests.adapter = guestHaveReservationAdapter
        viewBinding.rvNeedReservationGuests.adapter = guestNeedReservationAdapter

        viewBinding.buttonToolbarBack.setOnClickListener { requireActivity().onBackPressed() }
        viewBinding.buttonContinue.setOnClickListener {
            viewModel.onContinueProcess()
        }

        // scroll behavior: checking the scroll position to update heading title display
        viewBinding.nestedScrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->
            if (scrollY > (viewBinding.tvGuestReservationLabel.y + viewBinding.tvGuestReservationLabel.height).toInt()
                && scrollY < viewBinding.tvGuestNeedReservationLabel.y
            ) {
                setActionBarTitle(getString(R.string.title_have_reservations))
            } else if (scrollY > (viewBinding.tvGuestNeedReservationLabel.y + viewBinding.tvGuestNeedReservationLabel.height).toInt()) {
                setActionBarTitle(getString(R.string.title_need_reservations))
            } else {
                setActionBarTitle(getString(R.string.title_select_guests))
            }
        })
    }

    private fun setActionBarTitle(title: String) {
        viewBinding.tvToolbarTitle.text = title
    }

    companion object {
        fun newInstance() = GuestListFragment()
    }
}