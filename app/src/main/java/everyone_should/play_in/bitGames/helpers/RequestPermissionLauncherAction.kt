package everyone_should.play_in.bitGames.helpers

import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.util.Log
import everyone_should.play_in.bitGames.applicationParts.smallerParts.web.WebSmallerPart
import everyone_should.play_in.bitGames.consts.Intents
import everyone_should.play_in.bitGames.consts.StartActivityForResultCodes
import kotlinx.coroutines.CoroutineScope
import java.io.File
import java.io.IOException

class RequestPermissionLauncherAction(private val smallerPart: WebSmallerPart) {
    private var intentForTakeImage: Intent
    init {
        val tempImageFile = getTempImageFile()

        val tempImageFileUri = Uri.fromFile(tempImageFile)
        smallerPart.filePathCallback = tempImageFileUri
        intentForTakeImage = Intent(Intents.INTENT_FOR_TAKE_IMAGE_ACTION)
        intentForTakeImage.putExtra(Intents.INTENT_FOR_TAKE_IMAGE_EXTRA, tempImageFileUri)
    }

    private fun getTempImageFile() = try {
        File.createTempFile(
            "file",
            ".jpg",
            smallerPart.requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        )
    } catch (exception: IOException) {
        Log.wtf("Image file", "Wtf? Why exception? Exception - $exception")
        null
    }
    fun endAction() {
        val oldIntent = createOldIntent()
        val intentForChoose = Intent(Intent.ACTION_CHOOSER).let {
            it.putExtra(Intent.EXTRA_INTENT, oldIntent)
            it.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(intentForTakeImage))
        }
        @Suppress("DEPRECATION")
        smallerPart.startActivityForResult(intentForChoose, StartActivityForResultCodes.DEFAULT)
    }

    private fun createOldIntent(): Intent {
        val oldIntent = Intent(Intent.ACTION_GET_CONTENT)
        oldIntent.addCategory(Intents.OLD_INTENT_CATEGORY)
        oldIntent.type = "${Intents.OLD_INTENT_TYPE_FIRST}/${Intents.OLD_INTENT_TYPE_SECOND}"
        return oldIntent
    }
}