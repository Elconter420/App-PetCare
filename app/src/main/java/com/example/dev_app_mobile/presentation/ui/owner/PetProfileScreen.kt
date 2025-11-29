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
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier.size(110.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Image(
                    painter = painterResource(id = R.drawable.lyra), // tu foto de Lyra
                    contentDescription = "Foto de la mascota",
                    modifier = Modifier
                        .size(110.dp)
                        .clip(CircleShape)
                )

                Surface(
                    shape = CircleShape,
                    color = PetCareWhite,
                    tonalElevation = 2.dp,
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.CameraAlt,
                        contentDescription = "Cambiar foto",
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Lyra",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = PetCareDark
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.perro_3), // iconito / sticker
                    contentDescription = "Icono perro",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Perro/a",
                    fontSize = 13.sp,
                    color = PetCareDark
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        PetSectionHeader("Datos")

        Surface(
            shape = RoundedCornerShape(8.dp),
            tonalElevation = 1.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = "Raza:",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp
                )
                Text(
                    text = "Criolla",
                    fontSize = 13.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                Text(
                    text = "Edad:",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp
                )
                Text(
                    text = "10 años",
                    fontSize = 13.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        PetSectionHeader("Historial")

        // Historial médico y de servicios
        PetOptionCard(
            text = "Historial medico y de servicios",
            enabled = true,
            onClick = onOpenHistoryClick
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Agenda (esta deshabilitada visualmente)
        PetOptionCard(
            text = "Agenda (Próximas citas)",
            enabled = false,
            onClick = onOpenAgendaClick
        )

        Spacer(modifier = Modifier.height(16.dp))


        PetSectionHeader("Gestión")

        PetOptionCard(
            text = "Editar información",
            enabled = true,
            onClick = onEditPetClick
        )

        Spacer(modifier = Modifier.height(8.dp))


        Surface(
            shape = RoundedCornerShape(8.dp),
            tonalElevation = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onDeletePetClick)
        ) {
            Text(
                text = "Eliminar mascota",
                color = MaterialTheme.colorScheme.error,
                fontSize = 13.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                textAlign = TextAlign.Start
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun PetSectionHeader(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        modifier = Modifier
            .fillMaxWidth()
            .background(PetCarePrimary.copy(alpha = 0.4f))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
}

@Composable
private fun PetOptionCard(
    text: String,
    enabled: Boolean,
    onClick: () -> Unit
) {
    val bgColor =
        if (enabled) MaterialTheme.colorScheme.surface
        else MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)

    val textColor =
        if (enabled) MaterialTheme.colorScheme.onSurface
        else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)

    Surface(
        shape = RoundedCornerShape(8.dp),
        tonalElevation = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .let {
                if (enabled) it.clickable(onClick = onClick) else it
            }
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 13.sp,
            modifier = Modifier
                .background(bgColor)
                .padding(horizontal = 12.dp, vertical = 10.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PetProfileScreenPreview() {
    PetProfileScreen()
}
