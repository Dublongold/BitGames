package com.bibitstarzrz.csnoslo.tsapp.webView

import android.Manifest
import android.net.Uri
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.bibitstarzrz.csnoslo.tsapp.applicationParts.smallerParts.web.WebSmallerPart

class WCClient(private val smallerPart: WebSmallerPart): WebChromeClient() {
    override fun onShowFileChooser(
        webView: WebView,
        filePathCallback: ValueCallback<Array<Uri>>,
        fileChooserParams: FileChooserParams
    ): Boolean {
        smallerPart.requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        smallerPart.mainFilePathCallback = filePathCallback
        return true
    }
}