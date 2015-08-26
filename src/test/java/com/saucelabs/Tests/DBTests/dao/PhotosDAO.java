package com.saucelabs.Tests.DBTests.dao;

import com.saucelabs.Tests.DBTests.entities.Photos;
import com.saucelabs.Tests.DBTests.entities.PhotosId;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by acidroed on 25.08.2015.
 */
public class PhotosDAO extends MainDAO {

    public Photos findById(PhotosId id) {
        Photos photo = (Photos) getSession().get(Photos.class, id);
        closeSession();
        return photo;
    }

    public List<Photos> findByUserIdAndProblemID(int userID, int problemID) {
        Photos photos = null;
        Query query = getSession().createQuery("from Photos where Users_Id = :userID and Problems_Id = :problemID");
        query.setParameter("userID", userID);
        query.setParameter("problemID", problemID);
        List<Photos> photosList = query.list();
        closeSession();
        return photosList;
    }

}
