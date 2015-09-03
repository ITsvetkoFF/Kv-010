package com.softserve.edu.db.entities;

import java.sql.Timestamp;

/**
 * __tablename__ = 'comments'
 *
 * id = Column(Integer, primary_key=True)
 * content = Column(Text, nullable=False)
 * problem_id = Column(Integer, ForeignKey('problems.id', ondelete='CASCADE'), nullable=False)
 * user_id = Column(Integer, ForeignKey(u'users.id'))
 * created_date = Column(DateTime, nullable=False)
 * modified_date = Column(DateTime, nullable=True)
 * modified_user_id = Column(Integer, nullable=True)
 *
 * problem = relationship('Problem')
 * user = relationship('User')
 */
public class CommentEntity {
    private int id;
    private String content;
    private int problem_id;
    private int user_id;
    private Timestamp created_date;
    private Timestamp modified_date;
    private int modified_user_id;

//    ProblemEntity problem;
//    UserEntity user;

}
