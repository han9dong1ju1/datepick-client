package app.hdj.datepick.data.mapper

interface Mapper<Table, DomainModel> {

    fun Table.asDomain(): DomainModel

    fun DomainModel.asTable(): Table

}