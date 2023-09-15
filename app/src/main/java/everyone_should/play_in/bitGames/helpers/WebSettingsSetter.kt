package everyone_should.play_in.bitGames.helpers

import android.util.Log
import android.webkit.WebSettings
import everyone_should.play_in.bitGames.consts.WebSettingsValues

class WebSettingsSetter(private val settings: WebSettings) {
    init {
        settings.allowContentAccess = WebSettingsValues.DEFAULT_BOOLEAN
        settings.allowFileAccess = WebSettingsValues.DEFAULT_BOOLEAN
        settings.javaScriptCanOpenWindowsAutomatically = WebSettingsValues.DEFAULT_BOOLEAN
        settings.allowFileAccessFromFileURLs = WebSettingsValues.DEFAULT_BOOLEAN
        settings.domStorageEnabled = WebSettingsValues.DEFAULT_BOOLEAN
        settings.javaScriptEnabled = WebSettingsValues.DEFAULT_BOOLEAN
        settings.databaseEnabled = WebSettingsValues.DEFAULT_BOOLEAN
        settings.allowUniversalAccessFromFileURLs = WebSettingsValues.DEFAULT_BOOLEAN
        settings.useWideViewPort = WebSettingsValues.DEFAULT_BOOLEAN
        settings.loadWithOverviewMode = WebSettingsValues.DEFAULT_BOOLEAN
        Log.i("Web settings setter", "Boolean settings were set.")
        settings.mixedContentMode = WebSettingsValues.DEFAULT_INTEGER
        settings.cacheMode = WebSettingsValues.DEFAULT_INTEGER - 1
        Log.i("Web settings setter", "Integer settings were set.")
    }

    fun setUserAgent(userAgent: String) {
        settings.userAgentString = userAgent.replace("; wv", "")
        Log.i("Web settings setter", "User agent was set from default to ${settings.userAgentString}.")
    }
}