package app.hdj.datepick.presentation.settings

import app.hdj.datepick.domain.settings.AppSettings
import app.hdj.datepick.presentation.PlatformViewModel
import app.hdj.datepick.presentation.UnidirectionalViewModelDelegate
import app.hdj.datepick.presentation.settings.SettingsScreenViewModelDelegate.*
import app.hdj.datepick.utils.AppInfo
import app.hdj.datepick.utils.di.HiltViewModel
import app.hdj.datepick.utils.di.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface SettingsScreenViewModelDelegate : UnidirectionalViewModelDelegate<
        State, Effect, Event> {

    class State(
        val appInfo: AppInfo = AppInfo(),
        val appTheme: AppSettings.AppTheme = AppSettings.AppTheme.System,
        val isNearbyRecommendBannerIgnored: Boolean = false,
    ) {

        class AppInfo(
            val version: String? = null,
            val isDebug: Boolean = false,
        )

    }

    class Effect

    sealed interface Event {
        class SwitchTheme(val appTheme: AppSettings.AppTheme) : Event
        class IgnoreNearbyRecommendedBanner(val value: Boolean) : Event
    }

}

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private val appInfo: AppInfo,
    private val appSettings: AppSettings
) : PlatformViewModel(), SettingsScreenViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.BUFFERED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    private val appInfoFlow = MutableStateFlow(appInfo)
    private val appTheme = appSettings.appTheme
    private val isNearbyRecommendBannerIgnored = appSettings.isNearbyRecommendBannerIgnored

    override val state: StateFlow<State> = combine(
        appInfoFlow,
        appTheme,
        isNearbyRecommendBannerIgnored
    ) { info, theme, isNearbyRecommendBannerIgnored ->
        State(
            appInfo = State.AppInfo(info.appVersion, info.debug),
            appTheme = theme,
            isNearbyRecommendBannerIgnored = isNearbyRecommendBannerIgnored ?: false
        )
    }.asStateFlow(State(), platformViewModelScope)

    override fun event(e: Event) {
        when (e) {
            is Event.SwitchTheme -> {
                platformViewModelScope.launch {
                    appSettings.updateAppTheme(e.appTheme)
                }
            }
            is Event.IgnoreNearbyRecommendedBanner -> {
                platformViewModelScope.launch {
                    appSettings.setNearbyRecommendBannerIgnored(e.value)
                }
            }
        }
    }

}