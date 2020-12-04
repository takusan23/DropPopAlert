package io.github.takusan23.droppopalertexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import io.github.takusan23.droppopalert.DropPopAlertView
import io.github.takusan23.droppopalertexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBindingを利用している。Kotlin Android Extensionsが非推奨になったので
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 5秒
        binding.dropPopAlertUpView.showTimeMs = 5 * 1000

        // 表示中かどうか
        val isAlerted = binding.dropPopAlertUpView.visibility == View.VISIBLE

        // 基本的な使い方
        binding.dropUpButton.setOnClickListener {
            // 表示/非表示を切り替える
            if (binding.dropPopAlertUpView.isVisible) {
                binding.dropPopAlertUpView.hideAlert(DropPopAlertView.ALERT_UP)
            } else {
                binding.dropPopAlertUpView.showAlert(DropPopAlertView.ALERT_UP)
            }
        }

        // 表示、非表示関数も有るので任意のタイミングで非表示にもできる
        binding.dropDownButton.setOnClickListener {
            binding.dropPopAlertDownView.showAlert(DropPopAlertView.ALERT_DROP)
        }
        binding.dropPopAlertDownViewCloseButton.setOnClickListener {
            binding.dropPopAlertDownView.hideAlert(DropPopAlertView.ALERT_DROP)
        }

    }
}