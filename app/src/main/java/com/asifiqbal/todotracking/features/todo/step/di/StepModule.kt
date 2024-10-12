package com.asifiqbal.todotracking.features.todo.step.di

import com.asifiqbal.todotracking.features.todo.step.data.IStepEnvironment
import com.asifiqbal.todotracking.features.todo.step.data.StepEnvironment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class StepModule {

    @Binds
    abstract fun provideEnvironment(
        environment: StepEnvironment
    ): IStepEnvironment

}
