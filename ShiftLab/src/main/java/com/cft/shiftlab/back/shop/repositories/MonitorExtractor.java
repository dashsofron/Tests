package com.cft.shiftlab.back.shop.repositories;

import com.cft.shiftlab.back.shop.models.Monitor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MonitorExtractor implements ResultSetExtractor<List<Monitor>> {
    @Override
    public List<Monitor> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<String, Monitor> monitors = new HashMap<>();

        while (rs.next()) {
            String productId = rs.getString("PRODUCT_ID");

            Monitor monitor;
            if (monitors.containsKey(productId)) {
                monitor = monitors.get(productId);
            } else {
                monitor = new Monitor();

                monitor.setId(rs.getString("PRODUCT_ID"));

                monitor.setDiagonal(rs.getInt("DIAGONAL"));
                monitor.setSeriulNum(rs.getString("SERIAL_NUM"));
                monitor.setProducer(rs.getString("PRODUCER"));
                monitor.setPrice(rs.getInt("PRICE"));
                monitor.setAmount(rs.getInt("AMOUNT"));
                monitors.put(productId, monitor);
            }
        }
        return new ArrayList<>(monitors.values());
    }
}