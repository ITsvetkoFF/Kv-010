package com.saucelabs.kv008.entities;

/**
 * __tablename__ = 'problem_types'
 *
 * id = Column(Integer, primary_key=True)
 * type = Column(String(100), nullable=False)
 */
public class ProblemTypeEntity {

    private int id;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
