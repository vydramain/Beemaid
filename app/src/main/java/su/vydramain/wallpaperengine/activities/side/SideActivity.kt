package su.vydramain.wallpaperengine.activities.side

import android.content.res.Configuration

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import androidx.activity.compose.setContent

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import su.vydramain.wallpaperengine.R
import su.vydramain.wallpaperengine.ui.theme.WallpaperEngineAppTheme

object SampleData {
    // Sample conversation data
    val conversationSample = listOf(
        Message(
            "Nanashi Mumei",
            "Test...Test...Test..."
        ),
        Message(
            "Nanashi Mumei",
            """List of Android versions:
            |Android KitKat (API 19)
            |Android Lollipop (API 21)
            |Android Marshmallow (API 23)
            |Android Nougat (API 24)
            |Android Oreo (API 26)
            |Android Pie (API 28)
            |Android 10 (API 29)
            |Android 11 (API 30)
            |Android 12 (API 31)""".trim()
        ),
        Message(
            "Nanashi Mumei",
            """I think Kotlin is my favorite programming language.
            |It's so much fun!""".trim()
        ),
        Message(
            "Nanashi Mumei",
            "Searching for alternatives to XML layouts..."
        ),
        Message(
            "Nanashi Mumei",
            """Hey, take a look at Jetpack Compose, it's great!
            |It's the Android's modern toolkit for building native UI.
            |It simplifies and accelerates UI development on Android.
            |Less code, powerful tools, and intuitive Kotlin APIs :)""".trim()
        ),
        Message(
            "Nanashi Mumei",
            "It's available from API 21+ :)"
        ),
        Message(
            "Nanashi Mumei",
            "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
        ),
        Message(
            "Nanashi Mumei",
            "Android Studio next version's name is Arctic Fox"
        ),
        Message(
            "Nanashi Mumei",
            "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
        ),
        Message(
            "Nanashi Mumei",
            "I didn't know you can now run the emulator directly from Android Studio"
        ),
        Message(
            "Nanashi Mumei",
            "Compose Previews are great to check quickly how a composable layout looks like"
        ),
        Message(
            "Nanashi Mumei",
            "Previews are also interactive after enabling the experimental setting"
        ),
        Message(
            "Nanashi Mumei",
            "Have you tried writing build.gradle with KTS?"
        ),
    )
}


class SideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WallpaperEngineAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MessageCard(Message("Nanashi Mumei", "Oh, hi"))
                }
            }
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(inputMessage: Message) {
    // Add padding around our message
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.nanashi_mumei),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                // Set image size 40 dp
                .size(60.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )

        // We keep track if the message is expanded or not in this variable
        var isExpanded by remember { mutableStateOf(false) }
        // surfaceColor will be updated gradually from one color to the other
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        )


        // Add a horizontal space between the image and column
        Spacer(modifier = Modifier.width(8.dp))

        // We toggle the isExpanded variable when we click on this Column
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = inputMessage.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
            // Add a vertical space between the author and message text
            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                // surfaceColor color will be changing gradually from primary to surface
                color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = inputMessage.body,
                    modifier = Modifier.padding(all = 7.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {
    WallpaperEngineAppTheme {
        Surface {
            MessageCard(inputMessage = Message("Nanashi Mumei", "Oh, hi"))
        }
    }
}

@Composable
fun Conversation(inputMessages: List<Message>) {
    LazyColumn {
        items(inputMessages) { message ->
            MessageCard(message)
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewConversation() {
    WallpaperEngineAppTheme {
        Conversation(SampleData.conversationSample)
    }
}