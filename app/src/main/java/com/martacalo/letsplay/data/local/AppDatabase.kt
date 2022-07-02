package com.martacalo.letsplay.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.martacalo.letsplay.data.local.model.*

@Database(
    entities = [
        GameEntity::class,
        ImagesEntity::class,
        DesignerEntity::class,
        LibraryEntity::class,
    ],
    version = 3,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gamesDao(): GamesDao
    abstract fun libraryDao(): LibraryDao

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "lets_play_db")
                .fallbackToDestructiveMigration()
                .build()
    }
}
