package app.hdj.datepick.data.mapper

interface Mapper<Table, Response, Model> {

    fun map(table: Table): Response

    fun map(response: Response): Table

    fun transfer(response: Response): Model

    fun transfer(table: Table): Model

}