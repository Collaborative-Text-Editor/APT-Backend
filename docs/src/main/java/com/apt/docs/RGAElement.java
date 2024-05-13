package com.apt.docs;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RGAElement {
    private String id;
    private char value;
    private boolean isDeleted;
    private boolean isBold;
    private boolean isItalic;

    @JsonCreator
    public RGAElement(@JsonProperty("id") String id, @JsonProperty("value") char value, @JsonProperty("bold") boolean isBold, @JsonProperty("italic") boolean isItalic, @JsonProperty("deleted") boolean isDeleted) {
        this.id = id;
        this.value = value;
        this.isDeleted = isDeleted;
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