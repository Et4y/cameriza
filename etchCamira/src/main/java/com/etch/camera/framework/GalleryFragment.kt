package com.etch.camera.framework

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.etch.camera.util.CamerizaConst.Companion.SELECTED_IMAGES_PATH
import com.etch.camera.data.CamerizaImageModel
import com.etch.camera.viewmodel.ImageViewModel
import com.etch.camera.adapter.MainImagesAdapter
import com.etch.camera.databinding.FragmentGallaryBinding
import com.etch.camera.framework.Cameriza.Companion.isSingleSelection
import com.etch.camera.util.addFragmentWithBack
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
 class GalleryFragment : Fragment() {

    @Inject
    lateinit var mainImagesAdapter: MainImagesAdapter

    private val viewModel by viewModels<ImageViewModel>()

    private var selectedImagesPath = ArrayList<String>()

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
        handleClicks()
        handleOnImageClick()
        handleOnCameraClicked()
        imagesObserver()
    }

    private fun handleClicks() {
        binding.toolbar.tvOk.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra(SELECTED_IMAGES_PATH, selectedImagesPath)
            activity?.setResult(Activity.RESULT_OK, returnIntent)
            activity?.finish()
        }

        binding.toolbar.ivBack.setOnClickListener {
            activity?.finish()
        }

        binding.btnEdit.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("uri", selectedImagesPath[0])
            (activity as Cameriza).addFragmentWithBack(EditImageFragment(), bundle, "sdsd")
        }

    }

    private fun initRecycler() {
        binding.ivImages.adapter = mainImagesAdapter
        mainImagesAdapter.isSingleSelection = isSingleSelection
    }

    private fun handleOnImageClick() {
        mainImagesAdapter.onImageClick = {

            selectedImagesPath.clear()

            for (i in it) {
                selectedImagesPath.add(i.image)
            }

            if (it.size > 0)
                binding.toolbar.tvOk.visibility = View.VISIBLE
            else
                binding.toolbar.tvOk.visibility = View.GONE

        }
    }

    private fun handleOnCameraClicked() {
        mainImagesAdapter.onCameraClick = {
            (activity as Cameriza).addFragmentWithBack(CameraizaFragment(), null, "")
        }
    }


    fun getAllImages() {
        viewModel.getAllImages()
    }

    private fun imagesObserver() {
        viewModel.imagesLiveDataObserver().observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {

                val imageModel = CamerizaImageModel()
                imageModel.image = ""
                imageModel.type = ""

                mainImagesAdapter.dataList.clear()
                mainImagesAdapter.dataList.add(imageModel)
                mainImagesAdapter.dataList.addAll(it.reversed() as ArrayList<CamerizaImageModel>)
                mainImagesAdapter.notifyDataSetChanged()

            }
        })
    }

}