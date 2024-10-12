package com.asifiqbal.todotracking.features.splash.di

import com.asifiqbal.todotracking.features.splash.data.ISplashEnvironment
import com.asifiqbal.todotracking.features.splash.data.SplashEnvironment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SplashModule {

    @Binds
    abstract fun provideEnvironment(
        environment: SplashEnvironment
    ): ISplashEnvironment

}
