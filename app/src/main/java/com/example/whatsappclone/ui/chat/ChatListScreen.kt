package com.example.whatsappclone.ui.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whatsappclone.R

@Composable
fun ChatListScreen() {
    val chatList = listOf(
        Chat("Dad", "Dear [Boss's Name], I hope you’re well...", "Yesterday", R.drawable.ic_user),
        Chat("Me", "WhatsApp Video 2024-11-26 at...", "27/11/2024", R.drawable.ic_user),
        Chat("AJUBA", "Dad: ohoo ham try kar rahe...", "22/11/2024", R.drawable.ic_user),
        Chat("ML", "Are you okay???", "12:02 am", R.drawable.ic_user),
        Chat("Arcanegrin's Pc Den", "~ Ethan changed this group's...", "Yesterday", R.drawable.ic_group),
        Chat("Anurag", "Photo", "Yesterday", R.drawable.ic_user),
        Chat("Shivam Bhopal", "Okay", "Yesterday", R.drawable.ic_user)
    )
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        // Top Bar
        Text(
            text = "WhatsApp",
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search chats...", color = Color.Gray) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),
        )


        // Chat List
        LazyColumn {
            items(chatList.filter {
                it.name.contains(searchQuery.text, ignoreCase = true) ||
                        it.message.contains(searchQuery.text, ignoreCase = true)
            }) { chat ->
                ChatItem(chat)
            }
        }
    }
}

@Composable
fun ChatItem(chat: Chat) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { /* Navigate to Chat Screen */ },
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Profile Image
        Image(
            painter = painterResource(id = chat.imageRes),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .padding(4.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Chat Content
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = chat.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White
                )
                Text(
                    text = chat.time,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = chat.message,
                fontSize = 14.sp,
                color = Color.Gray,
                maxLines = 1
            )
        }

        // Pin or Badge Icon
        if (chat.name == "Arcanegrin's Pc Den") {
            Icon(
                painter = painterResource(id = R.drawable.ic_pin),
                contentDescription = "Pinned",
                tint = Color.Green ,
                modifier = Modifier
                    .size(width = 10.dp, height = 10.dp)
            )
        }
    }
}

// Data Class for Chats
data class Chat(
    val name: String,
    val message: String,
    val time: String,
    val imageRes: Int
)
