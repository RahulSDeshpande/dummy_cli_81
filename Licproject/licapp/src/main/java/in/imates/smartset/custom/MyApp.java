package in.imates.smartset.custom;

import com.drivemode.android.typeface.TypefaceHelper;

/**
 * Created by root on 5/1/16.
 */
public class MyApp extends android.app.Application {
    @Override
    public void onCreate() {

        super.onCreate();

        TypefaceHelper.initialize(this);
    }

    @Override
    public void onTerminate() {

        TypefaceHelper.destroy();

        super.onTerminate();
    }
}