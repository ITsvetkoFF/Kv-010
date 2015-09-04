package com.saucelabs.kv008.entities;


import org.postgis.Point;

import java.sql.Timestamp;

/**
 *__tablename__ = 'detailed_problem'
 *
 * id = Column(Integer, primary_key=True)
 * title = Column(String(200), nullable=False)
 * content = Column(Text)
 * proposal = Column(Text)
 * severity = Column(Enum(*SEVERITY_TYPES, name='severitytypes'))
 * location = Column(Geography, nullable=False)
 * status = Column(Enum(*STATUSES, name='status'))
 * problem_type_id = Column(Integer, ForeignKey('problem_types.id'))
 * region_id = Column(Integer, ForeignKey('regions.id'))
 * users_id = Column(Integer, ForeignKey('users.id'))
 * first_name = Column(String(100), nullable=False)
 * last_name = Column(String(100), nullable=False)
 * datetime = Column(DateTime, nullable=False)
 * number_of_votes = Column(Integer)
 * number_of_comments = Column(Integer)
 */
public class DetailedProblemEntity {

    private int id;
    private String title;
    private String content;
    private String proposal;
    private SeverityTypes severity;
    private Point location;
    private Status status;
    private int problem_type_id;
    private int region_id;
    private int users_id;
    private String first_name;
    private String last_name;
    private Timestamp datetime;
    private int number_of_votes;
    private int number_of_comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProposal() {
        return proposal;
    }

    public void setProposal(String proposal) {
        this.proposal = proposal;
    }

    public SeverityTypes getSeverity() {
        return severity;
    }

    public void setSeverity(SeverityTypes severity) {
        this.severity = severity;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getProblem_type_id() {
        return problem_type_id;
    }

    public void setProblem_type_id(int problem_type_id) {
        this.problem_type_id = problem_type_id;
    }

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public int getNumber_of_votes() {
        return number_of_votes;
    }

    public void setNumber_of_votes(int number_of_votes) {
        this.number_of_votes = number_of_votes;
    }

    public int getNumber_of_comments() {
        return number_of_comments;
    }

    public void setNumber_of_comments(int number_of_comments) {
        this.number_of_comments = number_of_comments;
    }
}
