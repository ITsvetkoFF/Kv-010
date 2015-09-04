package com.saucelabs.kv008.entities;

/**
 * __tablename__ = 'permissions'
 *
 * id = Column(Integer, primary_key=True, autoincrement=True)
 * res_name = Column(String(100), ForeignKey('resources.name'), nullable=False)
 * action = Column(Enum(*ACTIONS, name='actions'), nullable=False)
 * modifier = Column(Enum(*MODIFIERS, name='modifiers'))
 *
 * res = orm.relationship('Resource')
 */
public class PermissionEntity {

    private int id;
    private String res_name;
    private Actions action;
    private Modifiers modifier;

    ResourceEntity res;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRes_name() {
        return res_name;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    public Actions getAction() {
        return action;
    }

    public void setAction(Actions action) {
        this.action = action;
    }

    public Modifiers getModifier() {
        return modifier;
    }

    public void setModifier(Modifiers modifier) {
        this.modifier = modifier;
    }

    public ResourceEntity getRes() {
        return res;
    }

    public void setRes(ResourceEntity res) {
        this.res = res;
    }
}
