package com.aaliyah.waterbottle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aaliyah.waterbottle.ui.theme.WaterBottleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaterBottleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    var usedWaterAmount by remember {
                        mutableStateOf(0)
                    }
                    val totalWaterAmount = remember {
                        2500
                    }

                    var buttonText = if (usedWaterAmount >= totalWaterAmount) "The bottle is empty" else "Drink"
                    var buttonClickable = usedWaterAmount < totalWaterAmount

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        WatterBottle(
                            totalWaterAmount = totalWaterAmount,
                            unit = "ml",
                            usedWaterAmount = usedWaterAmount
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "Bottle size: $totalWaterAmount",
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(
                            modifier = Modifier.width(300.dp).height(50.dp),
                            onClick = {
                                if (usedWaterAmount < totalWaterAmount) {
                                    usedWaterAmount += 250
                                    if (usedWaterAmount >= totalWaterAmount) {
                                        buttonClickable = false
                                    }
                                }
                            },
                            enabled = buttonClickable,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (buttonClickable) Color(0xff279EFF) else Color.Gray,
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                text = buttonText,
                                style = TextStyle(fontSize = 20.sp)
                            )
                        }
                    }
                }
            }
        }
    }
}