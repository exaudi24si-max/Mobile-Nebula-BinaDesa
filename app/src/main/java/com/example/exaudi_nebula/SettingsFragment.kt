package com.example.exaudi_nebula

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.exaudi_nebula.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. ListView Sederhana dengan ArrayAdapter (Kebutuhan Tugas)
        val listMenu = arrayOf(
            "Panduan Aplikasi",
            "Kebijakan Privasi",
            "Syarat & Ketentuan",
            "Tentang Versi Aplikasi",
            "Pusat Bantuan",
            "Beri Rating Aplikasi"
        )

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            listMenu
        )
        binding.lvSettings.adapter = adapter

        binding.lvSettings.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(requireContext(), "Membuka: ${listMenu[position]}", Toast.LENGTH_SHORT).show()

        }

        // 2. Button Action (Kebutuhan Tugas)
        binding.btnSubmitFeedback.setOnClickListener {
            val feedback = binding.etFeedback.text.toString()
            if (feedback.isNotEmpty()) {
                Toast.makeText(requireContext(), "Terima kasih atas saran Anda!", Toast.LENGTH_SHORT).show()
                binding.etFeedback.text?.clear()
            } else {
                Toast.makeText(requireContext(), "Mohon isi saran terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
