package app.hdj.datepick.data.mapper

interface Mapper<Table, DataModel, DomainModel> {

    fun Table.tableToDomain(): DomainModel
    fun DataModel.dataToDomain(): DomainModel

    fun DomainModel.domainToTable(): Table
    fun DataModel.dataToTable(): Table

    fun List<DataModel>.mapDomain() = map { it.dataToDomain() }
    fun List<DataModel>.mapTable() = map { it.dataToTable() }

}