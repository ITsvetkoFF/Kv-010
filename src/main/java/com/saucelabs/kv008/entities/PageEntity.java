package com.saucelabs.kv008.entities;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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

    public boolean isResource() {
        return isResource;
    }

    public void setResource(boolean isResource) {
        this.isResource = isResource;
    }

    @Override
    public String toString() {
        return "PageEntity{" +
                "id=" + id +
                ", alias='" + alias + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", isResource=" + isResource +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PageEntity that = (PageEntity) o;

        if (isResource != that.isResource) return false;
        if (!alias.equals(that.alias)) return false;
        if (!title.equals(that.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = alias.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (isResource ? 1 : 0);
        return result;
    }
}
