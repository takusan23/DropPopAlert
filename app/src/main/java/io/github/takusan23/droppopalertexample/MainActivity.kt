package io.github.takusan23.droppopalertexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.takusan23.droppopalert.DropPopAlertView
import io.github.takusan23.droppopalertexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dropDownButton.setOnClickListener {
            binding.dropPopAlertDownView.alert(DropPopAlertView.ALERT_DROP)
        }

        binding.dropUpButton.setOnClickListener {
            binding.dropPopAlertUpView.alert(DropPopAlertView.ALERT_UP)
        }

    }
}