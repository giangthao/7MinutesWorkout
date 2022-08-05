package com.example.a7minutesworkout

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.a7minutesworkout.databinding.ActivityBmiactivityBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {
    // create binding for the activity
    private var binding: ActivityBmiactivityBinding? = null

    // TODO(Step 2 : Added variables for METRIC and US UNITS views and a variable for displaying the current selected view..)
    // START
    companion object {
        private const val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW" // Metric Unit View
        private const val US_UNITS_VIEW = "US_UNIT_VIEW" // US Unit View
    }
    private var currentVisibleView: String =
        METRIC_UNITS_VIEW // A variable to hold a value to make a selected view visible

    // END

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarBmiActivity)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATE BMI"
        }
        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            onBackPressed()
        }
        makeVisibleMetricUnitViews()
        binding?.rgUnits?.setOnCheckedChangeListener{ _, checkedId : Int ->
            if(checkedId == R.id.rbMetricUnits){
                makeVisibleMetricUnitViews()
            } else {
                makeVisibleUSMetricUnitViews()
            }

        }
        binding?.btnCalculateUnits?.setOnClickListener {
            calculateUnits()
        }
        binding?.root?.setOnClickListener{
            hideKeyboard()
        }
    }
    private fun validateUSMetricUnits():Boolean {
        var isValid = true
        if (binding?.etUsMetricUnitWeight?.text.toString().isEmpty()){
            isValid = false
        } else if(binding?.etUsMetricUnitHeightFeet?.text.toString().isEmpty()){
            isValid = false
        }
        else if(binding?.etUsMetricUnitHeightInch?.text.toString().isEmpty()){
            isValid = false
        }
            return  isValid
    }
    private fun validateMetricUnits():Boolean {
        var isValid = true
        if (binding?.etMetricUnitWeight?.text.toString().isEmpty()){
            isValid = false
        } else if(binding?.etMetricUnitHeight?.text.toString().isEmpty()){
            isValid = false
        }
            return  isValid
    }
    private fun calculateUnits(){
        if(currentVisibleView == METRIC_UNITS_VIEW){
            if (validateMetricUnits()){
                val weightValue: Float = binding?.etMetricUnitWeight?.text.toString().toFloat()
                val heightValue: Float = binding?.etMetricUnitHeight?.text.toString().toFloat() / 100
                val bmi = weightValue / (heightValue * heightValue)
                displayBMIResult(bmi)
            } else {
                Toast.makeText(this, "You don't input correct value",Toast.LENGTH_LONG).show()
            }
        } else if(currentVisibleView == US_UNITS_VIEW){
            if(validateUSMetricUnits()){
                val usUnitHeightValueFeet: String =
                    binding?.etUsMetricUnitHeightFeet?.text.toString()
                val usUnitHeightValueInch: String =
                    binding?.etUsMetricUnitHeightInch?.text.toString()
                val usUnitWeightValue: Float = binding?.etUsMetricUnitWeight?.text.toString().toFloat()
                // Here the Height Feet and Inch values are merged and multiplied by 12 for convert
                val heightValue =
                    usUnitHeightValueInch.toFloat() + usUnitHeightValueFeet.toFloat() * 12
                val bmi = 703 * (usUnitWeightValue / (heightValue * heightValue))
                displayBMIResult(bmi)
            }else {
                Toast.makeText(this, "You don't input correct value", Toast.LENGTH_LONG).show()
            }
        }
        hideKeyboard()
    }
    private fun displayBMIResult(bmi : Float){
        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0
        ) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0
        ) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0
        ) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (java.lang.Float.compare(bmi, 25f) > 0 && java.lang.Float.compare(
                bmi,
                30f
            ) <= 0
        ) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0
        ) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0
        ) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

        //Use to set the result layout visible
        binding?.llDiplayBMIResult?.visibility = View.VISIBLE

        // This is used to round the result value to 2 decimal values after "."
        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        binding?.tvBMIValue?.text = bmiValue // Value is set to TextView
        binding?.tvBMIType?.text = bmiLabel // Label is set to TextView
        binding?.tvBMIDescription?.text = bmiDescription // Description is set to TextView
    }
    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
    private fun makeVisibleMetricUnitViews(){
        currentVisibleView = METRIC_UNITS_VIEW
        binding?.tilMetricUnitWeight?.visibility = View.VISIBLE
        binding?.tilMetricUnitHeight?.visibility = View.VISIBLE
        binding?.tilUsMetricUnitWeight?.visibility = View.INVISIBLE
        binding?.tilMetricUsUnitHeightFeet?.visibility = View.INVISIBLE
        binding?.tilMetricUsUnitHeightInch?.visibility = View.INVISIBLE
        binding?.etMetricUnitHeight?.text!!.clear()
        binding?.etMetricUnitWeight?.text!!.clear()
    }
    private fun makeVisibleUSMetricUnitViews(){
        currentVisibleView = US_UNITS_VIEW
        binding?.tilMetricUnitWeight?.visibility = View.INVISIBLE
        binding?.tilMetricUnitHeight?.visibility = View.INVISIBLE
        binding?.tilUsMetricUnitWeight?.visibility = View.VISIBLE
        binding?.tilMetricUsUnitHeightFeet?.visibility = View.VISIBLE
        binding?.tilMetricUsUnitHeightInch?.visibility = View.VISIBLE
    }

}