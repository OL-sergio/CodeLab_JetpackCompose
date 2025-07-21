/*
 * Copyright 2020 Google LLC
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
package com.google.samples.apps.sunflower.plantdetail



import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.compose.animation.animateBounds
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.text.HtmlCompat
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.data.Plant
import com.google.samples.apps.sunflower.theme.SunflowerTheme
import com.google.samples.apps.sunflower.viewmodels.PlantDetailViewModel


data class PlantDetailsCallbacks(
    val onFabClick: () -> Unit,
    val onBackClick: () -> Unit,
    val onShareClick: (String) -> Unit,
    val onGalleryClick: (Plant) -> Unit

)


@Composable
fun PlantDetailDescriptionScreen(
    plantDetailViewModel: PlantDetailViewModel,
    onBackClick: () -> Unit,
    onShareClick: (String) -> Unit,
    onGalleryClick: (Plant) -> Unit,
) {
    val plant =  plantDetailViewModel.plant.observeAsState().value
    val isPlanted = plantDetailViewModel.isPlanted.observeAsState().value

    if (plant != null && isPlanted != null) {
        Surface {
            PlantDetails(
                plant,
                isPlanted,
                PlantDetailsCallbacks(
                    onFabClick = { plantDetailViewModel.addPlantToGarden() },
                    onBackClick = onBackClick,
                    onShareClick = onShareClick,
                    onGalleryClick = onGalleryClick,
                ),
            )
        }
    }
}

@Composable
private fun PlantDetails(
    plant: Plant,
    isPlanted: Boolean,
    callbacks: PlantDetailsCallbacks,
    modifier: Modifier = Modifier,
    ) {

    val scrollState = rememberScrollState()
    var plantScroller by remember {
        mutableStateOf(PlantDetailScroller(scrollState, Float.MIN_VALUE))
    }

    val transitionState = remember(plantScroller) { plantScroller.toolbarTransitionState }
    val toolbarState = plantScroller.getToolbarState(LocalDensity.current)


    val transition = rememberTransition(transitionState, label = "")
    val toolbarAlpha by transition.animateFloat(
        transitionSpec = { spring(stiffness = Spring.StiffnessLow) }, label = ""
    ) { toolbarTransitionState ->
        if (toolbarTransitionState == ToolbarState.HIDDEN) 1f else 0f
    }

    val contentAlpha by transition.animateFloat(
        transitionSpec = { spring(stiffness = Spring.StiffnessLow) }, label = ""
    ) { toolbarTransitionState ->
        if (toolbarTransitionState == ToolbarState.HIDDEN) 1f else 0f
    }

    Box (
        modifier.fillMaxSize()
    ){
        PlantDetailContent(
            scrollState = scrollState,
            plant = plant,
            isPlanted = isPlanted,
            toolbarState = toolbarState,
            onNamePosition = { newNamePosition ->
                if ( plantScroller.namePosition == Float.MIN_VALUE ) {
                    plantScroller = plantScroller.copy(namePosition = newNamePosition)
                }
            },

            imageHeight = with(LocalDensity.current) {
                val candidateHeight =
                    Dimens.PlantDetailAppBarHeight
                // FIXME: Remove this workaround when https://github.com/bumptech/glide/issues/4952
                // is released
                maxOf(candidateHeight, 1.dp)
            },
            onFabClick = callbacks.onFabClick,
            onGalleryClick = { callbacks.onGalleryClick(plant) },
            contentAlpha = { contentAlpha }
        )
        PlantToolbar(
            toolbarState, plant.name, callbacks,
            toolbarAlpha = { toolbarAlpha },
            contentAlpha = { contentAlpha }
        )
    }
}

@Composable
private fun PlantDetailContent(
    scrollState: ScrollState,
    plant: Plant,
    isPlanted: Boolean,
    toolbarState: ToolbarState,
    onNamePosition: (Float) -> Unit,
    imageHeight: Dp,
    onFabClick: () -> Unit,
    onGalleryClick: () -> Unit,
    contentAlpha: () -> Float,
    ) {
    Column(
        Modifier.verticalScroll(scrollState)
    ) {
            ConstraintLayout {
                val (image, info , fab) = createRefs()

                PlantImage(
                    imageUrl = plant.imageUrl,
                    imageHeight = imageHeight,
                    modifier = Modifier
                        .constrainAs(image) { top.linkTo(parent.top) }
                        .alpha(contentAlpha())
                )

                if(!isPlanted) {
                    // If the plant is already planted, we don't show the FAB
                    // as it is not needed.
                    val fabEndMargin = Dimens.PaddingSmall
                    PlantFab(
                        onFabClick = onFabClick,
                        modifier = Modifier
                            .constrainAs(fab) {
                                centerAround(image.bottom)
                                absoluteRight.linkTo(
                                    parent.absoluteRight,
                                    margin = fabEndMargin
                                )
                            }
                            .alpha(contentAlpha())
                    )
                }
                PlantInformation(
                    name = plant.name,
                    wateringInterval = plant.wateringInterval,
                    description = plant.description,
                    toolbarState = toolbarState,
                    onNamePosition = { onNamePosition (it) },
                    onGalleryClick = onGalleryClick,
                    modifier = Modifier.constrainAs(info) { top.linkTo(image.bottom) }
                )
            }
    }
}

@Composable
private fun PlantInformation(
    name: String,
    wateringInterval: Int,
    description: String,
    toolbarState: ToolbarState,
    onNamePosition: (Float) -> Unit,
    onGalleryClick: () -> Unit,
    modifier: Modifier = Modifier)
    {
        Column (modifier = modifier.padding(Dimens.PaddingLarge)) {
            Text(
                text = name,
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier
                    .padding(
                        start = Dimens.PaddingSmall,
                        end = Dimens.PaddingSmall,
                        bottom = Dimens.PaddingNormal
                    )
                    .align(Alignment.CenterHorizontally)

            )

            Box(
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(
                        start = Dimens.PaddingSmall,
                        end = Dimens.PaddingSmall,
                        bottom = Dimens.PaddingNormal
                    )
            ) {
                Column(
                    Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = stringResource(id = R.string.watering_needs_prefix),

                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(horizontal = Dimens.PaddingSmall)
                            .align(Alignment.CenterHorizontally)
                    )

                    val wateringIntervalText = pluralStringResource(
                        R.plurals.watering_needs_suffix, wateringInterval, wateringInterval
                    )

                    Text(
                        text = wateringIntervalText,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    /*Image(
                        painter = painterResource(id = R.drawable.ic_photo_library),
                        contentDescription = "Gallary Icon",
                        Modifier
                            .clickable { onGalleryClick() }
                            .align(Alignment.End)
                    )*/
                }
            }
            PlantDescription(description)
        }
}
@Composable
fun PlantToolbar(
    toolbarState: ToolbarState,
    plantName: String,
    callbacks: PlantDetailsCallbacks,
    toolbarAlpha: () -> Float,
    contentAlpha: () -> Float

) {

    val onShareClick = {
        callbacks.onShareClick(plantName)
    }

    if (toolbarState.isShown){
        PlantDetailsToolbar(

            plantName = plantName,
            onBackClick = callbacks.onBackClick,
            onShareClick = onShareClick,
            modifier = Modifier.alpha(toolbarAlpha())

        )
    }else {

        PlantHeaderActions(
            onBackClick = callbacks.onBackClick,
            onShareClick = onShareClick,
            modifier = Modifier.alpha(contentAlpha())

        )
    }
}


