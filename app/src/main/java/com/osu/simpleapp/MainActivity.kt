package com.osu.simpleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.osu.simpleapp.ui.theme.SimpleAppTheme
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    var a by remember { mutableStateOf(TextFieldValue()) }
    var b by remember { mutableStateOf(TextFieldValue()) }
    var c by remember { mutableStateOf(TextFieldValue()) }
    var result by remember { mutableStateOf<String>("") }

    fun calculateRoots() {
        val aValue = a.text.toDoubleOrNull()
        val bValue = b.text.toDoubleOrNull()
        val cValue = c.text.toDoubleOrNull()

        if (aValue != null && bValue != null && cValue != null) {
            val discriminant = bValue * bValue - 4 * aValue * cValue
            if (discriminant > 0) {
                val root1 = (-bValue + sqrt(discriminant)) / (2 * aValue)
                val root2 = (-bValue - sqrt(discriminant)) / (2 * aValue)
                result = "Roots are: $root1 and $root2"
            } else if (discriminant == 0.0) {
                val root = -bValue / (2 * aValue)
                result = "Single root: $root"
            } else {
                result = "No real roots"
            }
        } else {
            result = "Please enter valid numbers for a, b, and c"
        }
    }

    Column(modifier = modifier.padding(16.dp)) {
        Text("Quadratic Equation Solver", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = a,
            onValueChange = { a = it },
            label = { Text("Enter coefficient a") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = b,
            onValueChange = { b = it },
            label = { Text("Enter coefficient b") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = c,
            onValueChange = { c = it },
            label = { Text("Enter coefficient c") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { calculateRoots() }, modifier = Modifier.fillMaxWidth()) {
            Text("Calculate Roots")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = result,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleAppTheme {
        CalculatorScreen()
    }
}
