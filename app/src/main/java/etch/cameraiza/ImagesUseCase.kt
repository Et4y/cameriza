package etch.cameraiza

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import javax.inject.Inject

class ImagesUseCase @Inject constructor(val context: Context) {

    fun loadImagesFromSDCard(): ArrayList<String> {
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val cursor: Cursor?
        val listOfAllImages = ArrayList<String>()
        var absolutePathOfImage: String?

        val projection = arrayOf(
            MediaStore.MediaColumns.DATA,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME
        )

        cursor = context.contentResolver.query(uri, projection, null, null, null)

        val column_index_data: Int = cursor!!.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
        cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data)
            listOfAllImages.add(absolutePathOfImage)
        }
        return listOfAllImages
    }


}