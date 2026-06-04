package com.example.exaudi_nebula.Complaint

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exaudi_nebula.data.entity.ComplaintEntity
import com.example.exaudi_nebula.databinding.ItemComplaintBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ComplaintAdapter(
    private val complaints: List<ComplaintEntity>,
    private val fragment: FragmentComplaint
) : RecyclerView.Adapter<ComplaintAdapter.ComplaintViewHolder>() {

    class ComplaintViewHolder(val binding: ItemComplaintBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComplaintViewHolder {
        val binding = ItemComplaintBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComplaintViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComplaintViewHolder, position: Int) {
        val complaint = complaints[position]
        holder.binding.tvTitle.text = complaint.title
        holder.binding.tvDescription.text = complaint.description
        holder.binding.tvStatus.text = complaint.status
        
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        holder.binding.tvDate.text = sdf.format(Date(complaint.date))

        holder.binding.btnDelete.setOnClickListener {
            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle("Hapus Pengaduan")
                .setMessage("Apakah Anda yakin ingin menghapus pengaduan ini?")
                .setPositiveButton("Ya") { dialog, _ ->
                    fragment.deleteComplaint(complaint)
                    dialog.dismiss()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun getItemCount(): Int = complaints.size
}