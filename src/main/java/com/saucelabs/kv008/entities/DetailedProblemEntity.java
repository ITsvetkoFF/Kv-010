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


}
