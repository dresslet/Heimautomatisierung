package de.piobyte.dressler.heimautomatisierung.model;


import java.util.List;

public class hADevice {
    private int id;
    private String name;
    private String friendlyName;
    private boolean status;
    private int icon;
    //private List<String> attributes;


    public hADevice(String name, int icon) {
        this.name = name;
        this.icon = icon;
        this.status = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
