package com.gangstakittenz.rockets.maps;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class MapLoader implements RequestHandler<Map<String,Object>, String> {

    private static final String jdbcDatabaseUrl = "jdbc:postgresql://rocketreplays.cmdbrvrcgpjg.us-east-1.rds.amazonaws.com:5432/rockets";
    private static final String user = "SegFault";
    private static final String password = "Jackson93";
    public String handleRequest(Map<String,Object> input, Context context) {
        try {
            Connection dbConnection = DriverManager.getConnection(jdbcDatabaseUrl, user, password);
            Statement statement = dbConnection.createStatement();

            ResultSet rs = statement.executeQuery("Select version();");

            rs.next();
            System.out.println(rs.getString(1));
            return "YAY";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Didn't work.";
    }

    public static void main(String[] args){
        MapLoader loader = new MapLoader();
        loader.handleRequest(null, null);
    }
}
