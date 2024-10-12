package com.asifiqbal.todotracking.features.localized.setting.di

import com.asifiqbal.todotracking.features.localized.setting.data.ILocalizedSettingEnvironment
import com.asifiqbal.todotracking.features.localized.setting.data.LocalizedSettingEnvironment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LocalizedSettingModule {

    @Binds
    abstract fun provideEnvironment(
        environment: LocalizedSettingEnvironment
    ): ILocalizedSettingEnvironment

}
