package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Movie;

public class MovieDAO extends DBContext {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public MovieDAO() {
        conn = getConnection();
    }

}