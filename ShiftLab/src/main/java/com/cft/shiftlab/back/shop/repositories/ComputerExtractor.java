package com.cft.shiftlab.back.shop.repositories;

import com.cft.shiftlab.back.shop.models.Computer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * for getting data from database
 */
@Component
public class ComputerExtractor implements ResultSetExtractor<List<Computer>> {
    @Override
    public List<Computer> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<String, Computer> computers = new HashMap<>();

        while (rs.next()) {
            String productId = rs.getString("PRODUCT_ID");

            Computer computer;
            if (computers.containsKey(productId)) {
                computer = computers.get(productId);
            } else {
                computer = new Computer();

                computer.setId(rs.getString("PRODUCT_ID"));

                computer.setFormFactor(rs.getString("FORM_FACTOR"));
                computer.setSeriulNum(rs.getString("SERIAL_NUM"));
                computer.setProducer(rs.getString("PRODUCER"));
                computer.setPrice(rs.getInt("PRICE"));
                computer.setAmount(rs.getInt("AMOUNT"));
                computers.put(productId, computer);
            }
        }
        return new ArrayList<>(computers.values());
    }
}
