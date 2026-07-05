package com.example.exaudi_nebula.Note

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.exaudi_nebula.BaseActivity
import com.example.exaudi_nebula.data.AppDatabase
import com.example.exaudi_nebula.data.entity.NoteEntity
import com.example.exaudi_nebula.databinding.ActivityNoteFormBinding
import com.example.exaudi_nebula.utils.ReminderHelper
import kotlinx.coroutines.launch
import java.util.Calendar

class NoteFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        binding.btnSaveNote.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()

            if (title.isNotBlank() && content.isNotBlank()) {
                val minutesStr = binding.etReminderMinutes.text.toString()
                val minutes = if (minutesStr.isNotBlank()) minutesStr.toInt() else 1

                // Solusi: Menggunakan Coroutine (lifecycleScope) agar database diakses di background thread
                lifecycleScope.launch {
                    val note = NoteEntity(
                        title = title,
                        content = content,
                        createdAt = System.currentTimeMillis()
                    )
                    db.noteDao().insert(note)
                    
                    // Set Reminder sesuai input user
                    val calendar = Calendar.getInstance().apply {
                        add(Calendar.MINUTE, minutes)
                    }
                    
                    ReminderHelper.setReminder(
                        context = this@NoteFormActivity,
                        hour = calendar.get(Calendar.HOUR_OF_DAY),
                        minute = calendar.get(Calendar.MINUTE),
                        title = "Ingat Catatan Anda",
                        message = "Waktunya meninjau catatan: $title",
                        targetActivity = BaseActivity::class.java
                    )
                    
                    Toast.makeText(this@NoteFormActivity, "Disimpan! Reminder diset $minutes menit lagi.", Toast.LENGTH_SHORT).show()

                    finish()
                }
            } else {
                Toast.makeText(this, "Isi semua kolom!", Toast.LENGTH_SHORT).show()
            }
        }
        
        // Back button in toolbar
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}