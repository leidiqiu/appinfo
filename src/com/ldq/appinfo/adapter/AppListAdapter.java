package com.ldq.appinfo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ldq.appinfo.R;
import com.ldq.appinfo.bean.AppInfo;

public class AppListAdapter extends BaseAdapter {

    private Context mContext;
    private List<AppInfo> mList;

    public AppListAdapter(Context context, List<AppInfo> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.appinfo_list_item, parent, false);
            holder = new Holder();
            holder.imageView = (ImageView) convertView
                    .findViewById(R.id.appinfo_image);
            holder.textViewName = (TextView) convertView
                    .findViewById(R.id.appinfo_name);
            holder.textViewSignature = (TextView) convertView
                    .findViewById(R.id.appinfo_signature);
            convertView.setTag(holder);
        }

        bindView(convertView, position);

        return convertView;
    }

    private void bindView(View view, int position) {
        Holder holder = (Holder) view.getTag();
        AppInfo appInfo = (AppInfo) getItem(position);
        holder.imageView.setImageDrawable(appInfo.getIcon());
        holder.textViewName.setText(appInfo.getName());
        holder.textViewSignature.setText("签名:" + appInfo.getSignature());
    }

    private class Holder {
        ImageView imageView;
        TextView textViewName;
        TextView textViewSignature;
    }
}
