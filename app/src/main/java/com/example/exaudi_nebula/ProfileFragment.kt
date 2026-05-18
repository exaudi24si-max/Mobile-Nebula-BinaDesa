package com.example.exaudi_nebula

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.exaudi_nebula.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Efek Animasi Masuk (Fade In & Slide Up)
        binding.cvProfileImage.alpha = 0f
        binding.cvProfileImage.translationY = 50f
        binding.cvProfileImage.animate().alpha(1f).translationY(0f).setDuration(800).start()

        binding.cardAboutMe.alpha = 0f
        binding.cardAboutMe.translationY = 100f
        binding.cardAboutMe.animate().alpha(1f).translationY(0f).setDuration(1000).setStartDelay(200).start()

        binding.cardContact.alpha = 0f
        binding.cardContact.translationY = 100f
        binding.cardContact.animate().alpha(1f).translationY(0f).setDuration(1000).setStartDelay(400).start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
