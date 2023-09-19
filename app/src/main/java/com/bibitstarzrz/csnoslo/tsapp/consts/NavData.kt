package com.bibitstarzrz.csnoslo.tsapp.consts

import android.util.Log
import androidx.fragment.app.Fragment
import com.bibitstarzrz.csnoslo.tsapp.applicationParts.smallerParts.main.LoadingSmallerPart
import com.bibitstarzrz.csnoslo.tsapp.applicationParts.smallerParts.main.MainSmallerPart
import com.bibitstarzrz.csnoslo.tsapp.applicationParts.smallerParts.main.PostSmallerPart
import com.bibitstarzrz.csnoslo.tsapp.applicationParts.smallerParts.main.PrivacyPolicySmallerPart

object NavData {
    var getNavData: (() -> Pair<String, String?>)? = null
    var setNavData: ((Pair<String, String?>) -> Unit)? = null
    var setLoadingCallback: (((Int) -> Unit) -> Unit)? = null
    const val LOADING = "loading"
    const val MAIN = "main"
    const val POST = "post"
    const val PRIVACY_POLICY = "privacy policy"
    const val WEB = "web"
    const val POP_BACK_STACK = ""

    fun fromDestinationToSmallerPart(destination: String, string: String?): Fragment {
        return when(destination) {
            LOADING -> LoadingSmallerPart()
            MAIN -> MainSmallerPart()
            POST -> PostSmallerPart(string!!)
            PRIVACY_POLICY -> PrivacyPolicySmallerPart()
            else -> {
                Log.wtf("NavData[fDTSP]", "Destination ($destination) or string ($string) is wrong... Error thrown.")
                throw IllegalArgumentException()
            }
        }
    }
}