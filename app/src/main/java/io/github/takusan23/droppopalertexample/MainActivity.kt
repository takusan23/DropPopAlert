package io.github.takusan23.droppopalertexample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import io.github.takusan23.droppopalert.DropPopAlert
import io.github.takusan23.droppopalert.toDropPopAlert
import io.github.takusan23.droppopalertexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBindingを利用している。Kotlin Android Extensionsが非推奨になったので
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 5秒
        binding.dropPopAlertUpView.dropPopAlert.showTimeMs = 5 * 1000

        // 表示中かどうか
        val isAlerted = binding.dropPopAlertUpView.visibility == View.VISIBLE

        // 基本的な使い方
        binding.dropUpButton.setOnClickListener {
            // 表示/非表示を切り替える
            if (binding.dropPopAlertUpView.isVisible) {
                binding.dropPopAlertUpView.hideAlert(DropPopAlert.ALERT_UP)
            } else {
                binding.dropPopAlertUpView.showAlert(DropPopAlert.ALERT_UP)
            }
        }

        // 表示、非表示関数も有るので任意のタイミングで非表示にもできる
        binding.dropDownButton.setOnClickListener {
            binding.dropPopAlertDownView.showAlert(DropPopAlert.ALERT_DROP)
        }
        binding.dropPopAlertDownViewCloseButton.setOnClickListener {
            binding.dropPopAlertDownView.hideAlert(DropPopAlert.ALERT_DROP)
        }

        // Viewが一つの場合は、DropPopAlertViewを利用せずに、DropPopAlertを利用したほうがいい
        val simpleDropPopAlert = binding.dropPopAlertSimple.toDropPopAlert()
        binding.dropSimpleButton.setOnClickListener {
            simpleDropPopAlert.alert(DropPopAlert.ALERT_DROP)
        }

    }
}