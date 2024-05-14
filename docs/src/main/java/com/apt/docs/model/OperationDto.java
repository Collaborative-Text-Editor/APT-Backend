package com.apt.docs.model;

public class OperationDto {
    private int documentId;
    private String index;
    private char newContent;
    private boolean isBold;
    private boolean isItalic;
    private int to;

    
    
    public int getDocumentId() {
        return documentId;
    }
    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }
    public String getIndex() {
        return index;
    }
    public void setIndex(String index) {
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
    public int getTo() {
        return to;
    }
    public void setTo(int to) {
        this.to = to;
    }

    
    
}
