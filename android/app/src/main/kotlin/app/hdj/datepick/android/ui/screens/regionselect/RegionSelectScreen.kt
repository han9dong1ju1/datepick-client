package app.hdj.datepick.android.ui.screens.regionselect

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.R
import app.hdj.datepick.android.data.CityData
import app.hdj.datepick.android.ui.components.list.Header
import app.hdj.datepick.ui.components.BaseScaffold
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Composable
@Destination
fun RegionSelectScreen(navigator: DestinationsNavigator) {

    val context = LocalContext.current

    var seoulCityLatLng by remember { mutableStateOf<List<CityData>>(emptyList()) }

    LaunchedEffect(true) {
        val parsedJson = async(Dispatchers.IO) {
            context.resources.openRawResource(R.raw.seoul_city_lat_lng)
                .bufferedReader()
                .use { it.readText() }
        }
        seoulCityLatLng = Json.decodeFromString(parsedJson.await())
    }

    BaseScaffold(
        modifier = Modifier.fillMaxSize().statusBarsPadding()
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth().padding(it)) {
            seoulCityLatLng.forEach {
                stickyHeader {
                    Surface(modifier = Modifier.fillMaxWidth()) { Header(it.district) }
                    Divider()
                }
                items(it.regions) { region ->
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(20.dp),
                        text = region.name
                    )
                }
            }
        }
    }


}