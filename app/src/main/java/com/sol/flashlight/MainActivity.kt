package com.sol.flashlight

import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sol.flashlight.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var flashLightStatus: Boolean = false
    lateinit var cameraManager: CameraManager
    lateinit var cameraId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener(View.OnClickListener {
            openFlashLight()
        })
    }

    override fun onPause() {
        if (flashLightStatus) {
            turnOffFlashLight()
        }
        super.onPause()
    }

    private fun openFlashLight() {
        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        cameraId = cameraManager.cameraIdList[0]
        if (!flashLightStatus) {
            try {
                cameraManager.setTorchMode(cameraId, true)
                flashLightStatus = true

            } catch (e: CameraAccessException) {
            }
        } else {
            turnOffFlashLight()
        }
    }

    private fun turnOffFlashLight() {
        try {
            cameraManager.setTorchMode(cameraId, false)
            flashLightStatus = false
        } catch (e: CameraAccessException) {
        }
    }
}