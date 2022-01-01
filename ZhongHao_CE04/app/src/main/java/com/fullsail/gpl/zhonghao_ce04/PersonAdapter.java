// Hao Zhong
// GPL - 202110
// PersonAdapter.java
package com.fullsail.gpl.zhonghao_ce04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PersonAdapter extends BaseAdapter {

    private static final long BASE_ID = 0x1011;
    private final Context mContext;
    private final ArrayList<Person> mPeople;
    private final boolean mLayoutType;

    public PersonAdapter(Context _context, ArrayList<Person> _persons, boolean _gridLayout) {
        mContext = _context;
        mPeople = _persons;
        mLayoutType = _gridLayout;
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
        Person someOne = (Person) getItem(i);

        if (view == null) {
            if (mLayoutType) {
                view = LayoutInflater.from(mContext).inflate(R.layout.gridview, viewGroup, false);
                vh = new ViewHolder(view, true);
            } else {
                view = LayoutInflater.from(mContext).inflate(R.layout.listview, viewGroup, false);
                vh = new ViewHolder(view, false);
            }

            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        if (someOne != null) {
            vh.tv_name.setText(someOne.getName());
            vh.tv_birthday.setText(someOne.getBirthday());
            vh.iv_profile.setImageResource(someOne.getImageById());
        }

        return view;
    }

    static class ViewHolder {
        TextView tv_name;
        TextView tv_birthday;
        ImageView iv_profile;

        public ViewHolder(View _layout, boolean _gridLayout) {
            if (_gridLayout) {
                tv_name = _layout.findViewById(R.id.gridview_name);
                tv_birthday = _layout.findViewById(R.id.gridview_birthday);
                iv_profile = _layout.findViewById(R.id.gridview_image);
            } else {
                tv_name = _layout.findViewById(R.id.listview_name);
                tv_birthday = _layout.findViewById(R.id.listview_birthday);
                iv_profile = _layout.findViewById(R.id.listview_image);
            }
        }
    }
}
