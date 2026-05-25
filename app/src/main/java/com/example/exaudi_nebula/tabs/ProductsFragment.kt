package com.example.exaudi_nebula.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.exaudi_nebula.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    // Data Laporan Aspirasi Masyarakat Bina Desa
    // Menggunakan Lorem Picsum (picsum.photos) agar gambar lebih stabil dan pasti tampil di emulator
    private val aspirasiList = listOf(
        AspirasiModel(
            "Perbaikan Jalan Dusun III", 
            "Menunggu Perbaikan", 
            "https://picsum.photos/seed/road_fix/500/300"
        ),
        AspirasiModel(
            "Drainase Tersumbat RT 02", 
            "Laporan Diterima", 
            "https://picsum.photos/seed/drainage/500/300"
        ),
        AspirasiModel(
            "Laporan Lampu Jalan Padam", 
            "Sedang Diproses", 
            "https://picsum.photos/seed/street_lamp/500/300"
        ),
        AspirasiModel(
            "Pembangunan Jembatan Desa", 
            "Selesai", 
            "https://picsum.photos/seed/bridge/500/300"
        ),
        AspirasiModel(
            "Sampah Menumpuk di Pasar", 
            "Menunggu Perbaikan", 
            "https://picsum.photos/seed/trash_bin/500/300"
        ),
        AspirasiModel(
            "Renovasi Pos Kamling", 
            "Laporan Diterima", 
            "https://picsum.photos/seed/village_hall/500/300"
        ),
        AspirasiModel(
            "Pembersihan Saluran Air", 
            "Sedang Diproses", 
            "https://picsum.photos/seed/river_cleanup/500/300"
        ),
        AspirasiModel(
            "Perbaikan Irigasi Sawah", 
            "Selesai", 
            "https://picsum.photos/seed/farm_irrigation/500/300"
        ),
        AspirasiModel(
            "Pemasangan Plang Nama Jalan", 
            "Laporan Diterima", 
            "https://picsum.photos/seed/road_sign/500/300"
        ),
        AspirasiModel(
            "Penataan Taman Lingkungan", 
            "Sedang Diproses", 
            "https://picsum.photos/seed/village_park/500/300"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Menggunakan AspirasiAdapter agar sesuai dengan tema laporan aspirasi masyarakat Bina Desa
        val adapter = AspirasiAdapter(aspirasiList) { item ->
            Toast.makeText(requireContext(), "Aspirasi: ${item.title}", Toast.LENGTH_SHORT).show()
        }

        binding.rvProducts.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
