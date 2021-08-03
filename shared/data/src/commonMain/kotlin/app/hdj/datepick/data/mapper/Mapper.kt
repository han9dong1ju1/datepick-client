package app.hdj.datepick.data.mapper

interface Mapper<Table, Response> {

    fun map(table: Table): Response

    fun map(response: Response): Table

}