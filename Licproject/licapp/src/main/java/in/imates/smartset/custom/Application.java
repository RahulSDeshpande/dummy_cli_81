package in.imates.smartset.custom;

/**
 * Created by root on 5/1/16.
 */
public final class Application extends android.app.Application {
    @Override
    public void onCreate() {

        super.onCreate();

        TypefaceOverride.setDefaultFont(this, "DEFAULT", "MyFontAsset.ttf");
        TypefaceOverride.setDefaultFont(this, "MONOSPACE", "fonts/Dosis-SemiBold.ttf");
        TypefaceOverride.setDefaultFont(this, "SERIF", "fonts/Pacifico.ttf");
        TypefaceOverride.setDefaultFont(this, "SANS_SERIF", "MyFontAsset4.ttf");
    }
}