package com.etch.camera.framework

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.etch.camera.CamerizaConst.Companion.IS_SINGLE_SELECTION
import com.etch.camera.R
import com.etch.camera.util.initFragment
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


const val KEY_EVENT_ACTION = "key_event_action"
const val KEY_EVENT_EXTRA = "key_event_extra"
val EXTENSION_WHITELIST = arrayOf("JPG")

@AndroidEntryPoint
class Cameriza : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camiraiza)

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

        lateinit var currentPhotoPath: String

        fun getOutputDirectory(context: Context): File {
            // Create an image file name
            val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            return File.createTempFile(
                "JPEG_${timeStamp}_", /* prefix */
                ".jpg", /* suffix */
                storageDir /* directory */
            ).apply {
                // Save a file: path for use with ACTION_VIEW intents
                currentPhotoPath = absolutePath
            }
        }

    }


}