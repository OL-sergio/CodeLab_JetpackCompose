package com.google.tutorial_jetpack_compose.codelab.basicstatecodelab_estadonojetpackcompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp



@Composable
fun StatefulCounter(
    modifier: Modifier = Modifier
) {
    var waterCount by rememberSaveable { mutableIntStateOf(0) } // This should be a state variable in a real app

    StatelessCounter(
        count = waterCount,
        onIncrement = { waterCount++ },
        modifier = modifier
    )
}

@Composable
fun StatelessCounter(
    count: Int,
    onIncrement: () -> Unit,
    modifier: Modifier = Modifier,

    ) {
    Column(modifier = modifier.padding(top = 42.dp)) {
        if (count > 0) {
            Text(
                text = "You've had $count glasses.",
                modifier = Modifier.padding(16.dp) // Add padding to the text
            )
        }
            Button(
                onClick = onIncrement,
                modifier = Modifier.padding(top = 8.dp), // Add padding to the button
                enabled = count < 10 // Disable button if count is 10 or more
            ) {
                Text(text = "Add a glass")

        }
    }
}


