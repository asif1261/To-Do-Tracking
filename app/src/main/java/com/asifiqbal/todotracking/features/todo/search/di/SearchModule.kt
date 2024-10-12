package com.asifiqbal.todotracking.features.todo.search.di

import com.asifiqbal.todotracking.features.todo.search.data.ISearchEnvironment
import com.asifiqbal.todotracking.features.todo.search.data.SearchEnvironment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SearchModule {

    @Binds
    abstract fun provideEnvironment(
        environment: SearchEnvironment
    ): ISearchEnvironment

}
