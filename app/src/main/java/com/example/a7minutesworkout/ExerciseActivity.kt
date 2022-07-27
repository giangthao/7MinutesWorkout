package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    //Todo 1 Create 1 binding variable
    private var binding: ActivityExerciseBinding? = null

    private var restTimer: CountDownTimer? =null
    private var restProgress = 0

    private var exerciseTimer: CountDownTimer? =null
    private var exerciseProgress = 0

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePostion = -1



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
        exerciseList = Constants.defaultExerciseList()
        binding?.toolbarExcise?.setNavigationOnClickListener {
            onBackPressed()
        }
        binding?.flRestView?.visibility = View.GONE
        setupRestView()
    }
    private fun setupRestView(){
        binding?.flRestView?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.flExerciseView?.visibility = View.INVISIBLE
        binding?.ivImage?.visibility = View.INVISIBLE
        if(restTimer !=null){
            restTimer?.cancel()
            restProgress =0
        }
        setRestProgressBar()
    }
    private fun setupExerciseView(){
        binding?.flRestView?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility = View.INVISIBLE
        binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.flExerciseView?.visibility = View.VISIBLE
        binding?.ivImage?.visibility = View.VISIBLE
        if(exerciseTimer !=null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePostion].getImage())
        binding?.tvExerciseName?.text = exerciseList!![currentExercisePostion].getName()
        setExerciseProgressBar()
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
                currentExercisePostion++
                setupExerciseView()

            }

        }.start()
    }
    private fun setExerciseProgressBar() {
        binding?.progressBarExercise?.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                binding?.progressBarExercise?.progress = 30 - exerciseProgress
                binding?.tvTimerExercise?.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {
                if(currentExercisePostion < exerciseList!!.size -1 ){
                    setupRestView()
                } else {
                    Toast.makeText(
                        this@ExerciseActivity,
                        "Congratulations ! You are completed the 7 minutes workout. ",
                        Toast.LENGTH_LONG
                    ).show()
                }
                setRestProgressBar()

            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(restTimer !=null){
            restTimer?.cancel()
            restProgress =0
        }
        if(exerciseTimer !=null){
            exerciseTimer?.cancel()
            exerciseProgress =0
        }
        binding = null
    }

}