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
}
