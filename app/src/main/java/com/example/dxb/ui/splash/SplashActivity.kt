package com.example.dxb.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.dxb.MainActivity
import com.example.dxb.R
import com.example.dxb.ui.dashboard.DashboardActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this,DashboardActivity::class.java))

        },3000.toLong())
    }
}