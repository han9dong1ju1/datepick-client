package app.hdj.datepick

import kotlin.Long
import kotlin.String

public data class PlaceEntity(
  public val id: String,
  public val kakaoId: Long,
  public val name: String
) {
  public override fun toString(): String = """
  |PlaceEntity [
  |  id: $id
  |  kakaoId: $kakaoId
  |  name: $name
  |]
  """.trimMargin()
}
