package com.whoisthat.pokemon.remote

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    suspend fun tes() {
        flow<Int> {
            emit(1)
            delay(1_000)
            throw IllegalAccessError("WKWKWK")
        }.catch {
            assertEquals(IllegalAccessError::class.java, it::class.java)
        }.collectLatest { assertEquals(1, it) }
//        a().catch {
//            assertEquals(IllegalAccessError::class.java, it::class.java)
//        }.collect {
//            assertEquals(1, it)
//        }
    }

    fun a() = flow<Int> {
        emit(1)
        delay(1_000)
        throw IllegalAccessError("WAAHAHA")
    }
}