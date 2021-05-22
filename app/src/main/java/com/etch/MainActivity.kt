package com.etch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.etch.camera.CamerizaConst
import com.etch.camera.R
import com.etch.camera.framework.Cameriza



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.etch.R.layout.activity_main2)

        val intent = Intent(this,Cameriza::class.java)
        intent.putExtra(CamerizaConst.IS_SINGLE_SELECTION, true)

        startActivityForResult(intent , 5)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val stringArrayListExtra = data?.getStringArrayListExtra(CamerizaConst.SELECTED_IMAGES_PATH)


    }


}