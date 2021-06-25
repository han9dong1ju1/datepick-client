package app.hdj.shared.client.data.repo

import app.hdj.shared.client.data.api.PlaceApi
import app.hdj.shared.client.data.cache.PlaceCache
import app.hdj.shared.client.data.repo.PlaceRepositoryImp
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DaggerPlaceRepositoryImp @Inject constructor(
    placeApi: PlaceApi,
    placeCache: PlaceCache
) : PlaceRepositoryImp(placeCache, placeApi)