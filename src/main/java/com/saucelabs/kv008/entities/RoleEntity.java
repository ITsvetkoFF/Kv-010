package com.softserve.edu.db.entities;

/**
 * __tablename__ = 'roles'
 * id = Column(Integer, primary_key=True)
 * name = Column(String(100), nullable=False, unique=True)
 */
public class RoleEntity {
    private int id;
    private String name;
}
