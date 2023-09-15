package everyone_should.play_in.bitGames.applicationParts.smallerParts.web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import everyone_should.play_in.bitGames.R
import everyone_should.play_in.bitGames.helpers.smallerPartInflater
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.webkit.CookieManager
import android.webkit.ValueCallback
import android.webkit.WebView
import androidx.activity.result.contract.ActivityResultContracts
import everyone_should.play_in.bitGames.helpers.CookieManagerSetter
import everyone_should.play_in.bitGames.helpers.OnActivityResultAction
import everyone_should.play_in.bitGames.helpers.RequestPermissionLauncherAction
import everyone_should.play_in.bitGames.helpers.WebSettingsSetter
import everyone_should.play_in.bitGames.webView.WCClient
import everyone_should.play_in.bitGames.webView.WVClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException

class WebSmallerPart(private val link: String): Fragment() {
    var currentWebView: WebView? = null
    var mainFilePathCallback: ValueCallback<Array<Uri>>? = null
    var filePathCallback: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return smallerPartInflater(R.layout.smaller_part_web_web, inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            val tempWebView: WebView = view.findViewWithTag("web_web_webView")
            currentWebView = tempWebView
            sett()
            tempWebView.loadUrl(link)
        }
    }

    fun sett() {
        WebSettingsSetter(currentWebView!!.settings).setUserAgent(currentWebView!!.settings.userAgentString)
        CookieManagerSetter(CookieManager.getInstance())
        currentWebView!!.webChromeClient = WCClient(this)
        currentWebView!!.webViewClient = WVClient()
    }

    val requestPermissionLauncher = registerForActivityResult (
        ActivityResultContracts.RequestPermission()
    ) { isResultGranted: Boolean? ->
        Log.i("Request permission launcher", "isResultGranted: $isResultGranted")
        CoroutineScope(Dispatchers.IO).launch {
            RequestPermissionLauncherAction(this@WebSmallerPart).endAction()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        OnActivityResultAction(this, resultCode, data).setMainFilePathCallback()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        currentWebView?.saveState(outState)
    }
}