package app.hdj.datepick.data.mapper

interface Mapper<DataModel, DomainModel> {

    fun DataModel.dataToDomain(): DomainModel

    fun List<DataModel>.mapDomain() = map { it.dataToDomain() }

}