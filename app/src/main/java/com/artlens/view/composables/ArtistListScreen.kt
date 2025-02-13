package com.artlens.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.artlens.R
import com.artlens.data.models.ArtistResponse

@Composable
fun ArtistListScreen(
    artists: List<ArtistResponse>,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onRecommendationClick: () -> Unit,
    onArtistClick: (Int) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Barra superior
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Flecha de retroceso
                IconButton(onClick = onBackClick) {
                    Image(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = "Back Arrow",
                        modifier = Modifier.size(30.dp)
                    )
                }

                // Título centrado
                Text(
                    text = "ARTISTS",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                // Icono de perfil a la derecha
                IconButton(onClick = { /* Acción de perfil */ }) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile Icon",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Grid de artistas con dos columnas
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 70.dp),  // Para dejar espacio a los botones inferiores
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(artists) { artist ->
                    ArtistItem(artist = artist, onClick = { onArtistClick(artist.pk) })
                }
            }
        }

        // Barra de navegación inferior sobrepuesta
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 8.dp)
                .height(50.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onHomeClick) {
                Image(
                    painter = painterResource(id = R.drawable.house),
                    contentDescription = "Home",
                    modifier = Modifier.size(30.dp)
                )
            }

            IconButton(onClick = { /* Acción para ir a Museos */ }) {
                Image(
                    painter = painterResource(id = R.drawable.camera),
                    contentDescription = "Museos",
                    modifier = Modifier.size(30.dp)
                )
            }

            IconButton(onClick = onRecommendationClick) {
                Image(
                    painter = painterResource(id = R.drawable.fire),
                    contentDescription = "Recommendations",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}

@Composable
fun ArtistItem(artist: ArtistResponse, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        // Imagen del artista
        Image(
            painter = rememberAsyncImagePainter(model = artist.fields.image),
            contentDescription = artist.fields.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Nombre del artista
        Text(
            text = artist.fields.name,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
    }
}

