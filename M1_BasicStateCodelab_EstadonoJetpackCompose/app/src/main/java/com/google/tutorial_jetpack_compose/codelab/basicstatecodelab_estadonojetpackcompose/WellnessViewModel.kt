package com.google.tutorial_jetpack_compose.codelab.basicstatecodelab_estadonojetpackcompose

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.google.tutorial_jetpack_compose.codelab.basicstatecodelab_estadonojetpackcompose.model.WellnessTask


class WellnessViewModel : ViewModel() {

    private val _tasks = getWellnessTasks().toMutableStateList()
    val tasks: List<WellnessTask>
        get() = _tasks

    fun changeTaskChecked(item: WellnessTask, checked: Boolean) {
            tasks.find { it.id == item.id} ?.let { tasks ->
                tasks.checked = checked

        }
    }

    fun remove(task: WellnessTask) {
        _tasks.remove(task)
    }

    private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }

}