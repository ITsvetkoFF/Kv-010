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

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
