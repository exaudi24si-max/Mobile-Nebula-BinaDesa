package com.example.exaudi_nebula.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.exaudi_nebula.databinding.FragmentAspirasiBinding

class AspirasiFragment : Fragment() {

    private var _binding: FragmentAspirasiBinding? = null
    private val binding get() = _binding!!

    // Data Laporan Aspirasi Masyarakat Bina Desa dengan URL yang lebih stabil (Picsum)
    // Seed yang unik memastikan gambar berbeda-beda dan bertema desa/pembangunan
    private val aspirasiList = listOf(
        AspirasiModel("Perbaikan Jalan Dusun III", "Menunggu Perbaikan", "https://picsum.photos/seed/village_road/500/350"),
        AspirasiModel("Saluran Air Tersumbat", "Laporan Diterima", "https://picsum.photos/seed/river_clean/500/350"),
        AspirasiModel("Lampu Jalan Padam", "Sedang Diproses", "https://picsum.photos/seed/lamp_post/500/350"),
        AspirasiModel("Pembangunan Jembatan", "Selesai", "https://picsum.photos/seed/bridge_village/500/350"),
        AspirasiModel("Tumpukan Sampah di Pasar", "Menunggu Perbaikan", "https://picsum.photos/seed/trash_bin/500/350"),
        AspirasiModel("Renovasi Balai Desa", "Laporan Diterima", "https://picsum.photos/seed/village_hall/500/350"),
        AspirasiModel("Pembersihan Sungai", "Sedang Diproses", "https://picsum.photos/seed/river_village/500/350"),
        AspirasiModel("Perbaikan Irigasi Sawah", "Selesai", "https://picsum.photos/seed/farming_village/500/350"),
        AspirasiModel("Pemasangan Rambu Desa", "Laporan Diterima", "https://picsum.photos/seed/village_sign/500/350"),
        AspirasiModel("Penyediaan Taman Bermain", "Sedang Diproses", "https://picsum.photos/seed/playground/500/350")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAspirasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Menggunakan AspirasiAdapter agar data tampil sesuai desain Bina Desa
        val adapter = AspirasiAdapter(aspirasiList) { item ->
            Toast.makeText(requireContext(), "Aspirasi: ${item.title}", Toast.LENGTH_SHORT).show()
        }

        binding.rvAspirasi.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}