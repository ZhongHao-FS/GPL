// Hao Zhong
// GPL - 202110
// FullsailorAdapter.java
package com.fullsail.gpl.zhonghao_ce05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FullsailorAdapter extends BaseAdapter {

    private static final long BASE_ID = 0x1012;
    private final Context mContext;
    private final ArrayList<FullSailor> mPeople;

    // Constructor
    public FullsailorAdapter(Context _context, ArrayList<FullSailor> _people) {
        mContext = _context;
        mPeople = _people;
    }

    @Override
    public int getCount() {
        if (mPeople != null) {
            return mPeople.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if (mPeople != null && i >= 0 && i < mPeople.size()) {
            return mPeople.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return BASE_ID + i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        FullSailor sailor = (FullSailor) getItem(i);

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.listview, viewGroup, false);
            vh = new ViewHolder(view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        if (sailor != null) {
            vh.tv_name.setText(sailor.getName());
            vh.tv_birthday.setText(sailor.getBirthday());
        }

        return view;
    }

    // Static Inner Class
    static class ViewHolder {
        TextView tv_name;
        TextView tv_birthday;

        // Constructor
        public ViewHolder(View _layout) {
            tv_name = _layout.findViewById(R.id.textView_listName);
            tv_birthday = _layout.findViewById(R.id.textView_listDOB);
        }
    }
}
