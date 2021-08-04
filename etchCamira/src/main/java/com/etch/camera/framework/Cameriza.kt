package com.etch.camera.framework

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.webkit.MimeTypeMap
import androidx.appcompat.app.AppCompatActivity
import com.etch.camera.R
import com.etch.camera.data.FileModel
import com.etch.camera.util.CamerizaConst.Companion.IS_SINGLE_SELECTION
import com.etch.camera.util.initFragment
import dagger.hilt.android.AndroidEntryPoint
import java.io.File


const val KEY_EVENT_ACTION = "key_event_action"
const val KEY_EVENT_EXTRA = "key_event_extra"
val EXTENSION_WHITELIST = arrayOf("JPG")

@AndroidEntryPoint
class Cameriza : AppCompatActivity() {


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

        fun getOutputDirectory(context: Context): File {
            val appContext = context.applicationContext
            val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
                File(it, appContext.resources.getString(R.string.app_name)).apply { mkdirs() }
            }
            return if (mediaDir != null && mediaDir.exists())
                mediaDir else appContext.filesDir
        }


    }


    private fun getExternalPDFFileList(): ArrayList<FileModel> {
        val cr = contentResolver
        val uri = MediaStore.Files.getContentUri("external")

        val projection =
            arrayOf(
                MediaStore.Files.FileColumns._ID,
                MediaStore.Files.FileColumns.DISPLAY_NAME
            )

        val selection = (
                MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                        + MediaStore.Files.FileColumns.MEDIA_TYPE
                )
        val selectionArgs: Array<String>? = null

        val selectionMimeType = MediaStore.Files.FileColumns.MIME_TYPE + "=?"
        val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension("pdf")
        val selectionArgsPdf = arrayOf(mimeType)
        val cursor = cr.query(uri, projection, selectionMimeType, selectionArgsPdf, null)!!
        val uriList: ArrayList<FileModel> = ArrayList()

        cursor.moveToFirst()

        val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)
        val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)

        while (!cursor.isAfterLast) {
            val columnIndex = cursor.getColumnIndex(projection[0])
            val fileId = cursor.getLong(idColumn)
            val fileUri = Uri.parse("$uri/$fileId")
            val displayName = cursor.getString(nameColumn)
            uriList.add(FileModel(displayName, fileUri))
            cursor.moveToNext()
        }
        cursor.close()
        return uriList
    }


}