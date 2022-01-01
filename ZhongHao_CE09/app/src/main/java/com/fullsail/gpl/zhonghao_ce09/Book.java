// Hao Zhong
// GPL - 202110
// Book.java
package com.fullsail.gpl.zhonghao_ce09;

public class Book {
    private final String title;
    private final String imageLink;

    public Book(String _title, String _image) {
        title = _title;
        imageLink = _image;
    }

    public String getTitle() {
        return title;
    }

    public String getImageLink() {
        return imageLink;
    }
}
