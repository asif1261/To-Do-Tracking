package com.asifiqbal.todotracking.foundation.datasource.preference.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.asifiqbal.todotracking.foundation.datasource.preference.model.ThemePreference
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

object ThemePreferenceSerializer : Serializer<ThemePreference> {

    override val defaultValue: ThemePreference = ThemePreference.SYSTEM

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun readFrom(input: InputStream): ThemePreference {
        try {
            return ThemePreference.ADAPTER.decode(input)
        } catch (exception: IOException) {
            throw CorruptionException("Cannot read proto", exception)
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun writeTo(t: ThemePreference, output: OutputStream) {
        ThemePreference.ADAPTER.encode(output, t)
    }

}
