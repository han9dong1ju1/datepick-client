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
    primary = Color(0xffd0bcff),
    onPrimary = Color(0xff381e72),
    primaryContainer = Color(0xff4f378b),
    onPrimaryContainer = Color(0xffeaddff),
    inversePrimary = Color(0xff6750a4),

    secondary = Color(0xffccc2dc),
    onSecondary = Color(0xff332d41),
    secondaryContainer = Color(0xff4a4458),
    onSecondaryContainer = Color(0xffe8def8),

    tertiary = Color(0xffefb8c8),
    onTertiary = Color(0xff492532),
    tertiaryContainer = Color(0xff633b48),
    onTertiaryContainer = Color(0xffffd8e4),

    error = Color(0xfff2b8b5),
    onError = Color(0xff601410),
    errorContainer = Color(0xff8c1d18),
    onErrorContainer = Color(0xfff2b8b5),

    outline = Color(0xff938f99),

    background = Color(0xff1c1b1f),
    onBackground = Color(0xffe6e1e5),

    surface = Color(0xff1c1b1f),
    onSurface = Color(0xffe6e1e5),
    surfaceVariant = Color(0xff49454f),
    onSurfaceVariant = Color(0xffcac4d0),

    inverseSurface = Color(0xffe6e1e5),
    inverseOnSurface = Color(0xff313033),
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xff6750a4),
    onPrimary = Color(0xffffffff),
    primaryContainer = Color(0xffeaddff),
    onPrimaryContainer = Color(0xff21005d),
    inversePrimary = Color(0xffd0bcff),

    secondary = Color(0xff625b71),
    onSecondary = Color(0xffffffff),
    secondaryContainer = Color(0xffe8def8),
    onSecondaryContainer = Color(0xff1d192b),

    tertiary = Color(0xff7d5260),
    onTertiary = Color(0xffffffff),
    tertiaryContainer = Color(0xffffd8e4),
    onTertiaryContainer = Color(0xff31111d),

    error = Color(0xffb3261e),
    onError = Color(0xffffffff),
    errorContainer = Color(0xfff9dedc),
    onErrorContainer = Color(0xff410e0b),

    outline = Color(0xff79747e),

    background = Color(0xfffffbfe),
    onBackground = Color(0xff1c1b1f),

    surface = Color(0xfffffbfe),
    onSurface = Color(0xff1c1b1f),
    surfaceVariant = Color(0xffe7e0ec),
    onSurfaceVariant = Color(0xff49454f),

    inverseSurface = Color(0xff313033),
    inverseOnSurface = Color(0xfff4eff4),
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