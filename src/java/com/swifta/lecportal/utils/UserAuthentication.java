/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swifta.lecportal.utils;

import com.swifta.lecportal.dblogic.JDCConnection;
import com.swifta.lecportal.entities.PortalAdmin;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import org.apache.log4j.Logger;

/**
 *
 * @author Opeyemi
 */
public class UserAuthentication {
    
    private Logger logger = Logger.getLogger(UserAuthentication.class);
    
    public UserAuthentication() {
    }
    
    public PortalAdmin authenticateAdmin(String username, String password) throws IOException, SQLException {
        String sqlQuery = "select * from admins where username = ? and password= ?";
        
        logger.info(sqlQuery);
        JDCConnection con = PortalDatabase.source.getConnection();
        PreparedStatement prepStmt = con.prepareStatement(sqlQuery);
        prepStmt.setString(1, username);
        prepStmt.setString(2, password);
        ResultSet res = prepStmt.executeQuery();
        PortalDatabase.source.returnConnection(con);
        
        PortalAdmin portalAdmin = null;
        while (res.next()) {
            portalAdmin = new PortalAdmin();
            portalAdmin.setUsername(res.getString("username"));
            portalAdmin.setId(res.getInt("id"));
        }
        
        return portalAdmin;
    }
    
    public String getFilePath() {
        logger.info("Getting properties file path ... "+System.getProperty("os.name"));
        logger.info("Getting properties file path2 ... "+System.getProperty("os.name").startsWith("mac"));
        
        
        String fileName = "";
        if (System.getProperty("os.name").startsWith("Mac")) {
            fileName = "/Users/modupealadeojebi/Desktop/afrinnovadatabase.properties";
            }else{
                fileName = "/opt/swifta/server/properties/afrinnovadatabase.properties";
            }
        
        
//        if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) {
//            fileName = "C:\\PropertyFiles\\afrinnovadatabase.properties";
//        }
//        if (System.getProperty("os.name").toLowerCase().indexOf("sunos") >= 0) {
//            fileName = "/opt/swifta/server/properties/testafrinnovadatabase.properties";
//        }
//        if (System.getProperty("os.name").toLowerCase().indexOf("nix") >= 0) {
//            fileName = "/opt/swifta/server/properties/afrinnovadatabase.properties";
//        }if (System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0) {
//            fileName = "/Users/user/NetBeansProjects/MTNLECPortal/PropertyFiles/afrinnovadatabase.properties";
//        }
//        if (fileName.length() <= 0) {
//            fileName = "/opt/swifta/server/properties/afrinnovadatabase.properties";
//        }
        logger.info("Fetching properties file from : " + fileName);
        return fileName;
    }
}
