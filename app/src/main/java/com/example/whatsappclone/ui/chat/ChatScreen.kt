package com.example.whatsappclone.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ChatScreen(chatId: String?) {
    var inputText by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf<Pair<String, Boolean>>() }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Chat with $chatId", modifier = Modifier.padding(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f).padding(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(messages) { message ->
                MessageBubble(message.first, message.second)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            TextField(
                value = inputText,
                onValueChange = { inputText = it },
                placeholder = { Text("Type a message") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (inputText.isNotEmpty()) {
                    messages.add(inputText to true)
                    inputText = ""
                }
            }) {
                Text("Send")
            }
        }
    }
}

@Composable
fun MessageBubble(message: String, isSent: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = if (isSent) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .background(
                    if (isSent) Color(0xFFDCF8C6) else Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
        ) {
            Text(message)
        }
    }
}
