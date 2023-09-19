package com.bibitstarzrz.csnoslo.tsapp.applicationParts.smallerParts.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.bibitstarzrz.csnoslo.tsapp.R
import com.bibitstarzrz.csnoslo.tsapp.consts.NavData
import com.bibitstarzrz.csnoslo.tsapp.helpers.smallerPartInflater

class MainSmallerPart: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return smallerPartInflater(R.layout.smaller_part_main_main, inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            findViewById<ImageButton>(R.id.main_privacyPolicyButton).setOnClickListener {
                NavData.setNavData?.invoke(NavData.PRIVACY_POLICY to null)
            }
            fun setPreviewClickListener(previewId: Int, title: String) {
                findViewById<ConstraintLayout>(previewId).setOnClickListener {
                    NavData.setNavData?.invoke(NavData.POST to title)
                }
            }
            setPreviewClickListener(R.id.main_main_constraintLayout_wildSpin, getString(R.string.main_wild_spin))
            setPreviewClickListener(R.id.main_main_constraintLayout_booksGiza, getString(R.string.main_books_giza))
            setPreviewClickListener(R.id.main_main_constraintLayout_royalLotus, getString(R.string.main_royal_lotus))
            setPreviewClickListener(R.id.main_main_constraintLayout_blackJack, getString(R.string.main_blackjack))

            setPreviewClickListener(R.id.main_main_constraintLayout_wolfGold, getString(R.string.main_wolf_gold))
            setPreviewClickListener(R.id.main_main_constraintLayout_fiveLions, getString(R.string.main_five_lions))
            setPreviewClickListener(R.id.main_main_constraintLayout_elvisFrog, getString(R.string.main_elvis_frog))
            setPreviewClickListener(R.id.main_main_constraintLayout_buffalo, getString(R.string.main_buffalo))
        }
    }
}