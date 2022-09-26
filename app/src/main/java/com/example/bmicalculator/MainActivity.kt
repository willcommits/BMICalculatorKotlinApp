package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weightText=findViewById<EditText>(R.id.etWeight)
        val heightText=findViewById<EditText>(R.id.etHeight)
        val calcButton=findViewById<Button>(R.id.btnCalculate)
        calcButton.setOnClickListener(){
            val weight=weightText.text.toString()
            val height=heightText.text.toString()
           if(validateInput(weight,height)){
               val bmi=weight.toFloat()/((height.toFloat()/100)*(height.toFloat()/100))
               //format to 2 decimal places
               val formatedBmi=String.format("%.2f",bmi.toFloat())
               displayResult(bmi)
           }
        }


    }
    private fun displayResult(bmi:Float){
        val resultIndex=findViewById<TextView>(R.id.tvIndex)
        val resultDescription=findViewById<TextView>(R.id.tvResult)
        val info=findViewById<TextView>(R.id.tvInfo)
        resultIndex.text=bmi.toString()
        var color=0
        var resultText=""
        info.text= "( Normal Range is 18.5 - 24.9 )"
        when{
            bmi<18.50->{
                resultText="Underweight"
                color=R.color.underweight
            }
            bmi in 18.50..24.99->{
                resultText="Healthy"
                color=R.color.normal
            }
            bmi in 25.00..29.99->{
                resultText="Overweight"
                color=R.color.over_weight
            }
            bmi >29.99->{
                resultText="Obese"
                color=R.color.obese

            }
        }
        resultDescription.setTextColor(ContextCompat.getColor(this,color))
        resultDescription.text=resultText

    }

    private fun validateInput(weight:String?,Height:String?):Boolean{
        return when{
            weight.isNullOrEmpty()->{
                Toast.makeText(this,"Weight is empty",Toast.LENGTH_SHORT).show()
                if(Height.isNullOrEmpty()){
                    Toast.makeText(this,"Height is empty",Toast.LENGTH_SHORT).show()
                }
                return false
            }
            Height.isNullOrEmpty()->{
                Toast.makeText(this,"Height is empty",Toast.LENGTH_SHORT).show()
                if(weight.isNullOrEmpty()){
                    Toast.makeText(this,"Weight is empty",Toast.LENGTH_SHORT).show()
                }
                return false
            }
            else->{
                return true
            }
        }
    }

}