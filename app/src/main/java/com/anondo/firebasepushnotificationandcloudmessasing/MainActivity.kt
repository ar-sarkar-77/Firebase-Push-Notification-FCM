package com.anondo.firebasepushnotificationandcloudmessasing

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.privacysandbox.ads.adservices.topics.GetTopicsRequest
import com.anondo.firebasepushnotificationandcloudmessasing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        askNotificationPermission()


    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {

        } else {

        }
    }

    private fun askNotificationPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {

            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {

                AlertDialog.Builder(this)
                    .setTitle("Notification Permission")
                    .setMessage("You should give permission to get alert notificatio")
                    .setPositiveButton("Yes") {dialog, _->
                        requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        dialog.dismiss()
                    }
                    .setNegativeButton("No") {dialog, _->
                        dialog.dismiss()
                    }
                    .create()
                    .show()

            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }


}