package com.example.whatsappclone.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.whatsappclone.ui.chat.ChatListScreen
import com.example.whatsappclone.ui.chat.ChatScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "chatList") {
        composable("chatList") { ChatListScreen() }
        composable("chat/{chatId}") { backStackEntry ->
            val chatId = backStackEntry.arguments?.getString("chatId")
            ChatScreen(chatId)
        }
    }
}
