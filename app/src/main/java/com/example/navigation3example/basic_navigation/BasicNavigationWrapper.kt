package com.example.navigation3example.basic_navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.navigation3example.presentation.DetailScreen
import com.example.navigation3example.presentation.HomeScreen

data object Home
data class Detail(val id:String)
@Composable
fun BasicNavigationWrapper(){
    val backStack = remember { mutableStateListOf<Any>(Home) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when(key){
                is Home -> NavEntry(key){
                    HomeScreen { id ->
                        backStack.add(Detail(id))
                    }
                }
                is Detail -> NavEntry(key){
                    DetailScreen(key.id){
                        backStack.removeLastOrNull()
                    }
                }
                else -> NavEntry(key = Unit){
                    Text("Error :(")
                }
            }
        }
    )
}
