package me.bread.order.framework

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

object InMemoryDatabase {
    val dataStores = ConcurrentHashMap<String, MutableMap<Any, Any>>()
    val idCounters = ConcurrentHashMap<String, AtomicLong>()

    inline fun <reified ID : Any, reified T : Any> repository(): InMemoryCrudRepository<ID, T> {
        val className = T::class.java.simpleName

        return object : InMemoryCrudRepository<ID, T> {
            override fun save(entity: T): ID {
                val store = dataStores.getOrPut(className) { mutableMapOf<Any, Any>() }
                val idCounter = idCounters.getOrPut(className) { AtomicLong(0) }

                val id: ID = idCounter.incrementAndGet() as ID
                store[id] = entity

                return id
            }

            override fun findById(id: ID): T? {
                val store = dataStores[className]
                return store?.get(id) as? T
            }

            override fun findAll(): List<T> {
                val store = dataStores[className]
                return store?.values?.map { it as T } ?: emptyList()
            }

            override fun update(id: ID, entity: T): Boolean {
                val store = dataStores[className] ?: return false
                if (!store.containsKey(id)) return false

                store[id] = entity
                return true
            }

            override fun delete(id: ID): Boolean {
                val store = dataStores[className] ?: return false
                return store.remove(id) != null
            }
        }
    }
}
