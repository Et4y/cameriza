package etch.cameraiza.framework

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint
import etch.cameraiza.R
import etch.cameraiza.framework.PermissionsFragment
import etch.cameraiza.util.initFragment
import java.io.File


const val KEY_EVENT_ACTION = "key_event_action"
const val KEY_EVENT_EXTRA = "key_event_extra"
val EXTENSION_WHITELIST = arrayOf("JPG")

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = ContextCompat.getColor(this, R.color.transparent)

        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            window.insetsController?.let {
                window.setDecorFitsSystemWindows(false)
                it.hide(WindowInsets.Type.statusBars())
            }


        initFragment(PermissionsFragment(), "")
    }


    override fun onBackPressed() {
        super.onBackPressed()
        val findFragmentById = supportFragmentManager.findFragmentById(R.id.container)
        Log.i("lsmdls", "onBackPressed: " + findFragmentById)
        if (findFragmentById is GalleryFragment) {
            findFragmentById.getAllImages()
        }

    }


    companion object {

        /** Use external media if it is available, our app's file directory otherwise */
        fun getOutputDirectory(context: Context): File {
            val appContext = context.applicationContext
            val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
                File(it, appContext.resources.getString(R.string.app_name)).apply { mkdirs() }
            }
            return if (mediaDir != null && mediaDir.exists())
                mediaDir else appContext.filesDir
        }
    }

}