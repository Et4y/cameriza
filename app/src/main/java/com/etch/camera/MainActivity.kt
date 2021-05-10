package com.etch.camera

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.etch.camera.framework.Cameriza

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val intent = Intent(this , Cameriza::class.java)
        intent.putExtra(CamerizaConst.IS_SINGLE_SELECTION , false)

        startActivityForResult(Intent(intent), 5)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val stringExtra = data?.getStringArrayListExtra("selectedImages")

        Log.i("ssddsds", "onActivityResult: " + stringExtra?.size)
    }

}