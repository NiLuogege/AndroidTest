//package com.niluogege.androidtest
//
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.junit.runners.JUnit4
//import org.mockito.Mock
//
//import org.mockito.Mockito.*
//import org.mockito.MockitoAnnotations
//import org.mockito.kotlin.whenever
//
///**
// * Mockito 不能 mock  final,静态和私有方法，
// */
//@RunWith(JUnit4::class)
//class MockitoTest {
//
//    @Mock
//    lateinit var beanAnnotation: Bean
//
//    @Before
//    fun setup() {
//        MockitoAnnotations.openMocks(this)
//    }
//
//
//    @Test
//    fun mockBean() {
//        val mock: Bean = mock(Bean::class.java)
//        mock.aaa = "aaa"
//        println("方法 aaa = ${mock.aaa} bbb=${mock.bbb} ccc=${mock.ccc}  getBBB=${mock.getBBB()}")
//    }
//
//
//    @Test
//    fun mockByAnnotation() {
//        whenever(beanAnnotation.getBBB()).thenReturn(72)
//        println("注解mock aaa = ${beanAnnotation.aaa} bbb=${beanAnnotation.bbb} ccc=${beanAnnotation.ccc} getBBB=${beanAnnotation.getBBB()}")
//    }
//
//}