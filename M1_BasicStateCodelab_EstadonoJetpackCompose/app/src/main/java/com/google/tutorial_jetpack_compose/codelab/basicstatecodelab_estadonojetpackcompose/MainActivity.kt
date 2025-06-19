package com.google.tutorial_jetpack_compose.codelab.basicstatecodelab_estadonojetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.tutorial_jetpack_compose.codelab.basicstatecodelab_estadonojetpackcompose.model.WellnessTask
import com.google.tutorial_jetpack_compose.codelab.basicstatecodelab_estadonojetpackcompose.ui.theme.BasicStateCodelab_EstadonoJetpackComposeTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicStateCodelab_EstadonoJetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //WellnessScreen()
                    WellnessScreenViewModel()

                }
            }
        }
    }
}

@Composable
fun WellnessScreen(modifier : Modifier = Modifier) {
    WaterCounter(
        taskName = "Have you taken your 15 minute walk today?",
        modifier = modifier
    )
    StatefulCounter(modifier)
}

@Composable
fun WaterCounter(  taskName: String,modifier: Modifier = Modifier ) {
    Column( modifier = modifier.padding(16.dp) ) {
        val count  = rememberSaveable { mutableIntStateOf(0) } // This should be a state variable in a real app
        if(count.value > 0){
          var showTaskItem by remember { mutableStateOf(true) }// This should be a state variable in a real app
           if (showTaskItem){
                WellnessTaskItem(
                    taskName = taskName,
                    onClose = { count.value = 0 }, // Reset the count when closed
                    modifier = modifier,
                )
            }

            Text(
                text = "You're had ${count.value} glasses.",
                modifier = modifier.padding(16.dp) // Add padding to the text
            )

        }
        Row (
            Modifier.padding(top = 8.dp)
            ){
            Button (
                enabled = count.value < 10,
                onClick = { count.value++ }, // This should update the state variable in a real app
            ) {
                Text(text = "Add a glass")
            }

           Button(
                onClick = { count.value = 0 },
                Modifier.padding(start = 8.dp) // Add padding to the button
            ) {
                Text(text = "Clear water count")
            }
        }
    }
}

@Composable
fun WellnessTaskItem(
    taskName: String,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = taskName,
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        )

        IconButton(
            onClick = onClose,
            modifier = modifier.padding(16.dp)
        ) {
            Icon(Icons.Default.Close, contentDescription = "Close")
        }
    }
}

//fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }








@Preview
@Composable
fun WellnessTaskItemPreview() {
    BasicStateCodelab_EstadonoJetpackComposeTheme {
        WellnessTaskItem(
            taskName = "Have you taken your 15 minute walk today?",
            onClose = { /* No-op */ }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WaterCounterPreview() {
    BasicStateCodelab_EstadonoJetpackComposeTheme {
        WellnessScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun WellnessScreenViewModelPreview() {
    BasicStateCodelab_EstadonoJetpackComposeTheme {
        WellnessScreenViewModel()
    }
}