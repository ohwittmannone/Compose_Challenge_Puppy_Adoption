package com.example.composechallengepuppyadoption.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composechallengepuppyadoption.data.Puppy
import com.example.composechallengepuppyadoption.ui.theme.ComposeChallengePuppyAdoptionTheme

@Composable
fun PuppyDetailView(puppy: Puppy, navController: NavController) {
    ComposeChallengePuppyAdoptionTheme {
        DetailTopBar(puppy.name, navController) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = puppy.image),
                    contentDescription = "A picture of ${puppy.name}",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .height(200.dp)
                        .width(200.dp)
                        .clip(RoundedCornerShape(percent = 8))
                )
                Text(text = puppy.description, modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
private fun DetailTopBar(name: String, navController: NavController, content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(name) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back button"
                        )
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {}, //purposely not having it do anything
                text = { Text("Adopt Me") },
                modifier = Modifier.padding(8.dp)
            )
        }
    ) {
        content()
    }
}