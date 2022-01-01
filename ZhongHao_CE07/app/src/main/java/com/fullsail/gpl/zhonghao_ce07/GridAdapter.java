// Hao Zhong
// GPL - 202110
// GridAdapter.java
package com.fullsail.gpl.zhonghao_ce07;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    private static final long BASE_ID = 0x1001;
    private final Context mContext;
    private final ArrayList<Book> mBooks;

    public GridAdapter(@NonNull Context context, @NonNull ArrayList<Book> objects) {
        mContext = context;
        mBooks = objects;
    }

    @Override
    public int getCount() {
        return mBooks.size();
    }

    @Override
    public Object getItem(int i) {
        if (i >= 0 && i < mBooks.size()) {
            return mBooks.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return BASE_ID + i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder vh;
        Book book = (Book) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.book_cell, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        if (book != null) {
            vh.tv_title.setText(book.getTitle());
            Picasso.with(mContext).load(book.getImageLink()).placeholder(R.drawable.ic_launcher_foreground).fit().centerInside().into(vh.iv_cover);
        }

        return convertView;
    }

    static class ViewHolder {
        TextView tv_title;
        ImageView iv_cover;

        public ViewHolder(View _layout) {
            tv_title = _layout.findViewById(R.id.gridView_cell_textView);
            iv_cover = _layout.findViewById(R.id.gridView_cell_imageView);
        }
    }
}
