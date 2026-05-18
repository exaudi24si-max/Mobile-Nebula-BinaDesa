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

    // Menggunakan Lorem Picsum agar gambar lebih stabil dan pasti tampil di emulator.
    // Setiap item memiliki 'seed' yang berbeda agar gambar yang muncul unik.
    private val aspirasiList = listOf(
        AspirasiModel("Perbaikan Jalan Dusun III", "Menunggu Perbaikan", "https://picsum.photos/seed/desa1/500/300"),
        AspirasiModel("Drainase Tersumbat RT 02", "Laporan Diterima", "https://picsum.photos/seed/desa2/500/300"),
        AspirasiModel("Lampu Jalan Padam", "Sedang Diproses", "https://picsum.photos/seed/desa3/500/300"),
        AspirasiModel("Pembangunan Jembatan Desa", "Selesai", "https://picsum.photos/seed/desa4/500/300"),
        AspirasiModel("Sampah Menumpuk di Pasar", "Menunggu Perbaikan", "https://picsum.photos/seed/desa5/500/300"),
        AspirasiModel("Renovasi Pos Kamling", "Laporan Diterima", "https://picsum.photos/seed/desa6/500/300"),
        AspirasiModel("Pembersihan Saluran Air", "Sedang Diproses", "https://picsum.photos/seed/desa7/500/300"),
        AspirasiModel("Perbaikan Irigasi Sawah", "Selesai", "https://picsum.photos/seed/desa8/500/300"),
        AspirasiModel("Pemasangan Plang Nama Jalan", "Laporan Diterima", "https://picsum.photos/seed/desa9/500/300"),
        AspirasiModel("Penataan Taman Lingkungan", "Sedang Diproses", "https://picsum.photos/seed/desa10/500/300")
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

        // Inisialisasi adapter dengan data Aspirasi
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
