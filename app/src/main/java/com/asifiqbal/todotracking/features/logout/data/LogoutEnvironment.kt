package com.asifiqbal.todotracking.features.logout.data

import com.asifiqbal.todotracking.foundation.datasource.preference.provider.CredentialProvider
import com.asifiqbal.todotracking.foundation.datasource.preference.provider.UserProvider
import com.asifiqbal.todotracking.model.Credential
import com.asifiqbal.todotracking.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogoutEnvironment @Inject constructor(
    private val credentialProvider: CredentialProvider,
    private val userProvider: UserProvider
) : ILogoutEnvironment {
    override suspend fun logout() {
        credentialProvider.setCredential(Credential(token = ""))
        userProvider.setUser(User(email = ""))
    }

    override fun getUser(): Flow<User> {
        return userProvider.getUser()
    }

}
