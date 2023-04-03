package com.emazou.primenumbers

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val primeCalculatorModel = PrimeCalculatorModel()
        super.onCreate(savedInstanceState)
        setContent {
            Home(primeCalculatorModel)
        }
    }
}

@Composable
fun Home(
    primeCalculatorModel: PrimeCalculatorModel = PrimeCalculatorModel()
){
    val textShow = remember { mutableStateOf("") }
    val listResult = remember { mutableStateOf(emptyList<Int>()) }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            InputNumber(text= "Enter an integer number", onButtonClicked = {value ->
                val isPrime = primeCalculatorModel.isPrime(value.toInt())
                textShow.value = "The number ${if (isPrime) "is" else "is not"} prime."
            })
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                text = textShow.value
            )
            InputNumber(text="Enter the amount of prime numbers ", onButtonClicked = {value ->
                listResult.value = primeCalculatorModel.giveMeXPrime(value.toInt())
            })
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                itemsIndexed(listResult.value.chunked(5)) { index, numbers ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        numbers.forEach { number ->
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(10.dp),
                                text = number.toString()
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun InputNumber(
    text: String = "Title",
    onButtonClicked: (String) -> Unit
) {
    val inputNumber = remember{ mutableStateOf("")}
    Column(
        modifier = Modifier
            .padding(all = 20.dp)
    ) {
        Text(
            modifier = Modifier,
            text = text,
            fontSize = 25.sp,
            )
        Row(
        ) {
            TextField(
                modifier = Modifier,
                value = inputNumber.value,
                onValueChange = {
                    inputNumber.value = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Button(
                modifier = Modifier
                    .padding(start = 15.dp),
                onClick = {
                    onButtonClicked(inputNumber.value)
            }) {
                Icon(Icons.Rounded.Send, contentDescription = "Send" )
            }
        }
    }
}

