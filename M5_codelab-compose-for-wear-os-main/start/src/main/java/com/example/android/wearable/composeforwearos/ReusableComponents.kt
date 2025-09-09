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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material3.AppScaffold
import androidx.wear.compose.material3.Button
import androidx.wear.compose.material3.ScreenScaffold
import androidx.wear.compose.material3.Text
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
}

// TODO: Create a Text Composable
@Composable
fun TextExample(modifier: Modifier = Modifier) {
}

// TODO: Create a Card (specifically, an AppCard) Composable
@Composable
fun CardExample(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
) {
}

// TODO: Create a Chip Composable
@Composable
fun ChipExample(
    modifier: Modifier = Modifier,
) {
}

// TODO: Create a Chip with a switch Composable
@Composable
fun SwitchChipExample(modifier: Modifier = Modifier) {
}

// Function only used as a demo for when you start the code lab (removed as step 1).
@Composable
fun StartOnlyTextComposables() {
    Text(
        modifier = Modifier.fillMaxSize(),
        textAlign = TextAlign.Center,
        text = stringResource(R.string.hello_world_starter),
    )
}

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
                        TextExample()
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
                        CardExample()
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
                        ChipExample()
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
                        SwitchChipExample()
                    }
                }
            }
        }
    }
}
