package com.example.exaudi_nebula.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exaudi_nebula.data.dao.ComplaintDao
import com.example.exaudi_nebula.data.dao.NoteDao
import com.example.exaudi_nebula.data.entity.ComplaintEntity
import com.example.exaudi_nebula.data.entity.NoteEntity

@Database(
    entities = [NoteEntity::class, ComplaintEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    
    abstract fun noteDao(): NoteDao
    abstract fun complaintDao(): ComplaintDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                .fallbackToDestructiveMigration()
                .build().also { INSTANCE = it }
            }
        }
    }
}