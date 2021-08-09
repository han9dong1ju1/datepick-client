package app.hdj.datepick.data.mapper

interface Mapper<Table, DomainModel> {

    fun Table.asDomain(): DomainModel

    fun DomainModel.asTable(): Table

    fun List<Table>.mapDomain() = map { it.asDomain() }
    fun List<DomainModel>.mapTable() = map { it.asTable() }

}