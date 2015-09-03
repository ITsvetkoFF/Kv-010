package com.saucelabs.kv008.entities;

import java.sql.Timestamp;

/**
 * __tablename__ = 'photos'
 *
 * id = Column(Integer, primary_key=True, autoincrement=True)
 * name = Column(String(200), nullable=False)
 * datetime = Column(DateTime)
 * comment = Column(Text)
 * problem_id = Column(Integer, ForeignKey('problems.id'), nullable=False)
 * user_id = Column(Integer, ForeignKey('users.id'))
 */
public class PhotoEntity {
    private int id;
    private String name;
    private Timestamp datetime;
    private String comment;
    private int problemId;
    private int userId;
}
