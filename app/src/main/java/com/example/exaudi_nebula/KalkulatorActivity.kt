package com.example.exaudi_nebula

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.exaudi_nebula.databinding.ActivityKalkulatorBinding

class KalkulatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKalkulatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKalkulatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarKalkulator)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Rumus Bangun Ruang"

        binding.btnHitungSegitiga.setOnClickListener {
            val alas = binding.etAlas.text.toString().toDoubleOrNull() ?: 0.0
            val tinggi = binding.etTinggi.text.toString().toDoubleOrNull() ?: 0.0
            val hasil = 0.5 * alas * tinggi
            binding.tvHasilSegitiga.text = "Hasil: $hasil"
            Log.d("EXAUDI_LOG", "Segitiga - Alas: $alas, Tinggi: $tinggi, Hasil: $hasil")
        }

        binding.btnHitungKubus.setOnClickListener {
            val sisi = binding.etSisi.text.toString().toDoubleOrNull() ?: 0.0
            val hasil = sisi * sisi * sisi
            binding.tvHasilKubus.text = "Hasil: $hasil"
            Log.i("EXAUDI_LOG", "Kubus - Sisi: $sisi, Hasil: $hasil")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
