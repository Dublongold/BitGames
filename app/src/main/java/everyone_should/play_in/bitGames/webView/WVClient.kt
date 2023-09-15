package everyone_should.play_in.bitGames.webView

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class WVClient : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        val uri = request.url.toString()
        return if (uri.contains("/")) {
            Log.i("shouldOverrideUrlLoading", uri)
            !uri.contains("http")
        } else true
    }
    override fun onReceivedLoginRequest(
        view: WebView,
        realm: String,
        account: String?,
        args: String
    ) {
        super.onReceivedLoginRequest(view, realm, account, args)
    }
}