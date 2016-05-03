package soda.apksharedemo;

import android.graphics.drawable.Drawable;

import java.io.File;

/**
 * Created by soda on 2016/5/3.
 */
public class AppModel {

    private String appName;
    private Drawable appIcon;
    private String appSize;
    private String lastUpdatetime;
    private File appFile;

    public AppModel(String appName, Drawable appIcon, String appSize, String lastUpdatetime, File path) {
        this.appName = appName;
        this.appIcon = appIcon;
        this.appSize = appSize;
        this.lastUpdatetime = lastUpdatetime;
        this.appFile = path;
    }


    public String getLastUpdatetime() {
        return lastUpdatetime;
    }

    public void setLastUpdatetime(String lastUpdatetime) {
        this.lastUpdatetime = lastUpdatetime;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppSize() {
        return appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    public File getAppFile() {
        return appFile;
    }

    public void setAppFile(File path) {
        this.appFile = path;
    }
}
