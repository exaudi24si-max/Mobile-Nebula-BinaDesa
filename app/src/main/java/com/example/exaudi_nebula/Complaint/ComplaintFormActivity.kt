package com.example.exaudi_nebula.Complaint

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.exaudi_nebula.data.AppDatabase
import com.example.exaudi_nebula.data.entity.ComplaintEntity
import com.example.exaudi_nebula.databinding.ActivityComplaintFormBinding
import kotlinx.coroutines.launch

class ComplaintFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComplaintFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComplaintFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        binding.btnSaveComplaint.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()

            if (title.isNotBlank() && description.isNotBlank()) {
                lifecycleScope.launch {
                    val complaint = ComplaintEntity(
                        title = title,
                        description = description,
                        status = "Baru",
                        date = System.currentTimeMillis()
                    )
                    db.complaintDao().insert(complaint)
                    finish()
                }
            } else {
                Toast.makeText(this, "Isi semua kolom!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
}