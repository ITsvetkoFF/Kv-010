package com.saucelabs.kv008.entities;

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

    ProblemEntity problem;
    UserEntity user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Timestamp getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Timestamp created_date) {
        this.created_date = created_date;
    }

    public Timestamp getModified_date() {
        return modified_date;
    }

    public void setModified_date(Timestamp modified_date) {
        this.modified_date = modified_date;
    }

    public int getModified_user_id() {
        return modified_user_id;
    }

    public void setModified_user_id(int modified_user_id) {
        this.modified_user_id = modified_user_id;
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
