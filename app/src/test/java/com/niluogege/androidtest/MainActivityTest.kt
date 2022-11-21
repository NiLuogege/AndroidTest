package com.niluogege.androidtest

import android.os.Build
import android.view.View
import android.widget.CheckBox
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.ActivityAction
import junit.framework.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowAlertDialog

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class MainActivityTest {


    @Test
    fun testLifecycle() {

        val controller = Robolectric.buildActivity(MainActivity::class.java)
        val activity = controller.get()
        assertEquals("INITIALIZED", activity.lifecycle.currentState.name)

        controller.create()
        assertEquals("CREATED", activity.lifecycle.currentState.name)

        controller.start()
        assertEquals("STARTED", activity.lifecycle.currentState.name)

        controller.resume()
        assertEquals("RESUMED", activity.lifecycle.currentState.name)
    }

    /**
     * 测试启动activity
     */
    @Test
    fun testJumpNextBtn() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                activity.findViewById<View>(R.id.btn_jump).performClick()
                val shadowActivity = Shadows.shadowOf(activity)
                val nexIntent = shadowActivity.nextStartedActivity
                assertEquals(nexIntent.component?.className, SecondActivity::class.java.name)
            }
        }
    }

    /**
     * checkBox状态切换
     */
    @Test
    fun testCheckBox() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                val cb = activity.findViewById<CheckBox>(R.id.cb)
                assertFalse(cb.isChecked)

                cb.performClick()
                assertTrue(cb.isChecked)

                cb.performClick()
                assertFalse(cb.isChecked)
            }
        }
    }


    /**
     * 测试dialog
     *
     * fixme 这个有问题不知道为什么 后面再搞
     */
    @Test
    fun testDialog() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->

                var alertDialog = ShadowAlertDialog.getLatestAlertDialog()
                // 判断Dialog尚未弹出
                assertNull(alertDialog)


                activity.findViewById<View>(R.id.btn_show_alert).performClick()
                alertDialog = ShadowAlertDialog.getLatestAlertDialog()
                assertNotNull(alertDialog)

                val shadowAlertDialog = shadowOf(alertDialog)
                assertEquals("title", shadowAlertDialog.title)
                assertEquals("message", shadowAlertDialog.message)
            }
        }
    }
}