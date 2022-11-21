package com.niluogege.androidtest

import io.mockk.every
import io.mockk.mockk
import org.junit.Test

/**
 * 可以mock final 方法
 */
class MockkTest {


    @Test
    fun testFinalMethod() {
        val mock = mockk<Bean>()
        every { mock.getAAA() } returns "可以正常mock final方法"
        println("mock 后 getAAA=${mock.getAAA()}")
    }
}