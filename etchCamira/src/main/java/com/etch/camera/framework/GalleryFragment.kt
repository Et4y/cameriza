package com.etch.camera.framework

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.etch.camera.ImageViewModel
import com.etch.camera.adapter.MainImagesAdapter
import com.etch.camera.databinding.FragmentGallaryBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class GalleryFragment : Fragment() {

    @Inject
    lateinit var mainImagesAdapter: MainImagesAdapter

    private val viewModel by viewModels<ImageViewModel>()

    private var _binding: FragmentGallaryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGallaryBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initRecycler()
        imagesObserver()
    }

    private fun initRecycler() {
        binding.ivImages.adapter = mainImagesAdapter

        mainImagesAdapter.onImageClick = {

        }

        mainImagesAdapter.onCameraClick = {
            val returnIntent = Intent()
            returnIntent.putExtra("result", 0)
            activity?.setResult(Activity.RESULT_OK, returnIntent)
            activity?.finish()
        }

    }

    fun getAllImages() {
        viewModel.getAllImages()
    }

    private fun imagesObserver() {
        viewModel.imagesLiveDataObserver().observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                mainImagesAdapter.dataList.clear()
                mainImagesAdapter.dataList.add("")
                mainImagesAdapter.dataList.addAll(it.reversed() as ArrayList<String>)
                mainImagesAdapter.notifyDataSetChanged()
            }
        })
    }

}