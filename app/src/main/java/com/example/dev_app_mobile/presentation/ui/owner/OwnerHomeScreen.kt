package com.example.dev_app_mobile.presentation.ui.owner

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dev_app_mobile.R
import com.example.dev_app_mobile.presentation.ui.theme.DevappmobileTheme
import com.example.dev_app_mobile.presentation.ui.theme.PetCarePrimary
import com.example.dev_app_mobile.presentation.ui.theme.PetCareWhite

data class Pet(
    val id: Int,
    val name: String,
    val imageResId: Int,
    val type: String,
    val age: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnerMainScreen(
    navController: NavController,
    userName: String = "Karen"
) {
    val pets = listOf(
        Pet(1, "Lyra", R.drawable.lyra, "Perro", "3 años"),
        Pet(2, "Ryuk", R.drawable.ryuk, "Gato", "2 años")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PetCareWhite)
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Hola",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
                Text(
                    text = userName,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            IconButton(
                onClick = { navController.navigate("notifications") }
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notificaciones",
                    tint = Color.Black,
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        // Sección: Mis mascotas
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Mis mascotas",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                TextButton(
                    onClick = { navController.navigate("add_pet") }
                ) {
                    Text(
                        text = "Ver todas",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = PetCarePrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Lista horizontal de mascotas
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(pets) { pet ->
                    PetCard(
                        pet = pet,
                        onClick = { navController.navigate("pet_detail/${pet.id}") }
                    )
                }
            }
        }

        // Banner: Quiero ser premium
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp)
                .height(120.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFF3E0)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            onClick = { navController.navigate("premium") }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Premium",
                            tint = Color(0xFFFFB300),
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Quiero ser premium",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFE65100)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Desbloquea beneficios exclusivos para tus mascotas",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFFE65100).copy(alpha = 0.8f),
                        lineHeight = 18.sp
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.perro_plan),
                    contentDescription = "Corona premium",
                    modifier = Modifier.size(60.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }

        // Sección central: Imagen con información
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            // Imagen de fondo
            Image(
                painter = painterResource(id = R.drawable.sabes_cuidado), // Necesitas esta imagen
                contentDescription = "Cuidado de mascotas",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
        }

        // Botones de servicios circulares
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Primera fila: Peluquería
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                ServiceButton(
                    iconRes = R.drawable.peluqueria, // Necesitas este drawable
                    text = "Peluquería",
                    onClick = { navController.navigate("schedule_grooming") },
                    modifier = Modifier.size(140.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Segunda fila: Transporte y Guardería
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ServiceButton(
                    iconRes = R.drawable.transporte, // Necesitas este drawable
                    text = "Transporte",
                    onClick = { navController.navigate("schedule_transport") },
                    modifier = Modifier.size(140.dp)
                )

                ServiceButton(
                    iconRes = R.drawable.guarderia, // Necesitas este drawable
                    text = "Guardería",
                    onClick = { navController.navigate("schedule_daycare") },
                    modifier = Modifier.size(140.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetCard(
    pet: Pet,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(140.dp)
            .height(160.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = pet.imageResId),
                contentDescription = "Foto de ${pet.name}",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(PetCarePrimary),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = pet.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${pet.type}, ${pet.age}",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceButton(
    iconRes: Int,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.size(140.dp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(
                containerColor = PetCarePrimary.copy(alpha = 0.2f)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            onClick = onClick
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = text,
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewOwnerMainScreen() {
    DevappmobileTheme {
        OwnerMainScreen(navController = rememberNavController())
    }
}