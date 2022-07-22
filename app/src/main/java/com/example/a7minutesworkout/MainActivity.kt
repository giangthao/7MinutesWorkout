package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  var binding:ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        val flStartButton :FrameLayout = findViewById(R.id.flStart)
        flStartButton.setOnClickListener {
            Toast.makeText(
                this,
                "Here we are click",
                Toast.LENGTH_LONG
            ).show()
        }

    }
}