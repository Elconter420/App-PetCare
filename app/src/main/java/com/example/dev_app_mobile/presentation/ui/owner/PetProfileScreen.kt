package com.example.dev_app_mobile.presentation.ui.owner

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
fun PetProfileScreen(
    onBackClick: () -> Unit = {},
    onOpenHistoryClick: () -> Unit = {},
    onOpenAgendaClick: () -> Unit = {},
    onEditPetClick: () -> Unit = {},
    onDeletePetClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 12.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {

        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 4.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Volver"
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier.size(125.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Image(
                    painter = painterResource(id = R.drawable.lyra),
                    contentDescription = "Foto mascota",
                    modifier = Modifier
                        .size(125.dp)
                        .clip(CircleShape)
                )

                Surface(
                    shape = CircleShape,
                    shadowElevation = 4.dp,
                    color = PetCareWhite,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.CameraAlt,
                        contentDescription = "Cambiar foto",
                        modifier = Modifier.padding(6.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))


            Text(
                text = "Lyra",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = PetCareDark
            )

            Spacer(modifier = Modifier.height(3.dp))


            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.perro_3),
                    contentDescription = "Icono perro",
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Perro/a",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        PetSectionHeader("Datos")

        Surface(
            shape = RoundedCornerShape(10.dp),
            shadowElevation = 1.dp,
            color = Color.White,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {

                Text("Raza:", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                Text("Criolla", fontSize = 14.sp, modifier = Modifier.padding(bottom = 12.dp))

                Text("Edad:", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                Text("10 años", fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        PetSectionHeader("Historial")

        PetOptionCard(
            text = "Historial medico y de servicios",
            enabled = true,
            onClick = onOpenHistoryClick
        )

        Spacer(modifier = Modifier.height(10.dp))

        PetOptionCard(
            text = "Agenda (Próximas citas)",
            enabled = false,
            onClick = onOpenAgendaClick
        )

        Spacer(modifier = Modifier.height(18.dp))

        PetSectionHeader("Gestión")

        PetOptionCard(
            text = "Editar información",
            enabled = true,
            onClick = onEditPetClick
        )

        Spacer(modifier = Modifier.height(10.dp))

        Surface(
            shape = RoundedCornerShape(10.dp),
            shadowElevation = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onDeletePetClick),
            color = Color.White
        ) {
            Text(
                text = "Eliminar mascota",
                color = Color(0xFFD32F2F),
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp)
            )
        }
    }
}


@Composable
private fun PetSectionHeader(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFC7E4DF)) // ✔ exactamente el verde de la imagen
            .padding(horizontal = 10.dp, vertical = 6.dp)
    )
}

@Composable
private fun PetOptionCard(
    text: String,
    enabled: Boolean,
    onClick: () -> Unit
) {
    val alpha = if (enabled) 1f else 0.5f

    Surface(
        shape = RoundedCornerShape(10.dp),
        shadowElevation = 1.dp,
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (enabled) Modifier.clickable(onClick = onClick)
                else Modifier
            )
    ) {
        Text(
            text = text,
            color = Color.Black.copy(alpha = alpha),
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PetProfileScreenPreview() {
    PetProfileScreen()
}