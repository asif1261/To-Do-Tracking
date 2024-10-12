package com.asifiqbal.todotracking.foundation.extension

import com.asifiqbal.todotracking.model.Credential

fun Credential.isLoggedIn() = token.isNotBlank()
