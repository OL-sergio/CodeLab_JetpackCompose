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

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material3.AppScaffold
import androidx.wear.compose.material3.EdgeButton
import androidx.wear.compose.material3.EdgeButtonSize
import androidx.wear.compose.material3.ScreenScaffold
import androidx.wear.compose.material3.SurfaceTransformation
import androidx.wear.compose.material3.Text
import androidx.wear.compose.material3.lazy.rememberTransformationSpec
import androidx.wear.compose.material3.lazy.transformedHeight
import com.example.android.wearable.composeforwearos.theme.WearAppTheme
import com.google.android.horologist.compose.layout.ColumnItemType
import com.google.android.horologist.compose.layout.rememberResponsiveColumnPadding

/**
 * This code lab is meant to help existing Compose developers get up to speed quickly on
 * Compose for Wear OS.
 *
 * The code lab walks through a majority of the simple composables for Wear OS (both similar to
 * existing mobile composables and new composables).
 *
 * It also covers more advanced composables like [TransformingLazyColumn] (Wear OS's version of
 * [LazyColumn]) and the Wear OS version of [Scaffold].The codelab explains the advantage of using
 * [TransformingLazyColumn] and  [AppScaffold] and [ScreenScaffold] to simplify
 * code development to align with Wear OS UX guidance.
 *
 * Check out [this link](https://android-developers.googleblog.com/2021/10/compose-for-wear-os-now-in-developer.html)
 * for more information on Compose for Wear OS.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WearApp()
        }
    }
}

@Composable
fun WearApp() {
    WearAppTheme {
        /* *************************** Part 4: Wear OS Scaffold *************************** */
        /*
         * AppScaffold adds a TimeText by default that can be override by the
         * ScreenScaffold. It ensures that TimeText animates correctly when navigating between
         * screens.
         * */
        AppScaffold {
            val listState = rememberTransformingLazyColumnState()
            val transformationSpec = rememberTransformationSpec()

            /* *************************** Part 4: Wear OS Scaffold *************************** */
            // TODO (Start): Create a ScreenScaffold (Wear Version)
            /*
             * ScreenScaffold is used in conjunction with AppScaffold and adds a
             * position indicator to the list by default.
             * Use the contentPadding parameter to specify the types of items that appear at the
             * start and end of the list ensures that the appropriate padding is used.
             * */

            ScreenScaffold(
                scrollState = listState,
                contentPadding = rememberResponsiveColumnPadding(
                    first = ColumnItemType.IconButton,
                    last = ColumnItemType.Button,
                ),
                /* *************************** Part 11: EdgeButton *************************** */
                // TODO: Add a EdgeButton
                edgeButton = {
                    EdgeButton(
                        onClick = { /* ... */ },
                        buttonSize = EdgeButtonSize.Medium,
                    ) {
                        Text(stringResource(R.string.more))
                    }
                },
            ) { contentPadding ->

                /* *************************** Part 3: ScalingLazyColumn *************************** */
                // TODO: Swap a TransformingLazyColumn (Wear's version of LazyColumn)
                /*
                 * TransformingLazyColumn applies padding for elements in the list to
                 * make sure no elements are clipped on different screen sizes.
                 * */

                TransformingLazyColumn(
                    state = listState,
                    contentPadding = contentPadding,
                ) {
                    /* ******************* Part 1: Simple composables ******************* */
                    item {
                        IconButtonExample()
                    }
                    item {
                        TextExample(
                            modifier =
                            Modifier.fillMaxWidth().transformedHeight(this, transformationSpec),
                            transformation = SurfaceTransformation(transformationSpec),
                        )
                    }
                    item {
                        CardExample(
                            modifier =
                            Modifier.fillMaxWidth().transformedHeight(this, transformationSpec),
                            transformation = SurfaceTransformation(transformationSpec),
                        )
                    }

                    /* ********************* Part 2: Wear unique composables ********************* */
                    item {
                        ChipExample(
                            modifier =
                            Modifier.fillMaxWidth().transformedHeight(this, transformationSpec),
                            transformation = SurfaceTransformation(transformationSpec),
                        )
                    }
                    item {
                        SwitchChipExample(
                            modifier =
                            Modifier.fillMaxWidth().transformedHeight(this, transformationSpec),
                            transformation = SurfaceTransformation(transformationSpec),
                        )
                    }
                }

                // TODO (End): Create a ScreenScaffold (Wear Version)
            }
            // TODO (End): Create a AppScaffold (Wear Version)
        }
    }
}
