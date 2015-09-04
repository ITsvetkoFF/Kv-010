package com.saucelabs.kv008.entities;

import com.saucelabs.kv008.dao.ProblemDao;
import com.saucelabs.kv008.dao.UserDao;

/**
 *  __tablename__ = 'solutions'

 id = sa.Column(sa.Integer, primary_key=True, autoincrement=True)
 problem_id = sa.Column(sa.Integer, sa.ForeignKey('problems.id', ondelete='CASCADE'), nullable=False)
 administrator_id = sa.Column(sa.Integer, sa.ForeignKey('users.id'), nullable=False)
 responsible_id = sa.Column(sa.Integer, sa.ForeignKey('users.id'), nullable=False)

 problem = sa.orm.relationship('Problem')
 administrator = sa.orm.relationship('User', foreign_keys=[administrator_id])
 responsible = sa.orm.relationship('User', foreign_keys=[responsible_id])
 */
public class SolutionEntity {

    private int id;
    private int problem_id;
    private int administrator_id;
    private int responsible_id;

    private ProblemEntity problem;
    private UserEntity administrator;
    private UserEntity responsible;

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

    public int getAdministrator_id() {
        return administrator_id;
    }

    public void setAdministrator_id(int administrator_id) {
        this.administrator_id = administrator_id;
    }

    public int getResponsible_id() {
        return responsible_id;
    }

    public void setResponsible_id(int responsible_id) {
        this.responsible_id = responsible_id;
    }

    public ProblemEntity getProblem() {
        if(this.problem == null && this.problem_id !=0){
            problem=  new ProblemDao().readByKey(this.problem_id);
        }
        return problem;
    }

    public void setProblem(ProblemEntity problem) {
        this.problem = problem;
        this.problem_id = problem.getId();
    }

    public UserEntity getAdministrator() {
        if(this.administrator == null && this.administrator_id !=0){
            administrator=  new UserDao().readByKey(this.administrator_id);
        }
        return responsible;
    }

    public void setAdministrator(UserEntity administrator) {
        this.administrator = administrator;
        this.administrator_id = administrator.getId();
    }

    public UserEntity getResponsible() {
        if(this.responsible == null && this.responsible_id !=0){
            responsible=  new UserDao().readByKey(this.responsible_id);
        }
        return responsible;
    }

    public void setResponsible(UserEntity responsible) {
        this.responsible = responsible;
        this.responsible_id = responsible.getId();
    }
}
