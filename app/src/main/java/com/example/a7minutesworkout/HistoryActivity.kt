package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.a7minutesworkout.databinding.ActivityBmiactivityBinding
import com.example.a7minutesworkout.databinding.ActivityExerciseBinding
import com.example.a7minutesworkout.databinding.ActivityHistoryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    // create a binding for the layout
    private var binding: ActivityHistoryBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //inflate the layout
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        //Todo 3 pass in binding?.root in the content view
        // bind the layout to this activity
        setContentView(binding?.root)
        //Todo 5 then set support action bả and get tooBarExerciser using the binding variable
        //Setting up the action bar in the History Screen Activity and
// adding a back arrow button and click event for it.)
// START
        setSupportActionBar(binding?.toolbarHistoryActivity)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true) //set back button
            supportActionBar?.title = "HISTORY"// Setting a title in the action bar.
        }

        binding?.toolbarHistoryActivity?.setNavigationOnClickListener {
            onBackPressed()
        }
        val dao = (application as WorkOutApp).db.historyDao()
        getAllCompletedDates(dao)
    }
    // TODO(Step 2 : Created a function to get the list of completed dates from the History Table.)
    // START
    /**
     * Function is used to get the list exercise completed dates.
     */
    private fun getAllCompletedDates(historyDao: HistoryDao){
        lifecycleScope.launch {
            historyDao.fetchAllEmployees().collect(){ allCompleteDatesList->
                //List items are printed in log
                for (i in allCompleteDatesList){
                    Log.e("Date: ","" +i)
                }


            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //reset the binding to null to avoid memory leak
        binding = null
    }
}