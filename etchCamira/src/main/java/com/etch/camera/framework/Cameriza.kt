package com.etch.camera.framework

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.WindowInsets
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.etch.camera.CamerizaConst.Companion.IS_SINGLE_SELECTION
import com.etch.camera.R
import com.etch.camera.util.initFragment
import dagger.hilt.android.AndroidEntryPoint
import java.io.File


const val KEY_EVENT_ACTION = "key_event_action"
const val KEY_EVENT_EXTRA = "key_event_extra"
val EXTENSION_WHITELIST = arrayOf("JPG")

@AndroidEntryPoint
class Cameriza : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.transparent)
        setContentView(R.layout.activity_camiraiza)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            window.insetsController?.let {
                window.setDecorFitsSystemWindows(false)
                it.hide(WindowInsets.Type.statusBars())
            }


        receiveData()
        initFragment(PermissionsFragment(), "")
    }

    private fun receiveData() {
        isSingleSelection = intent.getBooleanExtra(IS_SINGLE_SELECTION, false)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        val findFragmentById = supportFragmentManager.findFragmentById(R.id.container)
        if (findFragmentById is GalleryFragment) {
            findFragmentById.getAllImages()
        }

    }


    companion object {

        var isSingleSelection = false

        /** Use external media if it is available, our app's file directory otherwise */
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun getOutputDirectory(context: Context): File {

            val appContext = context.applicationContext
//            var dir: File? = null
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//                dir = context.getExternalFilesDir(appContext.resources.getString(R.string.app_name))
//            } else {
//                dir = Environment.getExternalStoragePublicDirectory(appContext.resources.getString(R.string.app_name))
//            }
//
//            if (!dir!!.exists()) {
//                dir.mkdirs()
//            }
//
//            return dir

            val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
                File(it, appContext.resources.getString(R.string.app_name)).apply {
                    mkdirs()
                }
            }

            return if (mediaDir != null && mediaDir.exists())
                mediaDir else appContext.filesDir
        }


    }

}