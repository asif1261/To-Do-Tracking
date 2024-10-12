package com.asifiqbal.todotracking.foundation.datasource.server

import com.asifiqbal.todotracking.model.Credential
import com.asifiqbal.todotracking.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID
import javax.inject.Inject

class ServerProvider @Inject constructor() {

    fun fetchCredential(): Flow<Credential> {
        return flow { emit(Credential(token = UUID.randomUUID().toString())) }
    }

    fun fetchUser(email: String, password: String): Flow<User> {
        return flow { emit(User(email)) }
    }

}
