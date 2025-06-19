package com.google.tutorial_jetpack_compose.codelab.mysoothe_layouts_basicos_no_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.tutorial_jetpack_compose.codelab.mysoothe_layouts_basicos_no_compose.ui.theme.MySoothe_Layouts_basicos_no_ComposeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
                val windowSizeClass = calculateWindowSizeClass(this).widthSizeClass
                MySootheApp(windowSizeClass)
            
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),

        placeholder = {
            Text(stringResource(R.string.search_placeholder))
        },

        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier
    ) {
        Text(
            text =  stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier

) {
    Surface(color = MaterialTheme.colorScheme.onSecondary,
        modifier = modifier.fillMaxHeight()){
        Column(
            modifier.verticalScroll(rememberScrollState())

        ) {
            Spacer(Modifier.height(16.dp))
            SearchBar(modifier = Modifier.padding(horizontal = 16.dp))
            HomeSection(

                R.string.align_your_body)
            {

                AlignYourBodyRow()
            }
            HomeSection(R.string.favorite_collections) {
                FavoriteCollectionsGrid()

            }
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid (
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp),
    ){
        items(favoriteCollectionsData) { item ->
            FavoriteCollectionCard(
                drawableRes = item.drawableRes,
                stringRes = item.stringRes,
                modifier = Modifier.height(80.dp) // Height: Width - Padding
            )
        }
    }
}


@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawableRes: Int,
    @StringRes stringRes: Int,
    modifier: Modifier = Modifier
) {
    Surface (
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
        modifier  = modifier
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp) // Width - Padding

        ) {
            Image(
                painter = painterResource(drawableRes),
                contentDescription = null,
                contentScale = ContentScale.Crop, // Crop - FillBounds - Fit
                modifier = Modifier.size(80.dp) // Size: Width - Height

            )
            Text(
                text = stringResource(stringRes),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawableRes: Int,
    @StringRes stringRes: Int,
    modifier: Modifier = Modifier
){
    Column(  // Column: Start - CenterHorizontally - End
        // / Row: Top - CenterVertically - Bottom
        // / Box: TopStart - TopCenter - TopEnd - CenterStart - Center - CenterEnd - BottomStart - BottomCenter - BottomEnd
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ){
        Image(
            painter = painterResource(drawableRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,//Crop - FillBounds - Fit
            modifier = Modifier
                .size( 88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(stringRes),
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    LazyRow (
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ){
        items(alignYourBodyData) { item ->
            AlignYourBodyElement(
                drawableRes = item.drawableRes,
                stringRes = item.stringRes,
            )
        }
    }
}

@Composable
private fun SootheBottomNavigation( modifier: Modifier = Modifier){
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier =  modifier.padding(top = 8.dp)
    ) {
        NavigationBarItem(

            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = { Text(stringResource(R.string.button_navigation_home)) },
            selected = true,
            onClick = { /* Handle Home Click */ }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = { Text(stringResource(R.string.button_navigation_profile)) },
            selected = false,
            onClick = { /* Handle Search Click */ }
        )
    }
}
@Composable
fun SootheBottomNavigationRail(modifier: Modifier = Modifier) {
    NavigationRail(
        modifier = modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.button_navigation_home))
                },
                selected = true,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.button_navigation_profile))
                },
                selected = false,
                onClick = {}
            )
        }
    }
}

@Composable
fun MySootheApp(windowSize: WindowWidthSizeClass) {
    when( windowSize ) {
        WindowWidthSizeClass.Compact -> {
                MySootheAppPortrait()
        }
        WindowWidthSizeClass.Expanded ->{
            MySootheAppLandscape()
        }
    }
}

@Composable
fun MySootheAppPortrait(){
    MySoothe_Layouts_basicos_no_ComposeTheme {
            Scaffold(
                bottomBar = { SootheBottomNavigation() }
            ) { padding ->
                HomeScreen(
                    Modifier.padding(padding)
                )
            }
    }
}

@Composable
fun MySootheAppLandscape() {
    MySoothe_Layouts_basicos_no_ComposeTheme {
        Surface(color = MaterialTheme.colorScheme.onPrimary) {
            Row {
                SootheBottomNavigationRail()
                HomeScreen()
            }
        }
    }
}

val favoriteCollectionsData = listOf(
    R.drawable.f_foto1 to R.string.f_foto1,
    R.drawable.f_foto2 to R.string.f_foto2,
    R.drawable.f_foto3 to R.string.f_foto3,
    R.drawable.f_foto4 to R.string.f_foto4,
    R.drawable.f_foto5 to R.string.f_foto5,
    R.drawable.f_foto6 to R.string.f_foto6,
).map { DrawableStringPair( it.first , it.second) }

val alignYourBodyData = listOf(
    R.drawable.foto1 to R.string.foto1,
    R.drawable.foto2 to R.string.foto2,
    R.drawable.foto3 to R.string.foto3,
    R.drawable.foto4 to R.string.foto4,
    R.drawable.foto5 to R.string.foto5,
    R.drawable.foto6 to R.string.foto6,
).map { DrawableStringPair( it.first , it.second) }

data class DrawableStringPair(
    @DrawableRes val drawableRes: Int,
    @StringRes val stringRes: Int
)


@Preview(showBackground = true)
@Composable
fun PreviewSearchBar() {
    MySoothe_Layouts_basicos_no_ComposeTheme {
        SearchBar()

    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun PreviewAlignYourBodyElement() {
    MySoothe_Layouts_basicos_no_ComposeTheme {
        AlignYourBodyElement(
            stringRes = R.string.foto1,
            drawableRes = R.drawable.foto1,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true , backgroundColor = 0xFFF5F0EE)
@Composable
fun PreviewHomeSection() {
    MySoothe_Layouts_basicos_no_ComposeTheme {
        HomeSection(R.string.align_your_body) {
            AlignYourBodyRow()
        }
    }
}


@Preview(showBackground = true , backgroundColor = 0xFFF5F0EE)
@Composable
fun PreviewFavoriteCollectionCard() {
    MySoothe_Layouts_basicos_no_ComposeTheme {
        FavoriteCollectionCard(
            stringRes = R.string.foto2,
            drawableRes = R.drawable.foto2,
            modifier = Modifier.padding(8.dp)
        )
    }
}
@Preview(showBackground = true , backgroundColor = 0xFFF5F0EE)
@Composable
fun PreviewFavoriteCollectionsGrid() {
    MySoothe_Layouts_basicos_no_ComposeTheme {
        FavoriteCollectionsGrid()
    }
}

@Preview(showBackground = true , backgroundColor = 0xFFF5F0EE)
@Composable
fun PreviewSootheBottomNavigation() {
    MySoothe_Layouts_basicos_no_ComposeTheme {
        SootheBottomNavigation()
    }
}



@Preview(showBackground = true , backgroundColor = 0xFFF5F0EE, heightDp = 640 , widthDp = 360)
@Composable
fun  HomeScreenPreview() {
    MySoothe_Layouts_basicos_no_ComposeTheme {
        HomeScreen()
    }
}


@Preview(showBackground = true , backgroundColor = 0xFFF5F0EE, heightDp = 640 , widthDp = 360)
@Composable
fun PreviewMySootheAppPortrait() {
    MySoothe_Layouts_basicos_no_ComposeTheme {
        MySootheAppPortrait()
    }
}

@Preview(showBackground = true , backgroundColor = 0xFFF5F0EE, heightDp = 360 , widthDp = 630)
@Composable
fun PreviewMySootheAppLandscape() {
    MySoothe_Layouts_basicos_no_ComposeTheme {
        MySootheAppLandscape()
    }
}

