package com.example.exaudi_nebula.tabs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.exaudi_nebula.R
import com.example.exaudi_nebula.databinding.ActivityTabsBinding
import com.google.android.material.tabs.TabLayoutMediator

class TabsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTabsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.title = "Berita & Informasi"
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // 1. Inisialisasi Adapter
        val tabsAdapter = TabsPagerAdapter(this)

        // 2. Set adapter ke ViewPager2
        binding.viewPager.adapter = tabsAdapter

        // 3. Hubungkan TabLayout & ViewPager2 menggunakan Adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Berita"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_home)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 3
                }
                1 -> {
                    tab.text = "Informasi"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_info)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                }
                2 -> {
                    tab.text = "Produk"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_settings) // Menggunakan ic_settings sebagai placeholder produk
                }
            }
        }.attach()
    }
}