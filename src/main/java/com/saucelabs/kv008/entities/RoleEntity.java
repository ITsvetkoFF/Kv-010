package com.saucelabs.kv008.entities;

/**
 * __tablename__ = 'roles'
 * id = Column(Integer, primary_key=True)
 * name = Column(String(100), nullable=False, unique=True)
 */
public class RoleEntity {
    private int id;
    private String name;

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
}
