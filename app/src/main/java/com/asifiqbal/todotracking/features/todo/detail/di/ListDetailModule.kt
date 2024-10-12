package com.asifiqbal.todotracking.features.todo.detail.di

import com.asifiqbal.todotracking.features.todo.detail.data.IListDetailEnvironment
import com.asifiqbal.todotracking.features.todo.detail.data.ListDetailEnvironment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ListDetailModule {

    @Binds
    abstract fun provideEnvironment(
        environment: ListDetailEnvironment
    ): IListDetailEnvironment

}
