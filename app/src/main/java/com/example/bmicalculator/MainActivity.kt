package com.example.bmicalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmicalculator.ui.theme.BMICalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMICalculatorTheme {
                    CalcBMI()
                }
            }
        }
    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalcBMI() {
    var gender by remember { mutableIntStateOf(0) }
    var result by remember { mutableStateOf("") }
    var sliderPosition by remember { mutableFloatStateOf(1f) }
    var sliderPosition2 by remember { mutableFloatStateOf(1f) }
    var expanded by remember { mutableStateOf (false) }
    Box(contentAlignment = Alignment.Center) {
        LazyColumn(
                Modifier
                    .background(Color(0xFFe0ebba))
                    .padding(20.dp)
                    .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "BMI Calculator", fontSize = 40.sp, fontWeight = FontWeight.Bold,
                    color = Color(0xFF38631d)
                )
                Spacer(Modifier.padding(15.dp))
                Row(
                    Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    var selected by remember { mutableStateOf(false) }
                    val pickColor = if (selected) 0xFFebe96b else 0xFF96d263
                    var selected2 by remember { mutableStateOf(false) }
                    val pickColor2 = if (selected2) 0xFFebe96b else 0xFF96d263
                    Button(
                        onClick = {
                            selected = !selected
                            if (selected2 == true) {
                                selected2 = !selected2
                            }
                            gender = 1
                        }, Modifier.size(170.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(pickColor))
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.male),
                            contentDescription = "male", tint = Color.Unspecified
                        )
                    }
                    Spacer(Modifier.weight(1f))
                    Button(
                        onClick = {
                            selected2 = !selected2
                            if (selected == true) {
                                selected = !selected
                            }
                            gender = 2
                        }, Modifier.size(170.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(pickColor2))
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.female),
                            contentDescription = "female", tint = Color.Unspecified
                        )
                    }
                }

                Text(
                    text = "Weight", fontSize = 35.sp, fontWeight = FontWeight.Bold,
                    color = Color(0xFF4d3316)
                )
                Slider(
                    valueRange = 1f..200f,
                    value = sliderPosition,
                    colors = SliderDefaults.colors(
                        thumbColor = Color(0xFF96d263),
                        activeTrackColor = Color(0xFF17501c),
                        inactiveTrackColor = Color(0xFFb7e8b0),
                    ),
                    onValueChange = { sliderPosition = it }
                )
                Text(
                    text = "%.0f".format(sliderPosition) + "kg", fontSize = 25.sp,
                    fontWeight = FontWeight.Bold, color = Color(0xFF4d3316)
                )
                Spacer(Modifier.padding(20.dp))
                Text(
                    text = "Height", fontSize = 35.sp, fontWeight = FontWeight.Bold,
                    color = Color(0xFF4d3316)
                )
                Slider(
                    valueRange = 1f..2f,
                    value = sliderPosition2,
                    colors = SliderDefaults.colors(
                        thumbColor = Color(0xFF96d263),
                        activeTrackColor = Color(0xFF17501c),
                        inactiveTrackColor = Color(0xFFb7e8b0),
                    ),
                    onValueChange = { sliderPosition2 = it }
                )
                Text(
                    text = "%.2f".format(sliderPosition2) + "m", fontSize = 25.sp,
                    fontWeight = FontWeight.Bold, color = Color(0xFF4d3316)
                )
                Spacer(Modifier.padding(20.dp))
                var age by remember { mutableStateOf("") }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Age", fontSize = 35.sp, fontWeight = FontWeight.Bold,
                            color = Color(0xFF4d3316),
                            modifier = Modifier.padding(start = 5.dp)
                        )
                        Spacer(Modifier.padding(10.dp))
                        TextField(
                            value = age, onValueChange = { age = it },
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            maxLines = 1,
                            modifier = Modifier
                                .size(80.dp)
                                .border(width = 2.dp, color = Color(0xFF96d263)),
                            textStyle = TextStyle.Default.copy(
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF4d3316),
                                textAlign = TextAlign.Center,
                            ),
                            colors = TextFieldDefaults.colors(
                                cursorColor = Color.Transparent,
//                                containerColor = Color(0xFFb7e8b0),
                            )
                        )
                    }
                    Spacer(Modifier.padding(20.dp))
                    Button(
                        onClick = {
                            result = OnSubmit(sliderPosition, sliderPosition2, gender, age)
                            if (expanded == false) {
                                expanded = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF96d263)),
                        modifier = Modifier
                            .size(height = 150.dp, width = 170.dp)
                            .padding(start = 30.dp, top = 55.dp)
                            .border(
                                width = 3.dp,
                                color = Color(0xFFebe96b)
                            ),
                        shape = RoundedCornerShape(0)
                    ) {
                        Text(
                            text = "Calculate BMI", fontWeight = FontWeight.Bold, fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
//            Text(text = result, fontSize = 25.sp)
            }
        }
        var bmi = sliderPosition/(sliderPosition2*sliderPosition2)
        var col = 0xFFFFFFFF
        var img = R.drawable.alrert
        if (bmi < 18.5){
            col = 0xFFe3e135
            img = R.drawable.alrert
        }
        else if(18.5 <= bmi && bmi <= 24.9){
            col = 0xFF79e95c
            img = R.drawable.healthy
        }
        else if(25 <= bmi && bmi <= 29.9){
            col = 0xFFe2a72b
            img = R.drawable.alrert
        }
        else if(30 <= bmi){
            col = 0xFFd72f1d
            img = R.drawable.alrert
        }
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { expanded = false },
            colors = CardDefaults.cardColors(
                containerColor = Color(col))
        ) {
            // Card content goes here, at this state a column to hold items
            if (expanded) {
                Image(
                    painter = painterResource(id = img), contentDescription = "",
                    Modifier
                        .size(70.dp)
                        .padding(5.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "Report", fontSize = 30.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = result, fontSize = 25.sp,
                    modifier = Modifier.padding(10.dp, bottom = 20.dp)
                )
            }

        }
    }
}




fun OnSubmit(w: Float, h: Float, g: Int, a: String): String{
    var gender = "Unknown"
    var age = a
    if (g == 1){
        gender = "Male"
    }
    else if(g == 2){
        gender = "Female"
    }
    var bmi = w/(h*h)
    var bmir = "%.0f".format(bmi)
    var classification = ""
    if (bmi < 18.5){
        classification = "Underweight"

    }
    else if(18.5 <= bmi && bmi <= 24.9){
        classification = "Normal Weight"
    }
    else if(25 <= bmi && bmi <= 29.9){
        classification = "Overweight"
    }
    else if(30 <= bmi && bmi <= 34.9){
        classification = "Obesity Class I"
    }
    else if(35 <= bmi && bmi <= 39.9){
        classification = "Obesity Class II"
    }
    else if(40 <= bmi){
        classification = "Obesity Class III"
    }
    return """Gender: ${gender}
        |
        |Age: ${age}
        |
        |BMI: ${bmir} 
        |
        |Classification: ${classification}""".trimMargin()
}