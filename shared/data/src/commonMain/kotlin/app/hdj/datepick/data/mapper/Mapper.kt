package app.hdj.datepick.data.mapper

interface Mapper<Table, DomainModel> {

    fun map(table: Table): DomainModel

    fun map(model: DomainModel): Table

}