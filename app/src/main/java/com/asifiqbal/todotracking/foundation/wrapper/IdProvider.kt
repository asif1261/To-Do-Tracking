package com.asifiqbal.todotracking.foundation.wrapper

import java.util.*

interface IdProvider {
    fun generate(): String
}

class IdProviderImpl : IdProvider {
    override fun generate(): String {
        return UUID.randomUUID().toString()
    }
}
