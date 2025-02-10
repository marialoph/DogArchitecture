package com.santi.pmdm.virgen.dogapicleanarchitecture.framework

import android.content.Context
import androidx.room.Room
import com.santi.pmdm.virgen.dogapicleanarchitecture.data.datasource.database.DatabaseDogs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val NAME_DATABASE = "database_dogs"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DatabaseDogs::class.java, NAME_DATABASE).build()

    @Singleton
    @Provides
    fun provideDao(database: DatabaseDogs) = database.dogDao()
}