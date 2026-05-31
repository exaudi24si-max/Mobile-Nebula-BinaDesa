package com.example.exaudi_nebula

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.exaudi_nebula.FormLogin.LoginMainActivity
import com.example.exaudi_nebula.tutorial.TutorialMessageActivity
import com.example.exaudi_nebula.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)

        Handler(Looper.getMainLooper()).postDelayed({
            val isLogin = sharedPreferences.getBoolean("isLogin", false)

            // Diarahkan ke BaseActivity sebagai Dashboard Utama dengan Bottom Navigation
            val intent = if (isLogin) {
                Intent(this, BaseActivity::class.java)
            } else {
                Intent(this, TutorialMessageActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, 2000)
    }
}
