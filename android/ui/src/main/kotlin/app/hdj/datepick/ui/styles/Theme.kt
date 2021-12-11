package app.hdj.datepick.ui.styles

import android.os.Build
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

/*
Primary                 	#ff6750a4	#ffd0bcff *
On Primary                 	#ffffffff	#ff381e72 *
Primary Container           #ffeaddff	#ff4f378b *
On Primary Container        #ff21005d	#ffeaddff *
Inverse Primary             #ffd0bcff	#ff6750a4 *

Secondary                 	#ff625b71	#ffccc2dc *
On Secondary                #ffffffff	#ff332d41 *
Secondary Container         #ffe8def8	#ff4a4458 *
On Secondary Container      #ff1d192b	#ffe8def8 *

Tertiary                 	#ff7d5260	#ffefb8c8 *
On Tertiary                 #ffffffff	#ff492532 *
Tertiary Container          #ffffd8e4	#ff633b48 *
On Tertiary Container       #ff31111d   #ffffd8e4 *

Error                 	    #ffb3261e	#fff2b8b5 *
On Error                  	#ffffffff	#ff601410 *
Error Container             #fff9dedc	#ff8c1d18 *
On Error Container          #ff410e0b	#fff2b8b5 *

Outline                   	#ff79747e   #ff938f99 *

Background              	#fffffbfe   #ff1c1b1f *
On Background               #ff1c1b1f   #ffe6e1e5 *

Surface                   	#fffffbfe   #ff1c1b1f *
On Surface                  #ff1c1b1f   #ffe6e1e5 *

Surface Variant             #ffe7e0ec   #ff49454f *
On Surface Variant          #ff49454f   #ffcac4d0 *

Inverse Surface             #ff313033   #ffe6e1e5 *
Inverse On Surface          #fff4eff4   #ff313033 *
*/


private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF8B374B),
    onPrimary = Color(0xFFFFDDE7),
    primaryContainer = Color(0xFF8B374B),
    onPrimaryContainer = Color(0xFFFFDDE7),
    inversePrimary = Color(0xFFA45064),

    secondary = Color(0xFFDCC2C9),
    onSecondary = Color(0xFF412D33),
    secondaryContainer = Color(0xFF584448),
    onSecondaryContainer = Color(0xFFF8DEE4),

    tertiary = Color(0xFFBAB8EF),
    onTertiary = Color(0xFF262549),
    tertiaryContainer = Color(0xFF3C3B63),
    onTertiaryContainer = Color(0xFFDED8FF),

    error = Color(0xfff2b8b5),
    onError = Color(0xff601410),
    errorContainer = Color(0xff8c1d18),
    onErrorContainer = Color(0xfff2b8b5),

    outline = Color(0xff938f99),

    background = Color(0xFF1D1517),
    onBackground = Color(0xFFE6E1E3),

    surface = Color(0xFF1D1517),
    onSurface = Color(0xFFE6E1E2),
    surfaceVariant = Color(0xFF4F4549),
    onSurfaceVariant = Color(0xFFD0C4C9),

    inverseSurface = Color(0xFFE6E1E3),
    inverseOnSurface = Color(0xFF333031),
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFDD6A80),
    onPrimary = Color(0xffffffff),
    primaryContainer = Color(0xFFDD6A80),
    onPrimaryContainer = Color(0xffffffff),
    inversePrimary = Color(0xfff2b8b5),

    secondary = Color(0xFFDD6A80),
    onSecondary = Color(0xffffffff),
    secondaryContainer = Color(0xFFF8DEE3),
    onSecondaryContainer = Color(0xFF702F45),

    tertiary = Color(0xff7d5260),
    onTertiary = Color(0xffffffff),
    tertiaryContainer = Color(0xffffd8e4),
    onTertiaryContainer = Color(0xff31111d),

    error = Color(0xffb3261e),
    onError = Color(0xffffffff),
    errorContainer = Color(0xfff9dedc),
    onErrorContainer = Color(0xff410e0b),

    outline = Color(0xff79747e),

    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF1D1517),

    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1D1517),
    surfaceVariant = Color(0xFFFFEDF1),
    onSurfaceVariant = Color(0xFF66263F),

    inverseSurface = Color(0xFF333031),
    inverseOnSurface = Color(0xFFE6E1E3),
)

@Composable
fun BaseTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val colorSchemes = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        if (isDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        if (isDarkTheme) DarkColorScheme else LightColorScheme
    } else {
        if (isDarkTheme) DarkColorScheme else LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorSchemes,
        typography = Typographies,
        content = {
            androidx.compose.material.MaterialTheme(
                colors = Colors(
                    primary = MaterialTheme.colorScheme.primary,
                    primaryVariant = MaterialTheme.colorScheme.primaryContainer,
                    secondary = MaterialTheme.colorScheme.secondary,
                    secondaryVariant = MaterialTheme.colorScheme.surfaceVariant,
                    background = MaterialTheme.colorScheme.background,
                    surface = MaterialTheme.colorScheme.surface,
                    error = MaterialTheme.colorScheme.error,
                    onPrimary = MaterialTheme.colorScheme.onPrimary,
                    onSecondary = MaterialTheme.colorScheme.onSecondary,
                    onBackground = MaterialTheme.colorScheme.onBackground,
                    onSurface = MaterialTheme.colorScheme.onSurface,
                    onError = MaterialTheme.colorScheme.onError,
                    isLight = !isDarkTheme
                )
            ) {
                content()
            }
        }
    )
}