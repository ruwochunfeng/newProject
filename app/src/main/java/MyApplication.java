import android.app.Application;

import io.vov.vitamio.Vitamio;

/**
 * Created by dell on 2017/6/30.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Vitamio.isInitialized(getApplicationContext());
    }
}
