package com.example.moviereviweapp.ui.main
import android.os.SystemClock
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.moviereviweapp.R
import org.junit.After
import org.junit.Before
import org.junit.Test
open class MainActivityTest {
    private lateinit var scenario: ActivityScenario<MainActivity>


    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.moveToState(Lifecycle.State.STARTED)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun recyclerViewTest() {
        SystemClock.sleep(5000)
        onView(withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    4,
                    click()
                )

            )


    }
    @After
    fun tearDown() {

    }


}