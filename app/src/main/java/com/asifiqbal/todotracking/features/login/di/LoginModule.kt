package com.asifiqbal.todotracking.features.login.di

import com.asifiqbal.todotracking.features.login.data.ILoginEnvironment
import com.asifiqbal.todotracking.features.login.data.LoginEnvironment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LoginModule {

    @Binds
    abstract fun provideEnvironment(
        environment: LoginEnvironment
    ): ILoginEnvironment

}
