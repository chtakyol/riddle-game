package com.oolong.riddle_game.data.local

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import com.oolong.riddle_game.AppUtilityData
import java.io.InputStream
import java.io.OutputStream

internal val Context.appUtilityData: DataStore<AppUtilityData> by dataStore(
    fileName = "app_utility_data.pb",
    serializer = AppUtilityDataSerializer
)

private object AppUtilityDataSerializer: Serializer<AppUtilityData> {
    override val defaultValue: AppUtilityData = AppUtilityData.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): AppUtilityData {
        try {
            return AppUtilityData.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: AppUtilityData, output: OutputStream) = t.writeTo(output)
}