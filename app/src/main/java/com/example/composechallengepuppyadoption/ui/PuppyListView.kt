package com.example.composechallengepuppyadoption.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.composechallengepuppyadoption.PUPPY_DETAIL_VIEW
import com.example.composechallengepuppyadoption.data.Puppy
import com.example.composechallengepuppyadoption.ui.theme.ComposeChallengePuppyAdoptionTheme
import com.google.gson.Gson

@ExperimentalFoundationApi
@Composable
fun PuppyListView(puppies: List<Puppy>, navController: NavController) {
    ComposeChallengePuppyAdoptionTheme {
        AppTopBar("Adopt a Puppy") { AdoptableList(puppies = puppies, navController) }
    }
}

fun navigateToPuppyDetailView(puppy: Puppy, navController: NavController) {
    val puppyJson = Gson().toJson(puppy)
    navController.navigate("$PUPPY_DETAIL_VIEW/$puppyJson")
}

@Composable
private fun CharacterHeader(initial: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.secondary)
    ) {
        Text(
            text = initial,
            modifier = Modifier.padding(start = 8.dp, top = 4.dp, bottom = 4.dp),
            style = TextStyle(color = MaterialTheme.colors.onSecondary)
        )
    }
}

@ExperimentalFoundationApi
@Composable
private fun AdoptableList(puppies: List<Puppy>, navController: NavController) {
    LazyColumn(Modifier.fillMaxWidth()) {
        val grouped = puppies.groupBy { it.name[0] }
        grouped.forEach { (initial, puppies) ->
            stickyHeader { CharacterHeader(initial.toString()) }
            items(puppies.size) { puppy ->
                PuppyCard(puppy = puppies[puppy], navController = navController)
            }
        }
    }
}

@Composable
private fun PuppyCard(puppy: Puppy, navController: NavController) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { navigateToPuppyDetailView(puppy, navController) }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = puppy.image),
                contentDescription = "${puppy.name}'s picture",
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(percent = 8)),
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = puppy.name,
                    fontSize = 32.sp
                )
            }
        }
    }
}