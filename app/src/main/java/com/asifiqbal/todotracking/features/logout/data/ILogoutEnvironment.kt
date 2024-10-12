package com.asifiqbal.todotracking.features.logout.data

import com.asifiqbal.todotracking.model.User
import kotlinx.coroutines.flow.Flow

interface ILogoutEnvironment {
    suspend fun logout()
    fun getUser(): Flow<User>
}
