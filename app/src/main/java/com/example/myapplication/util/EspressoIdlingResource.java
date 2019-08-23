package com.example.myapplication.util;

import android.support.test.espresso.IdlingResource;

/**
 * Contains a static reference IdlingResource, and should be available only in a mock build type.
 */
public final class EspressoIdlingResource {

    private static final String RESOURCE = "GLOBAL";

    private static SimpleCountingIdlingResource mCountingIdlingResource =
            new SimpleCountingIdlingResource(RESOURCE);

    public static void increment() {
        mCountingIdlingResource.increment();
    }

    public static void decrement() {
        mCountingIdlingResource.decrement();
    }

    public static IdlingResource getIdlingResource() {
        return mCountingIdlingResource;
    }

    private EspressoIdlingResource() {
    }
}
