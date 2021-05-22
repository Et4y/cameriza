package com.etch.camera.framework

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.etch.camera.databinding.FragmentEditImageBinding
import java.io.File


class EditImageFragment : Fragment() {

    private var _binding: FragmentEditImageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        receiveData()

    }

    private fun receiveData() {
        val uri = arguments?.getString("uri")


        binding.cropView.setUri(Uri.parse(File(uri!!).toString()))

//        val imageUri = binding.cropImageView.imageUri

//        Log.i("sdsdsd", "receiveData: " + imageUri)


    }

    protected fun CropImage(uri: Uri) {
        try {
            val intent = Intent("com.android.camera.action.CROP")
            intent.setDataAndType(uri, "image/*")
            intent.putExtra("crop", "true")
            intent.putExtra("outputX", 200)
            intent.putExtra("outputY", 200)
            intent.putExtra("aspectX", 3)
            intent.putExtra("aspectY", 4)
            intent.putExtra("scaleUpIfNeeded", true)
            intent.putExtra("return-data", true)
            startActivityForResult(intent, 5)
        } catch (e: ActivityNotFoundException) {
        }
    }

}