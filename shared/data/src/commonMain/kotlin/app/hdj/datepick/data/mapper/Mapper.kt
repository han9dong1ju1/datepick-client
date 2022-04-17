package app.hdj.datepick.data.mapper

interface Mapper<DataModel, DomainModel> {

    fun DataModel.asDomain(): DomainModel

    fun List<DataModel>.mapDomain() = map { it.asDomain() }

}