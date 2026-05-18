package com.example.exaudi_nebula

import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.exaudi_nebula.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Penyesuaian Window Insets agar BottomNav menempel ke bawah (Solusi dari Notion)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        // Menampilkan HomeFragment sebagai default
        // Solusi Agar Tidak Ada Halaman Putih: Fragment pertama tidak dimasukkan ke BackStack
        if (savedInstanceState == null) {
            loadFragment(HomeFragment(), false)
        }

        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment(), false)
                    true
                }
                R.id.nav_about -> {
                    loadFragment(AboutFragment(), true)
                    true
                }
                R.id.nav_profile -> {
                    loadFragment(ProfileFragment(), true)
                    true
                }
                R.id.nav_settings -> {
                    loadFragment(SettingsFragment(), true)
                    true
                }
                else -> false
            }
        }

        // Solusi Praktikum: Menangani tombol back agar tidak muncul halaman putih (container kosong)
        onBackPressedDispatcher.addCallback(this) {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
            } else {
                finish()
            }
        }
    }

    private fun loadFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.fragmentContainer.id, fragment)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }
}
