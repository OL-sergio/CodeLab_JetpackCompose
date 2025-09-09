/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.wearable.composeforwearos

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Message
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.SelfImprovement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material3.AppCard
import androidx.wear.compose.material3.AppScaffold
import androidx.wear.compose.material3.Button
import androidx.wear.compose.material3.FilledIconButton
import androidx.wear.compose.material3.Icon
import androidx.wear.compose.material3.ListHeader
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.ScreenScaffold
import androidx.wear.compose.material3.SurfaceTransformation
import androidx.wear.compose.material3.SwitchButton
import androidx.wear.compose.material3.Text
import androidx.wear.compose.material3.lazy.rememberTransformationSpec
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import com.example.android.wearable.composeforwearos.theme.WearAppTheme
import com.google.android.horologist.compose.layout.ColumnItemType
import com.google.android.horologist.compose.layout.rememberResponsiveColumnPadding

/* Contains individual Wear OS demo composables for the code lab. */

// TODO: Create a Icon Button Composable
@Composable
fun IconButtonExample(
    modifier: Modifier = Modifier,
) {
    FilledIconButton(
        onClick = { /* ... */ },
        modifier = modifier,
    ) {
        Icon(
            imageVector = Icons.Rounded.Phone,
            contentDescription = "triggers phone action",
        )
    }
}

// TODO: Create a Text Composable
@Composable
fun TextExample(modifier: Modifier = Modifier, transformation: SurfaceTransformation) {
    ListHeader(
        modifier = modifier,
        transformation = transformation,
    ) {
        Text(
            modifier = modifier,
            textAlign = TextAlign.Center,
            text = stringResource(R.string.hello_compose_codelab),
        )
    }
}

// TODO: Create a Card (specifically, an AppCard) Composable
@Composable
fun CardExample(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    transformation: SurfaceTransformation,
) {
    AppCard(
        modifier = modifier,
        transformation = transformation,
        appImage = {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.Message,
                contentDescription = "triggers open message action",
                modifier = iconModifier,
            )
        },
        appName = { Text("Messages") },
        time = { Text("12m") },
        title = { Text("Kim Green") },
        onClick = { /* ... */ },
    ) {
        Text("On my way!")
    }
}

// TODO: Create a Chip Composable
@Composable
fun ChipExample(
    modifier: Modifier = Modifier,
    transformation: SurfaceTransformation,
) {
    Button(
        modifier = modifier,
        transformation = transformation,
        onClick = { /* ... */ },
        icon = {
            Icon(
                imageVector = Icons.Rounded.SelfImprovement,
                contentDescription = "triggers meditation action",
            )
        },
    ) {
        Text(
            text = "5 minute Meditation",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

// TODO: Create a Switch Chip Composable
@Composable
fun SwitchChipExample(modifier: Modifier = Modifier, transformation: SurfaceTransformation) {
    var checked by remember { mutableStateOf(true) }
    SwitchButton(
        modifier = modifier,
        label = {
            Text(
                "Sound",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.semantics {
                    this.contentDescription = if (checked) "On" else "Off"
                },
            )
        },
        checked = checked,
        onCheckedChange = { checked = it },
        enabled = true,
    )
}

// Function only used as a demo for when you start the code lab (removed as step 1).
@Composable
fun StartOnlyTextComposables() {
    Text(
        modifier = Modifier.fillMaxSize(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.primary,
        text = stringResource(R.string.hello_world_starter),
    )
}

/* Previews of Composables. */

// Hello, world starter text preview
@WearPreviewDevices
@Composable
fun StartOnlyTextComposablesPreview() {
    WearAppTheme {
        AppScaffold {
            val listState = rememberTransformingLazyColumnState()
            val contentPadding =
                rememberResponsiveColumnPadding(first = ColumnItemType.BodyText)
            ScreenScaffold(
                scrollState = listState,
                contentPadding = contentPadding,
            ) { contentPadding ->
                TransformingLazyColumn(state = listState, contentPadding = contentPadding) {
                    item {
                        StartOnlyTextComposables()
                    }
                }
            }
        }
    }
}

// Button Preview
@WearPreviewDevices
@Composable
fun ButtonExamplePreview() {
    WearAppTheme {
        //  val transformationSpec = rememberTransformationSpec()
        AppScaffold {
            val listState = rememberTransformingLazyColumnState()
            val contentPadding =
                rememberResponsiveColumnPadding(first = ColumnItemType.IconButton)
            ScreenScaffold(
                scrollState = listState,
                contentPadding = contentPadding,
            ) { contentPadding ->
                TransformingLazyColumn(state = listState, contentPadding = contentPadding) {
                    item {
                        IconButtonExample()
                    }
                }
            }
        }
    }
}

// Text Preview
@WearPreviewDevices
@Composable
fun TextExamplePreview() {
    WearAppTheme {
        val transformationSpec = rememberTransformationSpec()
        AppScaffold {
            val listState = rememberTransformingLazyColumnState()
            val contentPadding =
                rememberResponsiveColumnPadding(first = ColumnItemType.BodyText)
            ScreenScaffold(
                scrollState = listState,
                contentPadding = contentPadding,
            ) { contentPadding ->
                TransformingLazyColumn(state = listState, contentPadding = contentPadding, modifier = Modifier.fillMaxWidth()) {
                    item {
                        TextExample(transformation = SurfaceTransformation(transformationSpec))
                    }
                }
            }
        }
    }
}

// Card Preview
@WearPreviewDevices
@Composable
fun CardExamplePreview() {
    WearAppTheme {
        val transformationSpec = rememberTransformationSpec()
        AppScaffold {
            val listState = rememberTransformingLazyColumnState()
            val contentPadding =
                rememberResponsiveColumnPadding(first = ColumnItemType.Card)
            ScreenScaffold(
                scrollState = listState,
                contentPadding = contentPadding,
            ) { contentPadding ->
                TransformingLazyColumn(state = listState, contentPadding = contentPadding) {
                    item {
                        CardExample(transformation = SurfaceTransformation(transformationSpec))
                    }
                }
            }
        }
    }
}

// Chip Preview
@WearPreviewDevices
@Composable
fun ChipPreview() {
    WearAppTheme {
        val transformationSpec = rememberTransformationSpec()
        AppScaffold {
            val listState = rememberTransformingLazyColumnState()
            val contentPadding =
                rememberResponsiveColumnPadding(first = ColumnItemType.Button)
            ScreenScaffold(
                scrollState = listState,
                contentPadding = contentPadding,
            ) { contentPadding ->
                TransformingLazyColumn(state = listState, contentPadding = contentPadding) {
                    item {
                        ChipExample(transformation = SurfaceTransformation(transformationSpec))
                    }
                }
            }
        }
    }
}

// Switch Chip Preview
@WearPreviewDevices
@Composable
fun SwitchChipExamplePreview() {
    WearAppTheme {
        val transformationSpec = rememberTransformationSpec()
        AppScaffold {
            val listState = rememberTransformingLazyColumnState()
            val contentPadding =
                rememberResponsiveColumnPadding(first = ColumnItemType.Button)
            ScreenScaffold(
                scrollState = listState,
                contentPadding = contentPadding,
            ) { contentPadding ->
                TransformingLazyColumn(state = listState, contentPadding = contentPadding) {
                    item {
                        SwitchChipExample(transformation = SurfaceTransformation(transformationSpec))
                    }
                }
            }
        }
    }
}
