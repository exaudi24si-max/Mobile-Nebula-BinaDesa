package com.example.exaudi_nebula.FormLogin

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.example.exaudi_nebula.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("UserRegData", MODE_PRIVATE)

        binding.btnNext.setOnClickListener {
            val nama = binding.inputRegNama.text.toString()
            val email = binding.inputRegEmail.text.toString()
            
            // Get Date from DatePicker
            val day = binding.datePicker.dayOfMonth
            val month = binding.datePicker.month + 1
            val year = binding.datePicker.year
            val birthDate = "$day/$month/$year"

            // Get Gender from RadioGroup
            val selectedGenderId = binding.rgGender.checkedRadioButtonId
            val gender = if (selectedGenderId != -1) {
                findViewById<RadioButton>(selectedGenderId).text.toString()
            } else ""

            val username = binding.inputRegUsername.text.toString()
            val password = binding.inputRegPassword.text.toString()
            val confirmPassword = binding.inputRegConfirmPassword.text.toString()

            // Save to SharedPreferences
            sharedPreferences.edit().apply {
                putString("reg_nama", nama)
                putString("reg_email", email)
                putString("reg_birthdate", birthDate)
                putString("reg_gender", gender)
                putString("reg_username", username)
                putString("reg_password", password)
                putString("reg_confirm_password", confirmPassword)
                apply()
            }

            // Navigate to Validation Activity
            val intent = Intent(this, ValidationActivity::class.java)
            startActivity(intent)
        }
    }
}
