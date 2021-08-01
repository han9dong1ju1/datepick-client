package app.hdj.datepick

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.Long
import kotlin.String

public interface PlaceEntityQueries : Transacter {
  public fun <T : Any> getPlace(id: String, mapper: (
    id: String,
    kakaoId: Long,
    name: String
  ) -> T): Query<T>

  public fun getPlace(id: String): Query<PlaceEntity>
}
