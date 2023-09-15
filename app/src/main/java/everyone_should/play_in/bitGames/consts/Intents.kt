package everyone_should.play_in.bitGames.consts

import android.content.Intent
import android.provider.MediaStore

object Intents {
    const val INTENT_FOR_TAKE_IMAGE_ACTION = MediaStore.ACTION_IMAGE_CAPTURE
    const val INTENT_FOR_TAKE_IMAGE_EXTRA = MediaStore.EXTRA_OUTPUT
    const val OLD_INTENT_CATEGORY = Intent.CATEGORY_OPENABLE
    const val OLD_INTENT_TYPE_FIRST = "*"
    const val OLD_INTENT_TYPE_SECOND = "*"
}