package com.example.exaudi_nebula.Complaint

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exaudi_nebula.data.AppDatabase
import com.example.exaudi_nebula.data.entity.ComplaintEntity
import com.example.exaudi_nebula.databinding.FragmentComplaintBinding
import kotlinx.coroutines.launch

class FragmentComplaint : Fragment() {

    private var _binding: FragmentComplaintBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ComplaintAdapter
    private lateinit var db: AppDatabase
    private val complaints = mutableListOf<ComplaintEntity>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentComplaintBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getInstance(requireContext())
        adapter = ComplaintAdapter(complaints, this)

        binding.rvComplaints.layoutManager = LinearLayoutManager(requireContext())
        binding.rvComplaints.adapter = adapter

        binding.fabAddComplaint.setOnClickListener {
            startActivity(Intent(requireContext(), ComplaintFormActivity::class.java))
        }

        fetchComplaints()
    }

    private fun fetchComplaints() {
        lifecycleScope.launch {
            val data = db.complaintDao().getAll()
            complaints.clear()
            complaints.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    fun deleteComplaint(complaint: ComplaintEntity) {
        lifecycleScope.launch {
            db.complaintDao().delete(complaint)
            fetchComplaints()
        }
    }

    override fun onResume() {
        super.onResume()
        fetchComplaints()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}