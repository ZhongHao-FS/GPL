// Hao Zhong
// GPL - 202110
// GridAdapter.java
package com.fullsail.gpl.zhonghao_ce09;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    private static final long BASE_ID = 0x1002;
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        Book book = (Book) getItem(i);

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.book_cell, viewGroup, false);
            vh = new ViewHolder(view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        if (book != null) {
            vh.tv_title.setText(book.getTitle());
            Picasso.get().load(book.getImageLink()).placeholder(R.drawable.ic_launcher_foreground).fit().centerInside().into(vh.iv_cover);
        }

        return view;
    }

    static class ViewHolder {
        TextView tv_title;
        ImageView iv_cover;

        public ViewHolder(View _layout) {
            tv_title = _layout.findViewById(R.id.cell_textView);
            iv_cover = _layout.findViewById(R.id.cell_imageView);
        }
    }
}
