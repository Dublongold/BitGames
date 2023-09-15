package everyone_should.play_in.bitGames.helpers

import android.content.Intent
import android.net.Uri
import everyone_should.play_in.bitGames.applicationParts.smallerParts.web.WebSmallerPart

class OnActivityResultAction(private val smallerPart: WebSmallerPart, resultCode: Int, data: Intent?) {
    private var needSetNull = false
    init {
        val mainFilePathCallback = smallerPart.mainFilePathCallback
        needSetNull = if (mainFilePathCallback != null) {
            if (resultCode == -1) {
                val nullableDataString = getDataString(data)
                if (nullableDataString != null) {
                    val u = Uri.parse(nullableDataString)
                    mainFilePathCallback.onReceiveValue(arrayOf(u))
                } else {
                    setMainFilePathCallback(smallerPart.filePathCallback)
                }
            } else {
                mainFilePathCallback.onReceiveValue(null)
            }
            true
        }
        else false
    }

    private fun getDataString(data: Intent?) = data?.dataString

    private fun setMainFilePathCallback(callback: Uri?) {
        smallerPart.mainFilePathCallback?.apply {
            if (callback != null) {
                onReceiveValue(arrayOf(callback))
            } else {
                onReceiveValue(null)
            }
        }
    }

    fun setMainFilePathCallback() {
        if(needSetNull) {
            needSetNull = false
            smallerPart.mainFilePathCallback = null
        }
    }
}