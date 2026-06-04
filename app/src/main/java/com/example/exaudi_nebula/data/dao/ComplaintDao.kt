package com.example.exaudi_nebula.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.exaudi_nebula.data.entity.ComplaintEntity

@Dao
interface ComplaintDao {
    @Query("SELECT * FROM complaints ORDER BY date DESC")
    suspend fun getAll(): List<ComplaintEntity>

    @Insert
    suspend fun insert(complaint: ComplaintEntity)

    @Delete
    suspend fun delete(complaint: ComplaintEntity)
}