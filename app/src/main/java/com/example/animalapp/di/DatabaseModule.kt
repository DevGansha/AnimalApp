package com.example.animalapp.di

import android.app.Application
import androidx.room.Room
import com.example.animalapp.data.local.AnimalFavDao
import com.example.animalapp.data.local.AnimalFavDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application, callback: AnimalFavDb.Callback): AnimalFavDb{
        return Room.databaseBuilder(application, AnimalFavDb::class.java, "animal_fav_db")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()
    }

    @Provides
    fun provideMovieAppDao(db: AnimalFavDb): AnimalFavDao{
        return db.getAnimalFavDao()
    }
}