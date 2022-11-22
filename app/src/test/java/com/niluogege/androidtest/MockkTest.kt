package com.niluogege.androidtest

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
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
        //这里的配置只会作用到注解创建的类上
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
     * 验证通过 spyk 方法 来创建对象
     * 值得注意的是： spyk 是真的创建一个对象，而 mockk 只是mock，mock出来的方法什么的都不是new出来的 返回值
     */
    @Test
    fun testSpyk(){
        //通过 spyk 创建的 类 返回原始的返回值
        val mather = spyk<Mother>()
        println(mather.giveMoney())

        //通过 mockk 创建的 类 需要mock方法的返回
        val motherMockk = mockk<Mother>()
        every { motherMockk.giveMoney() } returns 30
        println(motherMockk.giveMoney())
    }

    /**
     * verify 用例：
     * 用于校验某个方法是否被正常调用
     */
    @Test
    fun verifyDemo() {
        // When
        //mock mother
        val mother = mockk<Mother>(relaxed = true)
        //通过mock来的 mother 创建 kid
        val kid = Kid(mother)
        //当调用 mock的mother的 giveMoney 返回30
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

    /**
     * capture 用例：
     * 用于获取 参数数据
     */
    @Test
    fun captureDemo() {
        // When
        val mother = mockk<Mother>(relaxed = true)
        val kid = Kid(mother)

        //设置监听
        every { mother.giveMoney() } returns 30

        //调用一次会将 money 加到 30
        kid.wantMoney()

        println("money=${kid.money}")

        // Given
        val slot = slot<Int>()
        every { mother.inform(capture(slot)) } just Runs

        //触发，会给 slot赋值 ，赋值的时候 money为30
        kid.wantMoney()

        // Then
        assertEquals(30, slot.captured)
    }

}