package com.ldq.appinfo.activity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.ldq.appinfo.R;
import com.ldq.appinfo.adapter.AppListAdapter;
import com.ldq.appinfo.bean.AppInfo;
import com.ldq.appinfo.consts.ConstKey;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class AppListActivity extends ActionBarActivity implements
        OnItemClickListener {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);

        mListView = (ListView) findViewById(R.id.listview);
        List<AppInfo> list = getAppListInfo();
        AppListAdapter appListAdapter = new AppListAdapter(
                getApplicationContext(), list);
        mListView.setAdapter(appListAdapter);

        mListView.setOnItemClickListener(this);
    }

    private List<AppInfo> getAppListInfo() {
        PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> list = packageManager
                .getInstalledApplications(PackageManager.SIGNATURE_MATCH);
        ArrayList<AppInfo> appInfos = new ArrayList<AppInfo>();
        for (ApplicationInfo applicationInfo : list) {
            try {
                Drawable icon = packageManager
                        .getApplicationIcon(applicationInfo.packageName);
                String label = (String) packageManager
                        .getApplicationLabel(applicationInfo);
                String signature = getSignature(applicationInfo.packageName);
                AppInfo appInfo = new AppInfo(icon, label,
                        applicationInfo.packageName, signature);
                appInfos.add(appInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return appInfos;
    }

    private String getSignature(String packageName) throws Exception {
        PackageManager packageManager = getPackageManager();
        Signature[] signatures = packageManager.getPackageInfo(packageName,
                PackageManager.GET_SIGNATURES).signatures;
        String signature = getMD5(signatures[0].toByteArray());
        return signature;
    }

    private String getMD5(byte[] b) {
        String HEX = "0123456789ABCDEF";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bb = md.digest(b);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < bb.length; i++) {
                sb.append(HEX.charAt((bb[i] >>> 4) & 0x0F));
                sb.append(HEX.charAt(bb[i] & 0x0F));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Intent intent = new Intent(AppListActivity.this,
                AppDetailActivity.class);
        AppListAdapter adapter = (AppListAdapter) parent.getAdapter();
        AppInfo appInfo = (AppInfo) adapter.getItem(position);
        String packageName = appInfo.getPackageName();
        intent.putExtra(ConstKey.KEY_PACKAGE_NAME, packageName);
        startActivity(intent);
    }

}
