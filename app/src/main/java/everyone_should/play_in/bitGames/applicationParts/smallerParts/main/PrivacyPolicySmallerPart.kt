package everyone_should.play_in.bitGames.applicationParts.smallerParts.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import everyone_should.play_in.bitGames.R
import everyone_should.play_in.bitGames.consts.NavData
import everyone_should.play_in.bitGames.helpers.smallerPartInflater

class PrivacyPolicySmallerPart: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return smallerPartInflater(R.layout.smaller_part_main_privacy_policy, inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            findViewById<ImageButton>(R.id.main_homeButton).setOnClickListener {
                NavData.setNavData?.invoke(NavData.POP_BACK_STACK to null)
            }
        }
    }
}