package everyone_should.play_in.bitGames.applicationParts.smallerParts.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.allViews
import androidx.fragment.app.Fragment
import everyone_should.play_in.bitGames.R
import everyone_should.play_in.bitGames.applicationParts.MainApplicationPart
import everyone_should.play_in.bitGames.consts.NavData
import everyone_should.play_in.bitGames.helpers.smallerPartInflater

class LoadingSmallerPart: Fragment() {
    private lateinit var stars: Array<ImageView>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return smallerPartInflater(R.layout.smaller_part_main_loading, inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stars = view.allViews.find {it is LinearLayout}!!.allViews.filter {
            it is ImageView
        }.map { it as ImageView }.toList().toTypedArray()
        NavData.setLoadingCallback?.invoke {
            when(it) {
                1 -> {
                    stars.take(2).forEach { star ->
                        star.setImageResource(R.drawable.star_full)
                    }
                    stars.drop(2).forEach { star ->
                        star.setImageResource(R.drawable.star_empty)
                    }
                }
                2 -> {
                    stars.take(6).forEach { star ->
                        star.setImageResource(R.drawable.star_full)
                    }
                    stars.drop(6).forEach { star ->
                        star.setImageResource(R.drawable.star_empty)
                    }
                }
                3 -> {
                    stars.take(8).forEach { star ->
                        star.setImageResource(R.drawable.star_full)
                    }
                    stars.drop(8).forEach { star ->
                        star.setImageResource(R.drawable.star_empty)
                    }
                }
                4 -> {
                    stars.forEach { star ->
                        star.setImageResource(R.drawable.star_full)
                    }
                }
            }
        }
    }
}