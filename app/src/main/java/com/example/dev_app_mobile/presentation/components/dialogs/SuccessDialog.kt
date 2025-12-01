package com.example.dev_app_mobile.presentation.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.dev_app_mobile.presentation.ui.theme.PetCarePrimary

@Composable
fun SuccessDialog(
    onLoginClick: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnClickOutside = true)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(20.dp))
                    .padding(horizontal = 24.dp, vertical = 26.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "¡Registro\ncompletado con\néxito! 🐾🐾\nBienvenido a la\nfamilia PetCare.",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    style = LocalTextStyle.current,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        onLoginClick()
                        onDismiss()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PetCarePrimary,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(24.dp),
                    elevation = ButtonDefaults.buttonElevation(6.dp)
                ) {
                    Text(
                        text = "Iniciar sesión",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SuccessDialogPreview() {
    SuccessDialog()
}
