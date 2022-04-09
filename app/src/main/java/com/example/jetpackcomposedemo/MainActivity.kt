package com.example.jetpackcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposedemo.ui.theme.JetPackComposeDemoTheme

class MainActivity : ComponentActivity() {
    private val messages = {
        listOf(
            Message(
                "Colleague",
                "Take a look at Jetpack Compose! It simplifies UI development on Android"
            ),
            Message(
                "Colleague",
                "Take a look at Jetpack Compose!"
            ),
            Message(
                "Colleague",
                "Take a look at Jetpack Compose!It simplifies UI development on Android.It simplifies UI development on Android.It simplifies UI development on Android.It simplifies UI development on Android.It simplifies UI development on Android"
            ),
            Message(
                "Colleague",
                "Take a look at Jetpack Compose!"
            ),
            Message(
                "Colleague",
                "Take a look at Jetpack Compose!"
            ),
            Message(
                "Colleague",
                "Take a look at Jetpack Compose!"
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Conversation(messages = messages.invoke())
                }
            }
        }
    }
}


data class Message(val author: String, val body: String)

@Composable
fun MessageCard(message: Message) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    val surfaceColor: Color by animateColorAsState(
        targetValue = if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
    )
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = "Hello, ${message.author}",
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(all = 1.dp)
            ) {
                Text(
                    text = "Hello, ${message.body}",
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2,
                )
            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message -> MessageCard(message = message) }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetPackComposeDemoTheme {
        MessageCard(message = Message("Android", "Jetpack Compose"))
    }
}