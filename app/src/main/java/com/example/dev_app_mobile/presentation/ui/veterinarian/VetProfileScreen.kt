package com.example.dev_app_mobile.presentation.ui.veterinarian

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dev_app_mobile.R
import com.example.dev_app_mobile.presentation.ui.theme.DevappmobileTheme
import com.example.dev_app_mobile.presentation.ui.theme.PetCarePrimary
import com.example.dev_app_mobile.presentation.ui.theme.PetCareWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VeterinarianProfileScreen(
    navController: NavController,
    veterinarianName: String = "Dra. Melissa",
    email: String = "melissavet@gmail.com",
    phone: String = "+57 315 534 55"
) {
    var selectedPlan by remember { mutableStateOf("Free") }
    var lyraSelected by remember { mutableStateOf(false) }
    var mateoSelected by remember { mutableStateOf(true) }

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
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver"
                )
            }

            Text(
                text = "Perfil",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            // Espacio para alinear el título
            Spacer(modifier = Modifier.size(48.dp))
        }

        // Profile Info
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Photo
            Image(
                painter = painterResource(id = R.drawable.veterinaria), // Necesitas este drawable
                contentDescription = "Foto de $veterinarianName",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(PetCarePrimary),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Name
            Text(
                text = veterinarianName,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Plan Badge
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = if (selectedPlan == "Premium") Color(0xFFFFD700) else Color(0xFF9E9E9E),
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Text(
                    text = selectedPlan,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                )
            }
        }

        // Datos personales
        ProfileSection(
            title = "Datos personales",
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Email
                Row(
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Correo",
                        tint = Color.Gray,
                        modifier = Modifier
                            .size(20.dp)
                            .padding(top = 2.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Correo electrónico:",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray
                        )
                        Text(
                            text = email,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        )
                    }
                }

                // Phone
                Row(
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "Teléfono",
                        tint = Color.Gray,
                        modifier = Modifier
                            .size(20.dp)
                            .padding(top = 2.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Teléfono:",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray
                        )
                        Text(
                            text = phone,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        )
                    }
                }
            }
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp),
            thickness = 1.dp,
            color = Color.Gray.copy(alpha = 0.3f)
        )

        // Gestión de pacientes
        ProfileSection(
            title = "Gestión de pacientes",
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Lyra
                PatientToggleItem(
                    petName = "Lyra",
                    ownerName = "Karen",
                    isSelected = lyraSelected,
                    onToggle = { lyraSelected = it }
                )

                // Mateo
                PatientToggleItem(
                    petName = "Mateo",
                    ownerName = "Carlos",
                    isSelected = mateoSelected,
                    onToggle = { mateoSelected = it }
                )
            }
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp),
            thickness = 1.dp,
            color = Color.Gray.copy(alpha = 0.3f)
        )

        // Planes y pagos
        ProfileSection(
            title = "Planes y pagos",
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Estado del plan
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Estado actual del plan:",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )

                    // Plan selector
                    var expanded by remember { mutableStateOf(false) }
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = selectedPlan,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            modifier = Modifier.menuAnchor(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = PetCarePrimary,
                                unfocusedBorderColor = Color.Gray.copy(alpha = 0.5f)
                            )
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Free") },
                                onClick = {
                                    selectedPlan = "Free"
                                    expanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Premium") },
                                onClick = {
                                    selectedPlan = "Premium"
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                // Historial de pagos
                ProfileMenuItem(
                    icon = Icons.Default.History,
                    text = "Historial de pagos",
                    onClick = { navController.navigate("payment_history") }
                )

                // Gestionar mi plan
                ProfileMenuItem(
                    icon = Icons.Default.ManageAccounts,
                    text = "Gestionar mi plan",
                    onClick = { navController.navigate("manage_plan") }
                )
            }
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp),
            thickness = 1.dp,
            color = Color.Gray.copy(alpha = 0.3f)
        )

        // Soporte y ayuda
        ProfileSection(
            title = "Soporte y ayuda",
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ProfileMenuItem(
                    icon = Icons.Default.Help,
                    text = "Centro de ayuda",
                    onClick = { navController.navigate("help_center") }
                )

                ProfileMenuItem(
                    icon = Icons.Default.SupportAgent,
                    text = "Contactar soporte",
                    onClick = { navController.navigate("contact_support") }
                )

                ProfileMenuItem(
                    icon = Icons.Default.Description,
                    text = "Términos y condiciones",
                    onClick = { navController.navigate("terms_conditions") }
                )
            }
        }

        // Cerrar sesión
        OutlinedButton(
            onClick = {
                // Handle logout logic
                navController.navigate("login") {
                    popUpTo(0)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 24.dp)
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            border = ButtonDefaults.outlinedButtonBorder.copy(
                width = 2.dp
            ),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color(0xFFD32F2F) // Rojo para cerrar sesión
            )
        ) {
            Icon(
                imageVector = Icons.Default.Logout,
                contentDescription = "Cerrar sesión",
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Cerrar sesión",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun ProfileSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        content()
    }
}

@Composable
fun PatientToggleItem(
    petName: String,
    ownerName: String,
    isSelected: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = petName,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            Text(
                text = "Dueño: $ownerName",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
        }

        Switch(
            checked = isSelected,
            onCheckedChange = onToggle,
            colors = SwitchDefaults.colors(
                checkedThumbColor = PetCareWhite,
                checkedTrackColor = PetCarePrimary,
                uncheckedThumbColor = PetCareWhite,
                uncheckedTrackColor = Color.Gray.copy(alpha = 0.5f)
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileMenuItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Ver más",
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewVeterinarianProfileScreen() {
    DevappmobileTheme {
        VeterinarianProfileScreen(navController = rememberNavController())
    }
}