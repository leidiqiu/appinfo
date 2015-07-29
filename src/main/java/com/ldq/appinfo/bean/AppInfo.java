package com.ldq.appinfo.bean;

import android.graphics.drawable.Drawable;

public class AppInfo {

    private Drawable icon;
    private String name;
    private String packageName;
    private String signature;

    public AppInfo(Drawable icon, String name, String packageName,
            String signature) {
        this.icon = icon;
        this.name = name;
        this.packageName = packageName;
        this.signature = signature;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

}
