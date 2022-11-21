//package com.niluogege.androidtest
//
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.kotlin.doReturn
//import org.powermock.api.mockito.PowerMockito
//import org.powermock.modules.junit4.PowerMockRunner
//
///**
// * Powermock 不能 mock final 方法 ，然而kotlin方法都是 final的
// *
// * 但可以mock 静态和私有方法，
// */
//@RunWith(PowerMockRunner::class)
//class PowermockTest {
//
//    @Test
//    fun testMockPriviceMethod() {
//
//        val mock = PowerMockito.mock(Bean::class.java)
//        PowerMockito.`when`(mock.getAAA()).doReturn("mockAAA")
//        println("getAAA=${mock.getAAA()}")
//    }
//}