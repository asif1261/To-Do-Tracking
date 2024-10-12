package com.asifiqbal.todotracking.features.splash.data

import com.asifiqbal.todotracking.model.Credential
import kotlinx.coroutines.flow.Flow

interface ISplashEnvironment {
    fun getCredential(): Flow<Credential>
}
