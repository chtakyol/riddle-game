package com.oolong.riddle_game.domain

import com.oolong.riddle_game.domain.model.UtilityData

interface IAppUtilityDataRepository {
    suspend fun getAppUtilityData(
        onSuccess: (UtilityData) -> Unit,
        onError: (Exception) -> Unit
    )

    suspend fun updateAppUtilityData(
        lastDateRemoteGet: String,
        onSuccess: (isRecord: Boolean) -> Unit,
        onError: (Exception) -> Unit
    )
}