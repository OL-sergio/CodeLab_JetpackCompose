package com.google.tutorial_jetpack_compose.codelab.basicstatecodelab_estadonojetpackcompose.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class WellnessTask(
    val id: Int,
    val label: String,
    val initialChecked: Boolean = false
) {
    var checked : Boolean by mutableStateOf(initialChecked)
}
