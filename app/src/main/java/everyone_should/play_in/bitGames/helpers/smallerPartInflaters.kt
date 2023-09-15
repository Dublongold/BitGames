package everyone_should.play_in.bitGames.helpers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import everyone_should.play_in.bitGames.R

fun smallerPartInflater(id: Int, inflater: LayoutInflater, container: ViewGroup?): View? = inflater.inflate(id, container, false)