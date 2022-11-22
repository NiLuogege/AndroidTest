package com.niluogege.androidtest

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * 可以mock final 方法
 */
class MockkTest {

    @MockK
    lateinit var beanAnnotation: Bean

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true, relaxUnitFun = true)
    }

    /**
     * 验证 注解方式 构建 mock对象
     */
    @Test
    fun testAnnotationMethod() {
        every { beanAnnotation.getBBB() } returns 100
        println("mock 后 getBBB=${beanAnnotation.getBBB()}")
    }


    /**
     * 验证 mockk() 构建mock对象
     */
    @Test
    fun testFinalMethod() {
        val mock = mockk<Bean>()
        every { mock.getAAA() } returns "可以正常mock final方法"
        println("mock 后 getAAA=${mock.getAAA()}")
    }


    /**
     * verify 用例：
     * 用于校验某个方法是否被正常调用
     */
    @Test
    fun verifyDemo() {
        // When
        val mother = mockk<Mother>(relaxed = true)
        val kid = Kid(mother)
        every { mother.giveMoney() } returns 30

        // Given
        kid.wantMoney()

        // Then
        //校验 inform 有没有在 wantMoney 中正常调用
        verify { mother.inform(any()) }
        assertEquals(30, kid.money)


        //校验 inform 的下一个方法一定是 giveMoney
        verifySequence {
            mother.inform(any())
            mother.giveMoney()
        }

        //校验 giveMoney 会在 inform 之后执行 ，不一定是 下一个
        verifyOrder {
            mother.inform(any())
            mother.giveMoney()
        }
    }


}