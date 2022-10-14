package com.vishal.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vishal.unitconverter.ui.theme.UnitConverterTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainApp()
                }
            }
        }
    }
}

@Composable
fun MainApp() {

    var userInput by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 120.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {

        //app title
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = MaterialTheme.typography.h3.fontSize
        )

        Spacer(modifier = Modifier.height(24.dp))

        // edit text field
        TextField(
            value = userInput,
            onValueChange = { userInput = it },
            placeholder = { Text(text = "Enter Value") })

        //convert distance button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Button(onClick = { result = changeDistance(isKm = true, userInput) }) {
                Text(text = "Km to m")
            }

            Button(onClick = {  result = changeDistance(isKm = false, userInput) }) {
                Text(text = "m to Km")
            }
        }

        //convert currency button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Button(onClick = {  result = changeCurrency(isUSD = false, userInput) }) {
                Text(text = "INR to USD")
            }

            Button(onClick = { result =  changeCurrency(isUSD = true, userInput) }) {
                Text(text = "USD to INR")
            }
        }

        //convert time button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Button(onClick = { result =  changeTime(isH = true, userInput) }) {
                Text(text = "hours to min")
            }

            Button(onClick = {  result = changeTime(isH = false, userInput) }) {
                Text(text = "min to hours")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        //result
        Text(
            text = stringResource(R.string.result, result),
            fontSize = MaterialTheme.typography.h4.fontSize,
            textAlign = TextAlign.Center
        )


    }
}

fun changeDistance(isKm: Boolean, userInput: String):String {
    return (if (isKm) {
        userInput.toInt() * 1000
    } else {
        userInput.toInt() / 1000
    }).toString()
}

fun changeCurrency(isUSD: Boolean, userInput: String):String {
    return (if (isUSD) {
        userInput.toInt() * 80
    } else {
        userInput.toInt() / 80
    }).toString()
}

fun changeTime(isH: Boolean, userInput: String):String {
    return (if (isH) {
        userInput.toInt() * 60
    } else {
        userInput.toInt() / 60
    }).toString()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UnitConverterTheme {
        MainApp()
    }
}