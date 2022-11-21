package com.niluogege.androidtest

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MockitoTest {

    @Mock
    lateinit var bean: Bean

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun mockBean() {

        val mock = mock(Bean::class.java)


        println("方法 aaa = ${mock.aaa} bbb=${mock.bbb} ccc=${mock.ccc}")
        println("注解mock aaa = ${bean.aaa} bbb=${bean.bbb} ccc=${bean.ccc}")
    }

}