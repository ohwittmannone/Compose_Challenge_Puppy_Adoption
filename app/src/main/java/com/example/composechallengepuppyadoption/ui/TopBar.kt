package com.example.composechallengepuppyadoption.ui

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun AppTopBar(title: String, content: @Composable () -> Unit) {
    Scaffold(topBar = { TopAppBar(title = { Text(title) }) }) {
        content()
    }
}