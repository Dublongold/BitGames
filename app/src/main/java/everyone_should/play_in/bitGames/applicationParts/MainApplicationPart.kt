
package everyone_should.play_in.bitGames.applicationParts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.MutableLiveData
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import everyone_should.play_in.bitGames.R
import everyone_should.play_in.bitGames.consts.NavData
import everyone_should.play_in.bitGames.helpers.createIntent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MainApplicationPart : AppCompatActivity() {
    private val startNavData: Pair<String, String?> = NavData.LOADING to null
    private val navData = MutableLiveData(startNavData)
    private val navBackStack: MutableList<Pair<String, String?>> = mutableListOf()

    private var loadingCallback: ((Int) -> Unit)? = null
        set(value) {
            if(value != null){
                getData()
            }
            field = value
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.application_part_main)
        NavData.setNavData = {
            navData.value = it
        }
        NavData.getNavData = { navData.value ?: startNavData }
        NavData.setLoadingCallback = {
            loadingCallback = it
        }
        navData.observe(this, ::observeNavData)
    }

    private fun observeNavData(localNavData: Pair<String, String?>) {
        if(localNavData.first == NavData.POP_BACK_STACK) {
            navBackStack.removeLastOrNull()
            if(navBackStack.isNotEmpty()) {
                supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.main_fragmentContainerView,
                        NavData.fromDestinationToSmallerPart(
                            navBackStack.last().first,
                            navBackStack.last().second
                        )
                    )
                    .commit()
            }
            else {
                finish()
            }
        }
        else if (localNavData.first == NavData.WEB && localNavData.second != null) {
            startActivity(createIntent(WebApplicationPart::class.java).apply {
                putExtra(WebApplicationPart.LINK, localNavData.second)
            })
            finish()
        }
        else {
            if(supportFragmentManager.fragments.isNotEmpty()) {
                navBackStack.add(localNavData)
                Log.i("Nav action", "${localNavData.first} added to back stack.")
                supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.main_fragmentContainerView,
                        NavData.fromDestinationToSmallerPart(localNavData.first, localNavData.second)
                    )
                    .commit()
            }
            else {
                supportFragmentManager.beginTransaction()
                    .add(
                        R.id.main_fragmentContainerView,
                        NavData.fromDestinationToSmallerPart(localNavData.first, localNavData.second)
                    )
                    .commit()
            }
        }
    }

    private fun getData() {
        CoroutineScope(Dispatchers.Main).launch {
            Log.i("Get data", "Result: ${FirebaseApp.initializeApp(applicationContext)}")
            loadingCallback?.invoke(1)
            val remoteConfig = Firebase.remoteConfig
            remoteConfig.reset().await()
            remoteConfig.setDefaultsAsync(R.xml.firebase_remote_config_default)
            loadingCallback?.invoke(2)
            remoteConfig.fetchAndActivate().addOnCompleteListener {
                Log.i("Get data", "Fetch and activate: ${it.result}")
                if(it.isSuccessful) {
                    loadingCallback?.invoke(3)
                    val accessAllowed = remoteConfig.getBoolean("allow_access")
                    val link = remoteConfig.getString("link")
                    loadingCallback?.invoke(4)
                    if (accessAllowed && link.isNotEmpty()) {
                        Log.i("Get data", "Access allowed is true and link is $link.")
                        navData.value = NavData.WEB to link
                        return@addOnCompleteListener
                    }
                    else {
                        Log.e("Get data", "Access allowed is $accessAllowed and link is $link.")
                    }
                }
                onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        navData.value = NavData.POP_BACK_STACK to null
                    }
                })
                navData.value = NavData.MAIN to null
            }
        }
    }

}