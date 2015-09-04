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

    private ProblemEntity problem;
    private UserEntity user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProblem_id() {
        return problem_id;
    }

    public void setProblem_id(int problem_id) {
        this.problem_id = problem_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public ActivityTypes getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(ActivityTypes activity_type) {
        this.activity_type = activity_type;
    }

    public ProblemEntity getProblem() {
        return problem;
    }

    public void setProblem(ProblemEntity problem) {
        this.problem = problem;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
