package com.oolong.riddle_game.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.oolong.riddle_game.data.local.QuizDataDataBase
import com.oolong.riddle_game.data.local.appUtilityData
import com.oolong.riddle_game.data.remote.DictionaryApi
import com.oolong.riddle_game.data.repository.AppUtilityDataRepositoryImpl
import com.oolong.riddle_game.data.repository.DictionaryRepositoryImpl
import com.oolong.riddle_game.domain.IAppUtilityDataRepository
import com.oolong.riddle_game.domain.repository.IDictionaryRepository
import com.oolong.riddle_game.domain.usecase.GetQuizDataFromLocalUseCase
import com.oolong.riddle_game.domain.usecase.GetWordInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDictionaryRepository(
        dictionaryApi: DictionaryApi,
        db: QuizDataDataBase
    ): IDictionaryRepository {
        return DictionaryRepositoryImpl(
            dictionaryApi,
            db.dao
        )
    }

    @Provides
    @Singleton
    fun provideGetWordUseCase(repository: IDictionaryRepository): GetWordInfoUseCase {
        return GetWordInfoUseCase(repository)
    }

//    @Provides
//    @Singleton
//    fun provideGetQuizDataUseCase(repository: IDictionaryRepository): GetQuizDataFromLocalUseCase {
//        return GetQuizDataFromLocalUseCase(repository)
//    }

    @Provides
    @Singleton
    fun provideQuizDataBase(app: Application): QuizDataDataBase {
        return Room.databaseBuilder(
            app,
            QuizDataDataBase::class.java,
            QuizDataDataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideUtilityData(
        @ApplicationContext app: Context
    ): IAppUtilityDataRepository = AppUtilityDataRepositoryImpl(app.appUtilityData)
}