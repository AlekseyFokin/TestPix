package com.example.pix.di

import android.content.Context
import androidx.room.Room
import com.example.pix.data.room.PictureDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun providesDB(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            PictureDatabase::class.java,
            "skillcinema.db"
        ).build()


    @Provides
    @Singleton
    fun provideCollectionDao(db: PictureDatabase) = db.getPictureDao()

}