package com.saucelabs.kv008.entities;


import org.postgis.Point;


/**
 * __tablename__ = 'problems'
 *
 * id = Column(Integer, primary_key=True)
 * title = Column(String(200), nullable=False)
 * content = Column(Text)
 * proposal = Column(Text)
 * severity = Column(Enum(*SEVERITY_TYPES, name='severitytypes'))
 * location = Column(Geography, nullable=False)
 * status = Column(Enum(*STATUSES, name='status'))
 * problem_type_id = Column(Integer, ForeignKey('problem_types.id'), nullable=False)
 * region_id = Column(Integer, ForeignKey('regions.id'))
 *
 * problem_type = relationship('ProblemType')
 * region = relationship('Region')
 */
public class ProblemEntity {


    private int id;
    private String title;
    private String content;
    private String proposal;
    private SeverityTypes severity;
    private Point location;
    private Status status;
    private int problem_type_id;
    private int regionId;

    private ProblemTypeEntity problem_type;
    private RegionEntity region;

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

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public ProblemTypeEntity getProblem_type() {
        return problem_type;
    }

    public void setProblem_type(ProblemTypeEntity problem_type) {
        this.problem_type = problem_type;
    }

    public RegionEntity getRegion() {
        return region;
    }

    public void setRegion(RegionEntity region) {
        this.region = region;
    }
}
