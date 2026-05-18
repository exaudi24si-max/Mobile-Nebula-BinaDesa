package com.example.exaudi_nebula.FormLogin

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.exaudi_nebula.databinding.ActivityValidationBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ValidationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityValidationBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityValidationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("UserRegData", MODE_PRIVATE)

        // Load data from SP
        val nama = sharedPreferences.getString("reg_nama", "") ?: ""
        val email = sharedPreferences.getString("reg_email", "") ?: ""
        val birthDate = sharedPreferences.getString("reg_birthdate", "") ?: ""
        val gender = sharedPreferences.getString("reg_gender", "") ?: ""
        val username = sharedPreferences.getString("reg_username", "") ?: ""
        val password = sharedPreferences.getString("reg_password", "") ?: ""
        val confirmPassword = sharedPreferences.getString("reg_confirm_password", "") ?: ""

        // Display data
        binding.tvValNama.text = "Nama: $nama"
        binding.tvValEmail.text = "Email: $email"
        binding.tvValBirthDate.text = "Tanggal Lahir: $birthDate"
        binding.tvValGender.text = "Jenis Kelamin: $gender"
        binding.tvValUsername.text = "Username: $username"

        binding.btnBack.setOnClickListener {
            finish() // Go back to Registration
        }

        binding.btnSubmit.setOnClickListener {
            if (nama.isEmpty() || email.isEmpty() || birthDate.isEmpty() || gender.isEmpty() || 
                username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Semua inputan tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Password dan Confirm Password harus sama!", Toast.LENGTH_SHORT).show()
            } else {
                // Validation Success
                showSuccessDialog()
            }
        }
    }

    private fun showSuccessDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Registrasi Berhasil")
            .setMessage("Data Anda telah berhasil divalidasi dan disimpan.")
            .setPositiveButton("Ke Halaman Login") { _, _ ->
                val intent = Intent(this, LoginMainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
            .show()
    }
}
