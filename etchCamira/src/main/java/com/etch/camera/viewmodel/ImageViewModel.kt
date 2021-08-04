package com.etch.camera.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.etch.camera.data.CamerizaImageModel
import com.etch.camera.ImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val imagesUseCase: ImagesUseCase,
) : ViewModel(), CoroutineScope {


    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private var imagesLiveData: MutableLiveData<List<CamerizaImageModel>> = MutableLiveData()


    init {
        getAllImages()
    }

    fun getAllImages() {
        viewModelScope.launch(coroutineContext) {
            imagesLiveData.value =
                imagesUseCase.loadImagesFromSDCard()
        }
    }


    fun imagesLiveDataObserver() = imagesLiveData


}