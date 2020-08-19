package com.shubham.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton:Button
    private  lateinit var falseButton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)

        trueButton.setOnClickListener { view:View ->
            //Do something for True button
            Toast.makeText(this,
            R.string.correct_toast,
            Toast.LENGTH_SHORT)
                .show()
         }

        falseButton.setOnClickListener { view:View->
            //Do something for false button
            val toast = Toast.makeText(
                this,
                R.string.incorrect_toast,
                Toast.LENGTH_SHORT
            )

            toast.show()
         }
    }
}