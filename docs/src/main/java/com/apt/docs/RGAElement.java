package com.apt.docs;

public class RGAElement {
    private String id;
    private char value;
    private boolean isDeleted;
    private boolean isBold;
    private boolean isItalic;

    public RGAElement(String siteId, int clock, char value, boolean isBold, boolean isItalic) {
        this.id = siteId + ":" + clock;
        this.value = value;
        this.isDeleted = false;
        this.isBold = isBold;
        this.isItalic = isItalic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
    public boolean isBold() {
        return isBold;
    }

    public void setBold(boolean bold) {
        isBold = bold;
    }

    public boolean isItalic() {
        return isItalic;
    }

    public void setItalic(boolean italic) {
        isItalic = italic;
    }
}