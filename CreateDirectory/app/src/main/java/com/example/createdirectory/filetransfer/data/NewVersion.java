package com.example.createdirectory.filetransfer.data;

public class NewVersion {
    private int id;
    private String versionName;
    private boolean isDownLoaded;
    private boolean isSaved;
    private boolean isMoved;

    public NewVersion(int id, String versionName, boolean isDownLoaded, boolean isSaved, boolean isMoved) {
        this.id = id;
        this.versionName = versionName;
        this.isDownLoaded = isDownLoaded;
        this.isSaved = isSaved;
        this.isMoved = isMoved;
    }
    public NewVersion (int id, String versionName) {
        this.id = id;
        this.versionName = versionName;
    }

    public NewVersion() {
    }

    public int getId() {
        return id;
    }

    public String getVersionName() {
        return versionName;
    }

    public boolean isDownLoaded() {
        return isDownLoaded;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public void setDownLoaded(boolean downLoaded) {
        isDownLoaded = downLoaded;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }
}
