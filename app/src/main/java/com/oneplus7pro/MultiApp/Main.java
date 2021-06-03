package com.oneplus7pro.MultiApp;

import android.os.Handler;
import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Main implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (loadPackageParam.packageName.equals("com.android.settings")){
            XposedHelpers.findAndHookMethod(loadPackageParam.classLoader.loadClass("com.oneplus.settings.apploader.OPApplicationLoader"), "initData",int.class, Handler.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    param.args[0]=0;
                    super.beforeHookedMethod(param);
                }
            });
        }
    }
}
