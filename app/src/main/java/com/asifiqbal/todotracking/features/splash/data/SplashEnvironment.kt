package com.asifiqbal.todotracking.features.splash.data

import com.asifiqbal.todotracking.foundation.datasource.preference.provider.CredentialProvider
import com.asifiqbal.todotracking.model.Credential
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SplashEnvironment @Inject constructor(
    private val credentialProvider: CredentialProvider
) : ISplashEnvironment {

    override fun getCredential(): Flow<Credential> {
        return credentialProvider.getCredential()
    }

}
