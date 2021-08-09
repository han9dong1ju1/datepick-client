package app.hdj.datepick.domain.usecase

interface UseCase<I, O> {
    suspend operator fun invoke(input: I): O
}