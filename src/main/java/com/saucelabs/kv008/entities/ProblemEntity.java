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

//    private ProblemTypeEntity problem_type;
//    private RegionEntity region;

}
