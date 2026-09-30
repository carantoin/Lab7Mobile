package com.example.solarguitars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.solarguitars.ui.theme.SolarGuitarsTheme
import com.example.solarguitars.ui.theme.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SolarGuitarsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = DarkBackground
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    var currentScreen by remember { mutableStateOf(0) }

    when (currentScreen) {
        0 -> GuitarDetailScreen(
            onNext = { currentScreen = 1 },
            onPrev = { currentScreen = 2 }
        )
        1 -> FilterScreen(
            onBack = { currentScreen = 0 }
        )
        2 -> ArtistScreen(
            onNext = { currentScreen = 0 }
        )
    }
}

@Composable
fun GuitarDetailScreen(onNext: () -> Unit, onPrev: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("SOLAR", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = TextWhite)
            Icon(Icons.Default.Menu, contentDescription = "Menu", tint = TextWhite)
        }

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("A1.8C", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = TextWhite)
            Text("CARBON", fontSize = 16.sp, color = TextGray, letterSpacing = 2.sp)
            Text("BLACK MATT", fontSize = 16.sp, color = TextGray, letterSpacing = 2.sp)

            Spacer(modifier = Modifier.height(32.dp))

            Box(
                modifier = Modifier
                    .height(400.dp)
                    .width(150.dp)
                    .background(Color.DarkGray, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("Guitar Image", color = TextGray)
            }
        }

        Text(
            text = "SPECS",
            color = TextWhite,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .rotateVertically(),
            fontWeight = FontWeight.Bold,
            letterSpacing = 4.sp
        )

        Column(
            modifier = Modifier.align(Alignment.CenterEnd),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FloatingActionButton(
                onClick = onNext,
                containerColor = TextWhite,
                contentColor = DarkBackground,
                shape = CircleShape
            ) {
                Icon(Icons.Default.ArrowForward, contentDescription = "Next")
            }
            FloatingActionButton(
                onClick = onPrev,
                containerColor = Color.DarkGray,
                contentColor = TextWhite,
                shape = CircleShape
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Prev")
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column {
                Text("1.299€", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                Text("PREORDER", fontSize = 12.sp, color = TextGray)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Filters", color = TextWhite, fontSize = 16.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(TextWhite, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("2", color = DarkBackground, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun FilterScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEEEEEE))
            .padding(24.dp)
    ) {
        Text(
            "Filter options",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        val filters = listOf("Type V", "Scale", "Number of strings", "Shipping Around", "Color Body", "Body Wood")
        filters.forEach { filter ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(filter, fontSize = 18.sp, color = Color.Black)
                Icon(Icons.Default.ArrowBack, contentDescription = null, modifier = Modifier.rotateVertically(), tint = Color.Black) // Используем стрелку как галочку вниз
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ProductCard(name = "V2.6MDS", price = "749€")
            ProductCard(name = "V1.7FBB", price = "1.299€")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Back to Details")
        }
    }
}

@Composable
fun ProductCard(name: String, price: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(100.dp, 150.dp)
                .background(Color.LightGray, RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(name, fontWeight = FontWeight.Bold, color = Color.Black)
        Text(price, color = Color.Gray)
    }
}

@Composable
fun ArtistScreen(onNext: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ) {
            Text("Artist Photo Here", modifier = Modifier.align(Alignment.Center), color = TextWhite)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("SOLAR", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = TextWhite)
            Icon(Icons.Default.Menu, contentDescription = "Menu", tint = TextWhite)
        }

        Text(
            text = "COMPARE SOLAR GUITARS",
            color = TextWhite,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 16.dp)
                .rotateVertically(),
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )

        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF222222)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    text = "«We have put every possible effort into designing the finest looking, most extreme metal playing and selling instruments on the market today»",
                    color = TextWhite,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Ola Englund", color = TextWhite, fontWeight = FontWeight.Bold)
                    Text("Signature", color = TextGray, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)
                }
            }
        }

        Button(
            onClick = onNext,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 80.dp, end = 24.dp)
        ) {
            Text("Next")
        }
    }
}

fun Modifier.rotateVertically() = this.then(
    Modifier.graphicsLayer(rotationZ = -90f)
)