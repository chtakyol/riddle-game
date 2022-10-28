package com.oolong.riddle_game.di

import android.app.Application
import androidx.room.Room
import com.oolong.riddle_game.data.local.QuizDataDao
import com.oolong.riddle_game.data.local.QuizDataDataBase
import com.oolong.riddle_game.data.remote.DictionaryApi
import com.oolong.riddle_game.data.repository.DictionaryRepositoryImpl
import com.oolong.riddle_game.domain.repository.IDictionaryRepository
import com.oolong.riddle_game.domain.usecase.GetSingleQuizDataUseCase
import com.oolong.riddle_game.domain.usecase.GetWordInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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

    @Provides
    @Singleton
    fun provideGetQuizDataUseCase(repository: IDictionaryRepository): GetSingleQuizDataUseCase {
        return GetSingleQuizDataUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideQuizDataBase(app: Application): QuizDataDataBase {
        return Room.databaseBuilder(
            app,
            QuizDataDataBase::class.java,
            QuizDataDataBase.DATABASE_NAME
        ).build()
    }
}