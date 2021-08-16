package app.hdj.datepick.android.ui.components.screens.others.place

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.components.screens.others.place.PlaceViewModelDelegate.*
import app.hdj.datepick.android.ui.providers.preview.FakePlacePreviewProvider
import app.hdj.datepick.domain.StateData
import app.hdj.datepick.domain.isStateSucceed
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.usecase.place.GetPlaceByIdUseCase
import app.hdj.datepick.ui.utils.ViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


fun fakePlaceViewModel() = object : PlaceViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(
        State(StateData.success(FakePlacePreviewProvider().values.first().first()))
    )

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) = Unit

}

interface PlaceViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    data class State(
        val place: StateData<Place> = StateData.loading()
    )

    sealed class Effect {
        class ShowToastMessage(val message: String) : Effect()
    }

    sealed class Event {
        object ReloadContents : Event()
        class RequestPlace(val id: Long) : Event()
        class ShowPassedPlace(val place: Place) : Event()
        object LikePlace : Event()
    }

}

@HiltViewModel
@OptIn(FlowPreview::class)
class PlaceViewModel @Inject constructor(
    private val getPlaceById: GetPlaceByIdUseCase
) : ViewModel(), PlaceViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect = effectChannel.receiveAsFlow()

    private val place = MutableStateFlow<StateData<Place>>(StateData.loading())

    private val placeId =
        place.filter { it.isStateSucceed() }.map { (it as StateData.Success).data.id }

    private val blogReviews = placeId.map { }

    private val reviews = placeId.map { }

    private val relatedCourses = placeId.map { }

    override val state: StateFlow<State> = combine(
        place,
        blogReviews,
        reviews,
        relatedCourses
    ) { place, blogReviews, reviews, relatedCourses ->
        State(place)
    }.stateIn(viewModelScope, SharingStarted.Lazily, State())

    override fun event(event: Event) {
        viewModelScope.launch {
            when (event) {
                Event.LikePlace -> {

                }
                Event.ReloadContents -> Unit
                is Event.RequestPlace -> {
                    getPlaceById.execute(id = event.id).collect(place::emit)
                }
                is Event.ShowPassedPlace -> {
                    place.emit(StateData.success(event.place))
                }
            }
        }
    }

}