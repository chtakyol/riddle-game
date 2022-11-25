package com.oolong.riddle_game.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.oolong.riddle_game.AppUtilityData
import com.oolong.riddle_game.domain.IAppUtilityDataRepository
import com.oolong.riddle_game.domain.model.UtilityData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class AppUtilityDataRepositoryImpl(
    private val dataStore: DataStore<AppUtilityData>
): IAppUtilityDataRepository {
    override suspend fun getAppUtilityData(
        onSuccess: (UtilityData) -> Unit,
        onError: (Exception) -> Unit
    ) = withContext(Dispatchers.IO) {
        try {
            val appUtilityData = dataStore.data.first()

            onSuccess(
                appUtilityData.toUtilityData
            )
        } catch (e: Exception) {
            onError(e)
        }
    }

    override suspend fun updateAppUtilityData(
        lastDateRemoteGet: String,
        onSuccess: (isRecord: Boolean) -> Unit,
        onError: (Exception) -> Unit
    ) = withContext(Dispatchers.IO) {
        try {
            dataStore.updateData { appUtilityData ->
                appUtilityData
                    .toBuilder()
                    .setLastDateRemoteGet(lastDateRemoteGet)
                    .build()
            }
            onSuccess(true)
        } catch (e: Exception) {
            onError(e)
        }
    }

    private val AppUtilityData.toUtilityData: UtilityData get() {
        return UtilityData(
            lastDateRemoteGet = this.lastDateRemoteGet
        )
    }

}