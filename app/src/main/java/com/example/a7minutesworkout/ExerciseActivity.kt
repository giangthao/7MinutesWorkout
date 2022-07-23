package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a7minutesworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    //Todo 1 Create 1 binding variable
    private var binding: ActivityExerciseBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Todo 2 inflate the layout
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        //Todo 3 pass in binding?.root in the content view
        setContentView(binding?.root)
        //Todo 5 then set support action bả and get tooBarExerciser using the binding variable
        setSupportActionBar(binding?.toolbarExcise)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarExcise?.setNavigationOnClickListener {
            onBackPressed()
        }
    }

}