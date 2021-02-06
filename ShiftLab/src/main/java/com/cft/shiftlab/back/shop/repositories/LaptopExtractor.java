package com.cft.shiftlab.back.shop.repositories;

import com.cft.shiftlab.back.shop.models.Laptop;
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
public class LaptopExtractor implements ResultSetExtractor<List<Laptop>> {
    @Override
    public List<Laptop> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<String, Laptop> laptops = new HashMap<>();

        while (rs.next()) {
            String productId = rs.getString("PRODUCT_ID");

            Laptop laptop;
            if (laptops.containsKey(productId)) {
                laptop = laptops.get(productId);
            } else {
                laptop = new Laptop();

                laptop.setId(rs.getString("PRODUCT_ID"));

                laptop.setSize(rs.getInt("SIZE_"));
                laptop.setSeriulNum(rs.getString("SERIAL_NUM"));
                laptop.setProducer(rs.getString("PRODUCER"));
                laptop.setPrice(rs.getInt("PRICE"));
                laptop.setAmount(rs.getInt("AMOUNT"));
                laptops.put(productId, laptop);
            }
        }
        return new ArrayList<>(laptops.values());
    }
}
