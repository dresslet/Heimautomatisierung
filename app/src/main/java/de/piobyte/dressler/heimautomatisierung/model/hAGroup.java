package de.piobyte.dressler.heimautomatisierung.model;

public class hAGroup {
    private int id;
    private String name;
    private int image;
    private String nameZ;

    public hAGroup(String name, int image, String nameZ) {
        this.name = name;
        this.image = image;
        this.nameZ = nameZ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNameZ() { return nameZ; }

    public void setNameZ(String nameZ) { this.nameZ = nameZ; }
}
