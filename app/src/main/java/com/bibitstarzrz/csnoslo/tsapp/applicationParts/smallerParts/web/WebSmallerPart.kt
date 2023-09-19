package com.bibitstarzrz.csnoslo.tsapp.applicationParts.smallerParts.web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bibitstarzrz.csnoslo.tsapp.R
import com.bibitstarzrz.csnoslo.tsapp.helpers.smallerPartInflater
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.webkit.CookieManager
import android.webkit.ValueCallback
import android.webkit.WebView
import androidx.activity.result.contract.ActivityResultContracts
import com.bibitstarzrz.csnoslo.tsapp.helpers.CookieManagerSetter
import com.bibitstarzrz.csnoslo.tsapp.helpers.OnActivityResultAction
import com.bibitstarzrz.csnoslo.tsapp.helpers.RequestPermissionLauncherAction
import com.bibitstarzrz.csnoslo.tsapp.helpers.WebSettingsSetter
import com.bibitstarzrz.csnoslo.tsapp.webView.WCClient
import com.bibitstarzrz.csnoslo.tsapp.webView.WVClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
            webViewPropertiesSetter()
            tempWebView.loadUrl(link)
        }
    }

    fun webViewPropertiesSetter() {
        WebSettingsSetter(currentWebView!!.settings).setUserAgent(currentWebView!!.settings.userAgentString)
        CookieManagerSetter(CookieManager.getInstance()).setAcceptThirdPartyCookies(currentWebView!!)
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

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        @Suppress("DEPRECATION")
        super.onActivityResult(requestCode, resultCode, data)
        OnActivityResultAction(this, resultCode, data).setMainFilePathCallback()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        currentWebView?.saveState(outState)
    }
}