package app.hdj.datepick.android.utils


fun <T> List<T>.moved(fromIdx: Int, toIdx: Int) : List<T>{
    val mutable = this.toMutableList()
    when {
        fromIdx == toIdx -> {
            return this
        }
        toIdx > fromIdx -> {
            (fromIdx until toIdx).forEach { i ->
                mutable[i] = mutable[i + 1].also { mutable[i + 1] = mutable[i] }
            }
        }
        else -> {
            (fromIdx downTo toIdx + 1).forEach { i ->
                mutable[i] = mutable[i - 1].also { mutable[i - 1] = mutable[i] }
            }
        }
    }
    return mutable
}