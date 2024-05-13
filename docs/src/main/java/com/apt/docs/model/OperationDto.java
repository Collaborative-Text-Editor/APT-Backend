package com.apt.docs.model;

public class OperationDto {
    private int documentId;
    private int index;
    private char newContent;
    private boolean isBold;
    private boolean isItalic;

    
    
    public int getDocumentId() {
        return documentId;
    }
    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public char getNewContent() {
        return newContent;
    }
    public void setNewContent(char newContent) {
        this.newContent = newContent;
    }
    public boolean isBold() {
        return isBold;
    }
    public void setBold(boolean isBold) {
        this.isBold = isBold;
    }
    public boolean isItalic() {
        return isItalic;
    }
    public void setItalic(boolean isItalic) {
        this.isItalic = isItalic;
    }

    
    
}
