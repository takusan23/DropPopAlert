package io.github.takusan23.droppopalertexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.takusan23.droppopalertexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.alertButton.setOnClickListener {
            binding.dropPopAlert.alert("こんにちは世界！")
        }

        binding.dropPopAlert.apply {
            transitionMs = 5000
            alertTextView.textSize = 20f
        }

    }
}