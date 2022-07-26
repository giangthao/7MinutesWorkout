package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    //Todo 1 Create 1 binding variable
    private var binding: ActivityExerciseBinding? = null
    private var restTimer: CountDownTimer? =null
    private var restProgress = 0


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
        setupRestView()
    }
    private fun setupRestView(){
        if(restTimer !=null){
            restTimer?.cancel()
            restProgress =0
        }
        setRestProgressBar()
    }
    private fun setRestProgressBar() {
        binding?.progressBar?.progress = restProgress

        restTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding?.progressBar?.progress = 10 - restProgress
                binding?.tvTimer?.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(
                    this@ExerciseActivity,
                    "Here now we will start the exercise.",
                    Toast.LENGTH_LONG
                ).show()

            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(restTimer !=null){
            restTimer?.cancel()
            restProgress =0
        }
        binding = null
    }

}