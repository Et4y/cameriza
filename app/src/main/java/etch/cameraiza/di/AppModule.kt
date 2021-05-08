package etch.cameraiza.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import etch.cameraiza.ImagesUseCase
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providersFilePath(application: Application): ImagesUseCase {
        return ImagesUseCase(application)
    }

}