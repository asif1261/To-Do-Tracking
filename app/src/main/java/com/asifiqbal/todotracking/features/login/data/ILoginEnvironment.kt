package com.asifiqbal.todotracking.features.login.data

import kotlinx.coroutines.flow.Flow

interface ILoginEnvironment {
    fun login(email: String, password: String): Flow<Any>
}
