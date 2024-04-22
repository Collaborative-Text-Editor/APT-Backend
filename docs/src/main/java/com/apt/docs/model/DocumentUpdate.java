package com.apt.docs.model;

public class DocumentUpdate {
    private int id;
    private int index;
    private byte[] newContent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public byte[] getNewContent() {
        return newContent;
    }

    public void setNewContent(byte[] newContent) {
        this.newContent = newContent;
    }
}