package com.example.order.framework

interface InMemoryCrudRepository<ID, T> {
    fun save(entity: T): ID

    fun findById(id: ID): T?

    fun findAll(): List<T>

    fun update(
        id: ID,
        entity: T,
    ): Boolean

    fun delete(id: ID): Boolean
}
