package com.saucelabs.kv008.entities;

import org.postgis.PGgeometry;

/**
 * __tablename__ = 'regions'
 *
 * id = Column(Integer, primary_key=True)
 * name = Column(String(100), nullable=False)
 * location = Column(Geometry, nullable=False)
 */
public class RegionEntity {
    private int id;
    private String name;
    private PGgeometry location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PGgeometry getLocation() {
        return location;
    }

    public void setLocation(PGgeometry location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "RegionEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location=" + location +
                '}';
    }
}
