package com.saucelabs.kv008.entities;


import com.saucelabs.kv008.dao.RegionDao;

/**
 *  __tablename__ = 'users'
 *  id = Column(Integer, primary_key=True, autoincrement=True)
 *  first_name = Column(String(100), nullable=False)
 *  last_name = Column(String(100))
 *  email = Column(String(100), nullable=False,
 *  password = Column(String(100), nullable=False)
 *  region_id = Column(Integer, ForeignKey('regions.id'))
 *  google_id = Column(String(100))
 *  facebook_id = Column(String(100))
 *
 *  region = relationship('Region')
 */
public class UserEntity {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private int region_id;
    private String google_id;
    private String facebook_id;

    RegionEntity region;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    public String getGoogle_id() {
        return google_id;
    }

    public void setGoogle_id(String google_id) {
        this.google_id = google_id;
    }

    public String getFacebook_id() {
        return facebook_id;
    }

    public void setFacebook_id(String facebook_id) {
        this.facebook_id = facebook_id;
    }

    public RegionEntity getRegion() {
        if(this.region == null && this.region_id !=0){
            region=  new RegionDao().readByKey(this.getRegion_id());
        }
        return region;
    }

    public void setRegion(RegionEntity region) {
        this.region = region;
        this.region_id = region.getId();
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", region_id=" + region_id +
                ", google_id='" + google_id + '\'' +
                ", facebook_id='" + facebook_id + '\'' +
                '}';
    }
}
