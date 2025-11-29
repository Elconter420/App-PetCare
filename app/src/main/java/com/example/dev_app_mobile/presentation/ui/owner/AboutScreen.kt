package com.example.dev_app_mobile.presentation.ui.owner

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dev_app_mobile.R
import com.example.dev_app_mobile.presentation.ui.theme.PetCareDark
import com.example.dev_app_mobile.presentation.ui.theme.PetCarePrimary
import com.example.dev_app_mobile.presentation.ui.theme.PetCareWhite

@Composable
fun AboutScreen(
    onBackClick: () -> Unit = {},
    onBackToHome: () -> Unit = {}
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Volver"
                )
            }

            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Sobre nosotros",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "Conoce más sobre nuestro propósito.",
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Image(
            painter = painterResource(id = R.drawable.principal), // 👈 AJUSTA el nombre al tuyo
            contentDescription = "Imagen principal",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(PetCarePrimary.copy(alpha = 0.4f))
                .padding(16.dp)
        ) {
            Text(
                text = "PetCare nació con el propósito de conectar a los amantes " +
                        "de los animales con profesionales del cuidado y la salud que " +
                        "comparten la misma pasión. Somos una plataforma integral " +
                        "que reúne servicios veterinarios, peluquería, transporte, " +
                        "guardería y mucho más, todo en un solo lugar.",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Justify
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // --- Misión ---
        MissionVisionBubble(
            title = "MISIÓN",
            text = "Brindar bienestar y tranquilidad a las familias, asegurando que " +
                    "cada mascota reciba el cuidado que merece de manera fácil, segura " +
                    "y accesible.",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // --- Visión ---
        MissionVisionBubble(
            title = "VISIÓN",
            text = "Convertirnos en la plataforma líder de servicios integrales para " +
                    "mascotas en Latinoamérica, promoviendo el amor, la salud y la " +
                    "responsabilidad hacia los animales.",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(PetCareDark),
        ) {
            Image(
                painter = painterResource(id = R.drawable.nuestrosvalores), // 👈 AJUSTA nombre
                contentDescription = "Veterinario",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .fillMaxHeight()
                    .width(120.dp)
                    .clip(RoundedCornerShape(topEnd = 24.dp, bottomEnd = 24.dp))
            )

            Column(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Nuestros",
                    color = PetCareWhite,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Valores",
                    color = PetCareWhite,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Valores que nos inspiran:",
                fontWeight = FontWeight.SemiBold
            )

            ValueItem("Amor y respeto por los animales.\nConfianza y transparencia.")
            ValueItem("Innovación tecnológica al servicio del bienestar animal.")
            ValueItem("Cercanía con nuestros usuarios y profesionales.")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "En PetCare creemos que cuidar de una mascota es cuidar del " +
                        "corazón. Gracias por ser parte de nuestra comunidad que hace " +
                        "la diferencia cada día. 💚",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Image(
                painter = painterResource(id = R.drawable.principa), // 👈 gatito (ajusta nombre)
                contentDescription = "Gatito",
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onBackToHome,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PetCareDark,
                contentColor = PetCareWhite
            )
        ) {
            Text(text = "Volver al inicio")
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun MissionVisionBubble(
    title: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(220.dp)
            .height(150.dp)
            .clip(CircleShape)
            .background(PetCarePrimary.copy(alpha = 0.7f))
            .padding(12.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(PetCareDark)
                    .padding(horizontal = 12.dp, vertical = 2.dp)
            ) {
                Text(
                    text = title,
                    color = PetCareWhite,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = text,
                fontSize = 11.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun ValueItem(text: String) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "• ")
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AboutScreenPreview() {
    AboutScreen()
}
