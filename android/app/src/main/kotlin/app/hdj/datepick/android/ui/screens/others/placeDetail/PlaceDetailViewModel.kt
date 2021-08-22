package app.hdj.datepick.android.ui.screens.others.placeDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.providers.preview.FakePlaceBlogReviewPreviewProvider
import app.hdj.datepick.android.ui.screens.others.placeDetail.PlaceDetailViewModelDelegate.*
import app.hdj.datepick.android.ui.providers.preview.FakePlacePreviewProvider
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.isStateSucceed
import app.hdj.datepick.domain.model.place.BlogReview
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.usecase.place.GetPlaceByIdUseCase
import app.hdj.datepick.ui.utils.ViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


fun fakePlaceDetailViewModel() = object : PlaceDetailViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(
        State(
            LoadState.success(FakePlacePreviewProvider().values.first().first()),
            LoadState.success(FakePlacePreviewProvider().values.first()),
            LoadState.success(FakePlaceBlogReviewPreviewProvider().values.first()),
        )
    )

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) = Unit

}

interface PlaceDetailViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    data class State(
        val place: LoadState<Place> = LoadState.loading(),
        val similarPlaces: LoadState<List<Place>> = LoadState.loading(),
        val blogReviews: LoadState<List<BlogReview>> = LoadState.loading(),
    )

    sealed class Effect {
        class ErrorOccurred(val message: String) : Effect()
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
class PlaceDetailViewModel @Inject constructor(
    private val getPlaceById: GetPlaceByIdUseCase
) : ViewModel(), PlaceDetailViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect = effectChannel.receiveAsFlow()

    private val place = MutableStateFlow<LoadState<Place>>(LoadState.loading())

    private val placeId =
        place.filter { it.isStateSucceed() }.map { (it as LoadState.Success).data.id }

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
                    place.emit(LoadState.success(event.place))
                }
            }
        }
    }

}