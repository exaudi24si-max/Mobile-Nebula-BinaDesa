package com.example.exaudi_nebula

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exaudi_nebula.FormLogin.LoginMainActivity
import com.example.exaudi_nebula.FormLogin.LoginResultActivity
import com.example.exaudi_nebula.data.api.CatFactApiClient
import com.example.exaudi_nebula.data.api.PhotoApiClient
import com.example.exaudi_nebula.databinding.FragmentHomeBinding
import com.example.exaudi_nebula.pertemuan_13.ThirteenthActivity
import com.example.exaudi_nebula.photo.PhotoAdapter
import com.example.exaudi_nebula.tabs.TabsActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireContext().getSharedPreferences("UserSession", Context.MODE_PRIVATE)


        // Sapaan User di Header
        val namaUser = sharedPreferences.getString("NAMA_USER", "Exaudi")
        binding.tvWelcome.text = getString(R.string.welcome_user, namaUser)

        // 1. Ke Kalkulator Bangun Ruang
        binding.cardRumus.setOnClickListener {
            val intent = Intent(requireContext(), KalkulatorActivity::class.java)
            startActivity(intent)
        }

        // 2. Ke Laporan/Result
        binding.cardCustom1.setOnClickListener {
            val intent = Intent(requireContext(), LoginResultActivity::class.java)
            startActivity(intent)
        }

        // 3. Ke Web View Bina Desa
        binding.cardWebView.setOnClickListener {
            val intent = Intent(requireContext(), WebViewActivity::class.java)
            startActivity(intent)
        }

        // 4. Ke Menu Berita & Info (Tabs Navigation)
        binding.cardTabNav.setOnClickListener {
            val intent = Intent(requireContext(), TabsActivity::class.java)
            startActivity(intent)
        }

        // 6. Ke Pertemuan 13
        binding.cardPertemuan13.setOnClickListener {
            val intent = Intent(requireContext(), ThirteenthActivity::class.java)
            startActivity(intent)
        }

        // 5. Logika Logout
        binding.cardLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Ya") { dialog, _ ->
                    sharedPreferences.edit {
                        clear()
                        apply()
                    }
                    val intent = Intent(requireContext(), LoginMainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    requireActivity().finish()
                    dialog.dismiss()
                }
                .setNegativeButton("Tidak", null)
                .show()
        }

        // --- Tambahan Pertemuan 11 ---
        loadCatFact()
        loadPhoto()

        binding.btnRefresh.setOnClickListener {
            loadCatFact()
        }
    }

    private fun loadCatFact() {
        lifecycleScope.launch {
            try {
                val response = CatFactApiClient.apiService.getCatFact()
                binding.tvCatFact.text = "\"${response.fact}\""
            } catch (e: Exception) {
                binding.tvCatFact.text = "Gagal mengambil fakta unik desa."
            }
        }
    }

    private fun loadPhoto() {
        lifecycleScope.launch {
            try {
                val photos = PhotoApiClient.apiService.getPhotos()
                if (photos.isNotEmpty()) {
                    val adapter = PhotoAdapter(photos)
                    binding.rvGallery.adapter = adapter
                    binding.rvGallery.layoutManager = LinearLayoutManager(requireContext())
                }
            } catch (e: Exception) {
                android.widget.Toast.makeText(requireContext(), "Gagal memuat data: Periksa koneksi internet", android.widget.Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
