package com.example.exaudi_nebula.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "complaints")
data class ComplaintEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val status: String, // e.g., "Baru", "Diproses", "Selesai"
    val date: Long
)