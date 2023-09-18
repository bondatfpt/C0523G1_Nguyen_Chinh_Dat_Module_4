package com.codegym.model;

import java.util.Arrays;

public class SettingEmailBox {
    private String languages;
    private int  pages;
    private boolean spamsFilter;
    private String signature;

    public SettingEmailBox() {
    }

    public SettingEmailBox(String languages, int pages, boolean spamsFilter, String signature) {
        this.languages = languages;
        this.pages = pages;
        this.spamsFilter = spamsFilter;
        this.signature = signature;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean isSpamsFilter() {
        return spamsFilter;
    }

    public void setSpamsFilter(boolean spamsFilter) {
        this.spamsFilter = spamsFilter;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "SettingEmailBox{" +
                "languages=" +languages+
                ", pages=" + pages +
                ", spamsFilter=" + spamsFilter +
                ", signature='" + signature + '\'' +
                '}';
    }
}
