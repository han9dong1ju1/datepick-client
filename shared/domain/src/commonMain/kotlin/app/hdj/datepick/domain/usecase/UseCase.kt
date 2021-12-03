package app.hdj.datepick.domain.usecase

interface UseCase<I, O> {
    operator fun invoke(input: I): O
}

operator fun <O> UseCase<Unit, O>.invoke() = this(Unit)