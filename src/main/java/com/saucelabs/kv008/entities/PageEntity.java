package com.softserve.edu.db.entities;

/**
 * class Page(Base):
 * __tablename__ = 'pages'
 *
 * id = Column(Integer, primary_key=True, autoincrement=True)
 * alias = Column(String(30), nullable=False, unique=True)
 * title = Column(String(150), nullable=False)
 * content = Column(Text, nullable=False)
 * is_resource = Column(Boolean, nullable=False)
 */
public class PageEntity {
    private int id;
    private String alias;
    private String title;
    private String content;
    private boolean isResource;
}
