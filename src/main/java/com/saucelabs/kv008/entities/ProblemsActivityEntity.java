package com.saucelabs.kv008.entities;

import org.postgresql.util.PGobject;

import java.sql.Timestamp;

/**
 * __tablename__ = 'problems_activities'

 id = Column(Integer, primary_key=True)
 problem_id = Column(Integer, ForeignKey('problems.id', ondelete='CASCADE'), nullable=False)
 user_id = Column(Integer, ForeignKey('users.id'))
 datetime = Column(DateTime, nullable=False)
 activity_type = Column(Enum(*ACTIVITY_TYPES, name='activitytype'), nullable=False)

 problem = relationship('Problem', backref='problem_activities')
 user = relationship('User', backref='problem_activities')
 */
public class ProblemsActivityEntity {

    private int id;
    private int problem_id;
    private int user_id;
    private Timestamp datetime;
    private ActivityTypes activity_type;


//    private ProblemEntity problem;
//    private UserEntity user;
}
