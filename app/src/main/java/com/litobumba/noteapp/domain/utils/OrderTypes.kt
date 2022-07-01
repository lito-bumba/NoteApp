package com.litobumba.noteapp.domain.utils

sealed class OrderTypes {
    object Ascending: OrderTypes()
    object Descending: OrderTypes()
}

sealed class NoteOrder(val orderTypes: OrderTypes) {
    class Title(orderTypes: OrderTypes): NoteOrder(orderTypes)
    class Date(orderTypes: OrderTypes): NoteOrder(orderTypes)
}