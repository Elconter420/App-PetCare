package com.example.dev_app_mobile.presentation.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// Tema oscuro usando la paleta
private val DarkColorScheme = darkColorScheme(
    primary = PetCareDark,
    secondary = PetCareSecondary,
    tertiary = PetCarePrimary,
)

//Tema claro usando la paleta
private val LightColorScheme = lightColorScheme(
    primary = PetCarePrimary,
    secondary = PetCareSecondary,
    tertiary = PetCareDark,
    // si quieres luego puedes ajustar background/surface/onPrimary, etc.
)

@Composable
fun DevappmobileTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Colores dinámicos (Android 12+)
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
