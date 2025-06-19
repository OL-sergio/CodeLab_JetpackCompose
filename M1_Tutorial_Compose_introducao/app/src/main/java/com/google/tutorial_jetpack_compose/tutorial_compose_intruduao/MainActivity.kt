package com.google.tutorial_jetpack_compose.tutorial_compose_intruduao

import SampleData
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.tutorial_jetpack_compose.tutorial_compose_intruduao.ui.theme.Tutorial_Compose_introduçãoTheme

//https://developer.android.com/develop/ui/compose/tutorial?hl=pt-br

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tutorial_Compose_introduçãoTheme{
                Surface(modifier = Modifier.fillMaxSize()) {
                    Conversation(SampleData.conversationSample)
                //MessageCard( Message("Android", "Jetpack Compose"))
                }
            }
        }
    }
}


data class Message(val author: String, val body: String)

@Composable
fun MessageCard( message: Message) {
    // Add padding around our message
    Row(modifier = Modifier.padding(all = 14.dp)) {
        Image(
            painter = painterResource(R.drawable.profile_image),
            contentDescription = "Contact Profile Image",
            modifier = Modifier
                // Set image size to 40 dp
                .size(40.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary , CircleShape)
        )
        // Add a horizontal space between the image and the column
        Spacer( modifier = Modifier.padding(start = 8.dp))

        var isExpanded by remember { mutableStateOf(false) }

        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
        )

        Column (modifier = Modifier.clickable{ isExpanded = !isExpanded }) {
            Text(
                text = message.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )

            // Add a vertical space between the author and message texts
            Spacer(modifier = Modifier.padding(bottom = 4.dp))
            Surface (
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text(
                    text = message.body,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        // The list items in the list are given a fixed height
        items(messages.size) { index ->
            MessageCard(message = messages[index])
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    Tutorial_Compose_introduçãoTheme {
        Conversation(SampleData.conversationSample)
    }
}


@Preview(name = "Light Mode", showBackground = true)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode",
)
@Composable
fun PreviewMessageCard() {
    Tutorial_Compose_introduçãoTheme {
        Surface {
            MessageCard(
                message = Message("Lexi", "Hey, take a look at Jetpack Compose, it's great!")
            )
        }
    }
}
