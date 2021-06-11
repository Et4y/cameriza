package com.etch.camera.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import com.etch.camera.ImagesUseCase
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
internal class AppModule {

    @Singleton
    @Provides
    fun providersFilePath(application: Application): ImagesUseCase {
        return ImagesUseCase(application)
    }

}