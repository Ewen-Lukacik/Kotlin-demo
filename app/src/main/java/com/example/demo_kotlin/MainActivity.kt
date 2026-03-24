package com.example.demo_kotlin

import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demo_kotlin.ui.theme.DemoKotlinTheme
import androidx.compose.runtime.*
import androidx.compose.material3.Switch
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemoKotlinTheme {
                /*
                Scaffold(
                    topBar = { TopApp() },
                    bottomBar = { BottomBar() },
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    VolumeScreen(innerPadding)
                }
                */
                 Dashboard()
            }
        }
    }
}


data class DashboardItem(
    val title: String,
    val description: String,
    var isFavorite: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard() {
    val items = remember {
        mutableStateListOf(
            DashboardItem("Test 1", "Exemple test 1"),
            DashboardItem("Test 2", "Exemple test 2"),
            DashboardItem("Test 3", "Exemple test 3"),
            DashboardItem("Test 4", "Exemple test 4"),
            DashboardItem("Test 5", "Exemple test 5")
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Dashboard") })
        }
    ) { innerPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = innerPadding,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(items.size) { index ->
                DashboardCard(
                    item = items[index],
                    onFavoriteClick = {
                        items[index] = items[index].copy(isFavorite = !items[index].isFavorite)
                    }
                )
            }
        }
    }
}

@Composable
fun DashboardCard(item: DashboardItem, onFavoriteClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.landscape),
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(12.dp)
            ) {
                Text(item.title, style = MaterialTheme.typography.titleMedium)
                Text(item.description, style = MaterialTheme.typography.bodySmall)
                Button(
                    onClick = onFavoriteClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (item.isFavorite) "Favori" else "Ajouter aux favoris")
                }
            }
        }
    }
}

@Composable
fun VolumeScreen(innerPadding: PaddingValues = PaddingValues()) {
    var volume by remember { mutableStateOf(0.5f) }
    var brightness by remember { mutableStateOf(0.5f) }
    var isSilent by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp)
    ) {
        Column {
            Text("Volume : ${(volume * 100).toInt()}%")
            Slider(
                value = volume,
                onValueChange = { volume = it },
                enabled = !isSilent
            )
        }
        Column {
            Text("Luminosité : ${(brightness * 100).toInt()}%")
            Slider(
                value = brightness,
                onValueChange = { brightness = it }
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Mode silencieux : ${if (isSilent) "ON" else "OFF"}")
            Switch(
                checked = isSilent,
                onCheckedChange = { isSilent = it }
            )
        }
    }
}

@Composable
fun UserProfile(innerPadding: PaddingValues = PaddingValues()) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.landscape),
            contentDescription = "Photo de profil",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )
        Text("Emma",)
        Row(
            horizontalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterHorizontally),
            modifier = Modifier.fillMaxWidth()
        ) {
            StatItem("248", "Posts")
            StatItem("12.4k", "Followers")
            StatItem("180", "Following")
        }

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Suivre")
        }
    }
}

@Composable
fun StatItem(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, style = MaterialTheme.typography.titleMedium)
        Text(label, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun DetailedProductCard(innerPadding: PaddingValues = PaddingValues()){
    Card(
        modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)
            .height(150.dp)
    ) {
        Row(

        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.landscape),
                    contentDescription = "Example image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(CardDefaults.shape)
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween

            ) {
                Row(

                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text("Nom produit")
                        Text("Prix")
                    }
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite"
                        )
                    }
                }

                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(bottomEnd = 12.dp)
                ) {
                    Text("Ajouter au panier")
                }
            }
        }
    }
}

@Composable
fun ListFruits(innerPadding: PaddingValues = PaddingValues()){
    val fruits = listOf("Banane", "Orange", "Pomme")
    var selectedFruit by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(fruits.size) {
                index ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable() { selectedFruit = fruits[index] }
                ) {
                    Text(fruits[index])
                }
            }
        }
        Text("Vous avez choisi le fruit: " + selectedFruit)

    }
}


class Task(
    val title: String,
    val description: String
){}

@Composable
fun ListTasks(innerPadding: PaddingValues = PaddingValues()){
    val tasks = listOf(
        Task("Task 1", "Finish task 1"),
        Task("Task 2", "Finish task 2"),
        Task("Task 3", "Finish task 3"),
        Task("Task 4", "Finish task 4"),
        Task("Task 5", "Finish task 5"))

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = innerPadding,
        modifier = Modifier.padding(16.dp)
    ) {
        items(tasks.size) {
            index ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(tasks[index].title + ": ")
                Text(tasks[index].description)
                Button(onClick = {}) {
                    Text("Terminé")
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(){
    BottomAppBar() {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {}) {
                Text("Home")
            }
            Button(onClick = {}) {
                Text("Profil")
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopApp(){
    TopAppBar(
        title = {
            Text("Tableau de bord")
        },
    )
}

@Composable
fun UserPreferences(){
    var isOn by remember { mutableStateOf(false) }
    var checked by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Switch(
                checked = isOn,
                onCheckedChange = { isOn = it }
            )
            Text("Activer notifications : ${if (isOn) "ON" else "OFF"}")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { checked = it }
            )
            Text("Recevoir newsletter : ${if (checked) "Coché" else "Décoché"}")
        }
    }

}


@Composable
fun Research(){
    var query by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        TextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Rechercher") }
        )
        Text("Résultat : $query")
    }
}

@Composable
fun ColorSquareApp() {
    var currentColor by remember { mutableStateOf(Color.Red) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize().padding(24.dp)
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(currentColor)
        )

        Button(onClick = { currentColor = Color.Red }) {
            Text("Rouge")
        }
        Button(onClick = { currentColor = Color.Green }) {
            Text("Vert")
        }
        Button(onClick = { currentColor = Color.Blue }) {
            Text("Bleu")
        }
    }
}

@Composable
fun PictureCaption(){
    Box() {
        Image(
            painter = painterResource(id = R.drawable.landscape),
            contentDescription = "Example image"
        )
        Text(
            "Vacances 2026",
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }

}

@Composable
fun MeteoCard(){
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = "Info"
        )
        Text("24°")
        Text("Ensoleillé")
    }
}

@Composable
fun UserCard() {
    Card() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Emma")
            Text("27 ans")
            Text("Designer")
        }
    }
}