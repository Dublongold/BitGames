package everyone_should.play_in.bitGames.helpers

import android.webkit.CookieManager
import android.webkit.WebView

class CookieManagerSetter(private val cookieManager: CookieManager) {
    init {
        cookieManager.setAcceptCookie(true)
    }
    fun setAcceptThirdPartyCookies(webView: WebView) {
        cookieManager.setAcceptThirdPartyCookies(webView, true)
    }
}