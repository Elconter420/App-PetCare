package com.example.dev_app_mobile.presentation.ui.veterinarian

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dev_app_mobile.presentation.components.dialogs.PaymentReceiptDialog
import com.example.dev_app_mobile.presentation.ui.theme.PetCarePrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentHistoryScreen(
    navController: NavController
) {
    var showReceiptDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Historial de pagos 🧾",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F6F6))
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            // Card: Estado actual del plan
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                shape = RoundedCornerShape(18.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Estado actual del plan:",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )

                    Text(
                        text = "Plan actual: Premium 💎",
                        fontSize = 14.sp
                    )
                    Text(
                        text = "Renovación automática: Activada",
                        fontSize = 14.sp
                    )
                    Text(
                        text = "Próximo cobro: 25/10/2025",
                        fontSize = 14.sp
                    )
                    Text(
                        text = "Método de pago: Tarjeta Visa •••• 5432",
                        fontSize = 14.sp
                    )
                }
            }

            // Separador "Pagos anteriores"
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                color = PetCarePrimary,
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Pagos anteriores",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color.White
                )
            }

            // Lista de pagos
            PaymentHistoryItem(
                month = "Septiembre 2025",
                amount = "$19.900 COP",
                method = "Tarjeta Visa •••• 5432",
                status = "✅ Pagado",
                onViewReceipt = { showReceiptDialog = true }
            )

            Spacer(modifier = Modifier.height(12.dp))

            PaymentHistoryItem(
                month = "Agosto 2025",
                amount = "$19.900 COP",
                method = "Tarjeta Visa •••• 5432",
                status = "✅ Pagado",
                onViewReceipt = { showReceiptDialog = true }
            )

            Spacer(modifier = Modifier.height(12.dp))

            PaymentHistoryItem(
                month = "Julio 2025",
                amount = "$19.900 COP",
                method = "Tarjeta Visa •••• 5432",
                status = "✅ Pagado",
                onViewReceipt = { showReceiptDialog = true }
            )

            Spacer(modifier = Modifier.height(12.dp))

            PaymentHistoryItem(
                month = "Junio 2025",
                amount = "$19.900 COP",
                method = "Tarjeta Visa •••• 5432",
                status = "✅ Pagado",
                onViewReceipt = { showReceiptDialog = true }
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }

    // Diálogo de recibo - Fuera del Scaffold para que esté encima de todo
    PaymentReceiptDialog(
        isVisible = showReceiptDialog,
        onDownloadPdf = {
            // TODO: Implementar descarga real de PDF
            println("Descargando recibo PDF...")
            showReceiptDialog = false
        },
        onClose = { showReceiptDialog = false }
    )
}

@Composable
private fun PaymentHistoryItem(
    month: String,
    amount: String,
    method: String,
    status: String,
    onViewReceipt: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "📅 $month",
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )
            Text(
                text = "💰 $amount",
                fontSize = 14.sp
            )
            Text(
                text = "Método: $method",
                fontSize = 14.sp
            )
            Text(
                text = "Estado: $status",
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Ver recibo",
                color = Color(0xFF1C6FD0),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .clickable(
                        onClick = onViewReceipt,
                    )
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PaymentHistoryScreenPreview() {
    MaterialTheme {
        PaymentHistoryScreen(navController = rememberNavController())
    }
}