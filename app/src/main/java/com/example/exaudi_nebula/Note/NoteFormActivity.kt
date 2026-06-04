package com.example.exaudi_nebula.Note

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.exaudi_nebula.data.AppDatabase
import com.example.exaudi_nebula.data.entity.NoteEntity
import com.example.exaudi_nebula.databinding.ActivityNoteFormBinding
import kotlinx.coroutines.launch

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
                // Solusi: Menggunakan Coroutine (lifecycleScope) agar database diakses di background thread
                lifecycleScope.launch {
                    val note = NoteEntity(
                        title = title,
                        content = content,
                        createdAt = System.currentTimeMillis()
                    )
                    db.noteDao().insert(note)
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