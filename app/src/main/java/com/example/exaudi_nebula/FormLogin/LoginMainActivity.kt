package com.example.exaudi_nebula.FormLogin

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.exaudi_nebula.MainActivity
import com.example.exaudi_nebula.databinding.ActivityLoginMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LoginMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var regPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Menghapus enableEdgeToEdge agar layout tidak tertutup status bar secara paksa
        // enableEdgeToEdge() 

        binding = ActivityLoginMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)
        regPreferences = getSharedPreferences("UserRegData", MODE_PRIVATE)

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            // Karena menggunakan ViewBinding, kita tetap bisa mengakses ID inputNama dan inputPassword
            val nama = binding.inputNama.text.toString()
            val pass = binding.inputPassword.text.toString()

            if (nama.isEmpty() || pass.isEmpty()) {
                // Memberikan error pada TextInputLayout agar lebih user-friendly
                binding.layoutNama.error = if (nama.isEmpty()) "Nama tidak boleh kosong" else null
                binding.layoutPassword.error = if (pass.isEmpty()) "Password tidak boleh kosong" else null
                
                Toast.makeText(this, "Silakan lengkapi data Anda!", Toast.LENGTH_SHORT).show()
            } else {
                val savedUsername = regPreferences.getString("reg_username", "")
                val savedPassword = regPreferences.getString("reg_password", "")

                val isValid = (nama == pass) || (nama == savedUsername && pass == savedPassword)

                if (isValid) {
                    // Simpan status login
                    sharedPreferences.edit().apply {
                        putBoolean("isLogin", true)
                        putString("NAMA_USER", nama) // Simpan nama agar Dashboard bisa menyapa
                        apply()
                    }

                    Toast.makeText(this, "Halo $nama, Selamat Datang!", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, com.example.exaudi_nebula.BaseActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    showErrorDialog()
                }
            }
        }
    }

    private fun showErrorDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Login Gagal")
            .setMessage("Username atau Password salah. Silakan coba lagi.")
            .setPositiveButton("OK", null)
            .show()
    }
}
