package com.example.converter

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.converter.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.math.RoundingMode
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityMainBinding

    val KMTOMI = 0.62
    val MITOKM = 1.61
    val CMTOINCH = 0.39
    val INCHTOCM = 2.54
    val KGTOLB = 2.2
    val LBTOKG = 0.45
    val GTOOZ = 0.04
    val OZTOG = 28.35
    private var selectedType = 0.0
    private var selectedTypeText = ""
    private var valueToConvert = 0.0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)


    }

    fun onRadioButtonClicked(view: View) {

        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

//             Check which radio button was clicked
            when (view.getId()) {
                binding.KMTOMI.id ->
                    if (checked) {
                        selectedType = KMTOMI
                        selectedTypeText = getString(R.string.KM_TO_MI)
                    }
                binding.MITOKM.id ->
                    if (checked) {
                        selectedType = MITOKM
                        selectedTypeText = getString(R.string.MI_TO_KM)

                    }

                binding.CMTOIN.id ->
                    if (checked) {
                        selectedType = CMTOINCH
                        selectedTypeText = getString(R.string.CM_TO_IN)
                    }


                binding.INTOCM.id ->
                    if (checked) {
                        selectedType = INCHTOCM
                        selectedTypeText = getString(R.string.IN_TO_CM)
                    }

                binding.KGTOLIB.id ->
                    if (checked) {
                        selectedType = KGTOLB
                        selectedTypeText = getString(R.string.KG_TO_LIB)
                    }
                binding.LIBTOKG.id ->
                    if (checked) {
                        selectedType = LBTOKG
                        selectedTypeText = getString(R.string.LIB_TO_KG)
                    }

                binding.GTOOZ.id ->
                    if (checked) {
                        selectedType = GTOOZ
                        selectedTypeText = getString(R.string.G_TO_OZ)
                    }

                binding.OZTOG.id ->
                    if (checked) {
                        selectedType = OZTOG
                        selectedTypeText = getString(R.string.OZ_TO_G)
                    }


            }
            }

        Toast.makeText(
            this,
            "Conversion for $selectedTypeText selected",
            Toast.LENGTH_SHORT
        ).show()

    }


    fun convertMeasurementValue(view: View) {
        try {

            val simpleEditText = findViewById<View>(binding.valueToConvert.id) as EditText
            valueToConvert = simpleEditText.text.toString().toDouble()
//            validdate input
            if (simpleEditText == null || valueToConvert <= 0 || selectedType == 0.0) {
                var snackbar: Snackbar
                snackbar = Snackbar.make(view, "Enter a valid value and select conversion type", Snackbar.LENGTH_SHORT);
                val snackBarView = snackbar.view
                snackBarView.setBackgroundColor(Color.parseColor("#FF0000"))
                snackbar.show()
            }
            else {
                convertValue(selectedType)
            }

        } catch(e:NumberFormatException) {
            var snackbar: Snackbar
            snackbar = Snackbar.make(view, "Something is not right, check value entered", Snackbar.LENGTH_SHORT);
            val snackBarView = snackbar.view
            snackBarView.setBackgroundColor(Color.parseColor("#FF0000"))
            snackbar.show()
        }

    }
    private fun convertValue(baseValue: Double) {
        var result: Double = valueToConvert * baseValue
//        format to 2 decimal places
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        var formatted = df.format(result)
        showDialog(formatted)

    }

     private fun showDialog(result: String) {
        val alert: AlertDialog.Builder = AlertDialog.Builder(this)
        alert.setTitle("Converted value from $selectedTypeText is $result")
        // alert.setMessage("Message");
        alert.setPositiveButton("Ok",
            DialogInterface.OnClickListener { dialog, whichButton ->

            })

        alert.show()
    }
}