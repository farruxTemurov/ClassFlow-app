package com.zumba.dao;

import com.zumba.bean.Batches;
import com.zumba.resource.DatabaseResource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BatchesDAO {
    private Connection con;

    public BatchesDAO() {
        this.con = DatabaseResource.getDbConnection();
    }

    public List<Batches> getAllBatches() {
        List<Batches> batchList = new ArrayList<>();
        String query = "SELECT batch_id, batch_type, batch_time FROM batches";

        try (PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                batchList.add(new Batches(rs.getInt("batch_id"), rs.getString("batch_type"), rs.getString("batch_time")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return batchList;
    }
}
