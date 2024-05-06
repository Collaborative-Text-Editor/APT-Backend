package com.apt.docs.model;

public class DocumentUpdate {
    private int id;
    private int index;
    private byte[] newContent;
    private String operation;
    private int length;

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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}