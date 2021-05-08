package etch.cameraiza.framework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import etch.cameraiza.ImageViewModel
import etch.cameraiza.R
import etch.cameraiza.adapter.MainImagesAdapter
import etch.cameraiza.databinding.FragmentCameraBinding
import etch.cameraiza.databinding.FragmentGallaryBinding
import etch.cameraiza.util.addFragmentWithBack
import etch.cameraiza.util.addFragmentWithoutBack
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
            (activity as MainActivity).addFragmentWithBack(
                CameraizaFragment(),
                null,
                "cameraiza_fragment"
            )
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