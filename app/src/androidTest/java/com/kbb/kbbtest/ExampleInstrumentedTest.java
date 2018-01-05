package com.kbb.kbbtest;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.kbb.kbbtest.interfaces.BeizerEvaluater;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.kbb.kbbtest", appContext.getPackageName());
    }

    public void testAnimator(){
        BeizerEvaluater beizerEvaluater = new BeizerEvaluater();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(beizerEvaluater,"",0.2f,1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(3000);
        animatorSet.setInterpolator(new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return 0;
            }
        });
    }

}
