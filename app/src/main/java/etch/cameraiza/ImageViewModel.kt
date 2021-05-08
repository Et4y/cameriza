package etch.cameraiza

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class ImageViewModel @ViewModelInject constructor(
    private val imagesUseCase: ImagesUseCase,
) : ViewModel(),
    CoroutineScope {


    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private var imagesLiveData: MutableLiveData<List<String>> = MutableLiveData()


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