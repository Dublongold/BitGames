package everyone_should.play_in.bitGames.webView

import android.Manifest
import android.net.Uri
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import everyone_should.play_in.bitGames.applicationParts.smallerParts.web.WebSmallerPart

class WCClient(private val smallerPart: WebSmallerPart): WebChromeClient() {
    override fun onShowFileChooser(
        webView: WebView,
        filePathCallback: ValueCallback<Array<Uri>>,
        fileChooserParams: WebChromeClient.FileChooserParams
    ): Boolean {
        smallerPart.requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        smallerPart.mainFilePathCallback = filePathCallback
        return true
    }
}