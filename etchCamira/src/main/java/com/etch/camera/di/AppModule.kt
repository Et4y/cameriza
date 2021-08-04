package com.etch.camera.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import com.etch.camera.ImagesUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class AppModule {

    @Singleton
    @Provides
    fun providesImagesUseCase(application: Application): ImagesUseCase {
        return ImagesUseCase(application)
    }

}