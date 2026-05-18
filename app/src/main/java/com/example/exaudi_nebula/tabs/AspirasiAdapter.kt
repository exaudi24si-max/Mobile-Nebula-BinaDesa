package com.example.exaudi_nebula.tabs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exaudi_nebula.R
import com.example.exaudi_nebula.databinding.ItemAspirasiBinding

class AspirasiAdapter(
    private val aspirasiList: List<AspirasiModel>,
    private val onItemClick: (AspirasiModel) -> Unit
) : RecyclerView.Adapter<AspirasiAdapter.AspirasiViewHolder>() {

    inner class AspirasiViewHolder(val binding: ItemAspirasiBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AspirasiViewHolder {
        val binding = ItemAspirasiBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AspirasiViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AspirasiViewHolder, position: Int) {
        val item = aspirasiList[position]
        with(holder.binding) {
            tvTitle.text = item.title
            tvStatus.text = item.status

            // Menambahkan placeholder dan error image agar user tahu jika gambar sedang loading atau gagal
            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_report_image)
                .centerCrop()
                .into(imgAspirasi)

            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun getItemCount(): Int = aspirasiList.size
}