@Composable
private fun PlantHeaderActions(

    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier

) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(top = Dimens.ToolbarIconPadding),
        horizontalArrangement = Arrangement.SpaceBetween,
        ){

        val iconModifier = Modifier
            .sizeIn(
                minWidth = Dimens.ToolbarIconSize,
                minHeight = Dimens.ToolbarIconSize
            )
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = CircleShape
            )

            IconButton (
                onClick = onBackClick,
                modifier = Modifier
                    .padding(start = Dimens.ToolbarIconPadding)
                    .then(iconModifier)
            ){
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.a11y_back)
                )

            }

        val shareContentDescription = stringResource(id = R.string.menu_item_share_plant)

        IconButton (
            onClick = onShareClick,
            modifier = Modifier
                .padding(end = Dimens.ToolbarIconPadding)
                .then(iconModifier)
                .semantics {
                    contentDescription = shareContentDescription
                }
        ) {
            Icon(
                Icons.Filled.Share,
                contentDescription = shareContentDescription
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlantDetailsToolbar(
    plantName: String,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface {
        TopAppBar(
                modifier = modifier
                    .statusBarsPadding()
                    .background(color = MaterialTheme.colorScheme.surface),
                title = {
                    Row{
                        IconButton(
                            onBackClick,
                            Modifier.align(Alignment.CenterVertically)
                        ){
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(id = R.string.a11y_back)
                            )
                        }
                        Text(
                            text = plantName,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(Alignment.Center)
                                .weight(1f)
                        )
                        
                        val shareContentDescription =
                            stringResource(R.string.menu_item_share_plant)
                        IconButton(
                            onShareClick,
                            Modifier
                                .align(Alignment.CenterVertically)
                                .semantics {
                                    contentDescription = shareContentDescription
                                }
                        ) {
                            Icon(
                                Icons.Filled.Share,
                                contentDescription = null
                            )
                        }
                    }
                }
            )
        }
    }



@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun PlantImage(
    imageUrl: String,
    imageHeight: Dp,
    modifier: Modifier = Modifier
    ) {

    var isLoading by remember { mutableStateOf(true) }
    Box(
        modifier
            .fillMaxWidth()
            .height(imageHeight)
    ) {
        GlideImage(
            model = imageUrl,
            contentDescription = null,
            modifier = modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        ){
                it.addListener(object : RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable?>,
                        isFirstResource: Boolean,
                    ): Boolean {
                        isLoading = false
                        return false
                    }
                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: Target<Drawable?>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean,
                    ): Boolean {
                        isLoading = false
                        return false
                    }
                }
            )
        }
    }
}

@Composable
private fun PlantDescription(description: String) {
    val htmlDescription = remember(description) {
        HtmlCompat.fromHtml(
            description,
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )
    }
    AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp, top = 16.dp, end = 8.dp, bottom = 56.dp
                ),

        factory = { context ->
                    TextView(context).apply {
                        movementMethod = LinkMovementMethod.getInstance()
                    }
            },
            update = {
                it.text = htmlDescription
            },
        )
}

@Composable
private fun PlantFab(
    onFabClick: () -> Unit,
    modifier: Modifier = Modifier
){
    val addPlantContentDescription = stringResource(id = R.string.add_plant)
    FloatingActionButton(
        onClick = onFabClick,
        shape = MaterialTheme.shapes.small,
        modifier = modifier.semantics{
            contentDescription = addPlantContentDescription
        }
    ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = null
            )
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PlantDetailDescriptionScreenPreview() {
    SunflowerTheme {
        Surface {
            PlantDetails(
                Plant("plantId", "Tomato", "HTML<br>description", 6),
                true,
                PlantDetailsCallbacks({ }, { }, { }, { }, )
                )
        }
    }
}


