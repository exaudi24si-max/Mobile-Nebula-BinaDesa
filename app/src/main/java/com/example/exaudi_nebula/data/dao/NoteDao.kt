package com.example.exaudi_nebula.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.exaudi_nebula.data.entity.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes ORDER BY createdAt DESC")
    suspend fun getAll(): List<NoteEntity>

    @Insert
    suspend fun insert(note: NoteEntity)

    @Delete
    suspend fun delete(note: NoteEntity)
}