package com.apt.docs;

public class Operation {
    private String type;
    private String id;
    private char value;
    private boolean isBold;
    private boolean isItalic;

    public Operation(String type, String id, char value, boolean isBold, boolean isItalic) {
        this.type = type;
        this.id = id;
        this.value = value;
        this.isBold = isBold;
        this.isItalic = isItalic;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
