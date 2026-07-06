package com.example.exaudi_nebula.Complaint

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.exaudi_nebula.BaseActivity
import com.example.exaudi_nebula.data.AppDatabase
import com.example.exaudi_nebula.data.entity.ComplaintEntity
import com.example.exaudi_nebula.databinding.ActivityComplaintFormBinding
import com.example.exaudi_nebula.utils.NotificationHelper
import com.example.exaudi_nebula.utils.PermissionHelper
import kotlinx.coroutines.launch

class ComplaintFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComplaintFormBinding
    private lateinit var db: AppDatabase

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifikasi ditolak", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComplaintFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar dengan tombol Back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Buat Pengaduan"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }

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
                    
                    // Kirim Notifikasi saat pengaduan berhasil disimpan
                    val intent = Intent(this@ComplaintFormActivity, BaseActivity::class.java)
                    NotificationHelper.showNotification(
                        this@ComplaintFormActivity,
                        "Pengaduan Terkirim",
                        "Laporan '$title' Anda sedang kami tinjau.",
                        intent
                    )

                    finish()
                }
            } else {
                Toast.makeText(this, "Isi semua kolom!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}