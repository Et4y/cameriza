package com.etch.camera.framework

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.etch.camera.R
import com.etch.camera.adapter.ColorsAdapter
import com.etch.camera.databinding.FragmentEditImageBinding
import com.etch.camera.util.setGlide
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditImageFragment : Fragment() {

    @Inject
    lateinit var colorsAdapter: ColorsAdapter


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
        initRecyclerColors()
    }

    private fun receiveData() {
        val path = arguments?.getString("uri")

        binding.iv.setGlide(path!!)

    }

    private fun initRecyclerColors(){
        binding.rvColors.adapter = colorsAdapter

        colorsAdapter.dataList = resources.getIntArray(R.array.colorsList).toCollection(ArrayList())
        colorsAdapter.notifyDataSetChanged()
    }


}