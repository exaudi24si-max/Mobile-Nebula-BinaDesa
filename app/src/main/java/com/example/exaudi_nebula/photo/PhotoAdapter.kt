package com.example.exaudi_nebula.photo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.exaudi_nebula.data.model.PhotoModel
import com.example.exaudi_nebula.databinding.ItemPhotoBinding
import kotlin.random.Random

class PhotoAdapter(private val items: List<PhotoModel>) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    private val daftarJudul = listOf(
        "Perbaikan Aspal Jalan Lintas Dusun",
        "Pembersihan Drainase Antisipasi Banjir",
        "Renovasi Pos Ronda RT 03 RW 01",
        "Pemasangan Lampu Jalan Tenaga Surya",
        "Gotong Royong Kebersihan Balai Desa",
        "Bantuan Pompa Air untuk Kelompok Tani",
        "Pembangunan Jembatan Gantung Mini",
        "Laporan Sampah Menumpuk di Area Pasar",
        "Pavingisasi Jalan Gang Mawar",
        "Rehabilitasi Saluran Irigasi Sawah",
        "Penyuluhan Posyandu Balita & Lansia",
        "Pemeliharaan Taman Bermain Anak",
        "Perbaikan Pipa Air Bersih Desa",
        "Pembangunan Tembok Penahan Tanah",
        "Renovasi Gedung Serbaguna Desa",
        "Normalisasi Aliran Sungai Kecil"
    )

    // Gunakan link gambar yang lebih stabil dan dipastikan aktif untuk tema Indonesia
    private val daftarGambarIndonesia = listOf(
        "https://images.unsplash.com/photo-1596438459194-f275f413d6ff?q=75&w=600",
        "https://images.unsplash.com/photo-1541088941260-ce55ed18ad75?q=75&w=600",
        "https://images.unsplash.com/photo-1552733407-5d5c46c3bb3b?q=75&w=600",
        "https://images.unsplash.com/photo-1516690561799-46d8f74f90f6?q=75&w=600",
        "https://images.unsplash.com/photo-1544919982-b61976f0ba43?q=75&w=600",
        "https://images.unsplash.com/photo-1523348837708-15d4a09cfac2?q=75&w=600",
        "https://images.unsplash.com/photo-1605303493733-f542152668ca?q=75&w=600",
        "https://images.unsplash.com/photo-1583303649938-f9b768a48b94?q=75&w=600",
        "https://images.unsplash.com/photo-1566484832329-4115aff80af7?q=75&w=600",
        "https://images.unsplash.com/photo-1518709268805-4e9042af9f23?q=75&w=600",
        "https://images.unsplash.com/photo-1537996194471-e657df975ab4?q=75&w=600",
        "https://images.unsplash.com/photo-1591834161245-56064f269a8b?q=75&w=600",
        "https://images.unsplash.com/photo-1565438842603-518883f84844?q=75&w=600",
        "https://images.unsplash.com/photo-1623912140411-a887f353664d?q=75&w=600",
        "https://images.unsplash.com/photo-1502444330042-d1a1ddf9bb5c?q=75&w=600",
        "https://images.unsplash.com/photo-1506466010722-395aa2bef877?q=75&w=600"
    )

    private val daftarPelapor = listOf(
        "Pak Bambang", "Bu Siti", "Mas Aris", "Mbak Indah", 
        "Pak RT Agus", "Bu Lurah Wahyu", "Pak Haji Slamet", "Mbak Sari",
        "Pak Dedi", "Bu Ani", "Mas Eko", "Mbak Yanti",
        "Pak Jarwo", "Bu Totok", "Mas Bagus", "Mbak Devi"
    )

    class PhotoViewHolder(val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val index = position % daftarJudul.size
        
        holder.binding.tvTitle.text = daftarJudul[index]
        holder.binding.tvAuthor.text = "Pelapor: ${daftarPelapor[index]}"
        holder.binding.tvDate.text = "${Random.nextInt(1, 28)} Mei 2026"
        
        val status = when(index % 3) {
            0 -> "Selesai"
            1 -> "Diproses"
            else -> "Baru"
        }
        holder.binding.tvStatus.text = status

        // Gunakan pemuatan gambar yang paling sederhana dan stabil
        Glide.with(holder.itemView.context)
            .load(daftarGambarIndonesia[index])
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE) // Cache versi gambar yang sudah di-resize saja
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_gallery) // Gunakan icon gallery jika gagal agar tetap rapi
            .centerCrop()
            .into(holder.binding.imgPhoto)
    }

    override fun getItemCount(): Int = items.size
}