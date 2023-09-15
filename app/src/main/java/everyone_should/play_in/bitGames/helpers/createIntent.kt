package everyone_should.play_in.bitGames.helpers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.createIntent(intentClass: Class<*>) = Intent(this, intentClass)