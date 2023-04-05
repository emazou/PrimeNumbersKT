package com.emazou.primenumbers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
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
@Preview
@Composable
fun Home(
    primeCalculatorModel: PrimeCalculatorModel = PrimeCalculatorModel()
){
    val textShow = remember { mutableStateOf("") }
    val listResult = remember { mutableStateOf(emptyList<Int>()) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF0f172b)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Prime Numbers",
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontSize = 30.sp,
            color = Color(0xFFcfd8e5),
            fontFamily = FontFamily.Monospace,
        )
        Column(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            InputNumber(text= "Enter an integer number", onButtonClicked = {value ->
                val isPrime = primeCalculatorModel.isPrime(value.toInt())
                textShow.value = "The number ${value} ${if (isPrime) "is" else "is not"} prime."
            })
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
                    .align(Alignment.CenterHorizontally),
                text = textShow.value,
                fontSize = 20.sp,
                color = Color(0xFFcfd8e5),
                fontFamily = FontFamily.Monospace,
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
                                text = number.toString(),
                                fontSize = 20.sp,
                                color = Color(0xFFcfd8e5),
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold
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
    val errorMessage = remember{ mutableStateOf("")}
    Column(
        modifier = Modifier
            .padding(20.dp)
            .background(color = Color(0xFFF1e293c), shape = RoundedCornerShape(10))
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.CenterHorizontally),
            text = text,
            fontSize = 20.sp,
            color = Color(0xFFcfd8e5),
            fontFamily = FontFamily.Monospace,
            )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.SpaceBetween
                ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .background(color = Color(0xFF3A4B61)),
                value = inputNumber.value,
                onValueChange = {
                    inputNumber.value = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = TextStyle.Default.copy(fontSize = 20.sp, color = Color(0xFFcfd8e5))
            )
            Button(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(0.9f),
                colors = ButtonDefaults.buttonColors(Color(0xFF78e8bf)),
                onClick = {
                    if (inputNumber.value.toIntOrNull() != null){
                        onButtonClicked(inputNumber.value)
                    }
            }) {
                Text(
                    modifier = Modifier,
                    text = "Calculate",
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Monospace,
                )
            }
        }
    }
}

