package com.saucelabs.kv008.entities;

/**
 * __tablename__ = 'resources'
 * <p>
 * name = Column(String(100), primary_key=True)
 */
public class ResourceEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
