package com.martacalo.letsplay.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.martacalo.letsplay.data.local.model.DesignerEntity
import com.martacalo.letsplay.data.local.model.GameEntity
import com.martacalo.letsplay.data.local.model.ImagesEntity

@Database(
    entities = [
        GameEntity::class,
        ImagesEntity::class,
        DesignerEntity::class,
    ],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gamesDao(): GamesDao

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
