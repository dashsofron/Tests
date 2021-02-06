package com.cft.shiftlab.back.shop.repositories;

import com.cft.shiftlab.back.shop.models.HDD;
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
public class HDDExtractor implements ResultSetExtractor<List<HDD>> {
    @Override
    public List<HDD> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<String, HDD> hdds = new HashMap<>();

        while (rs.next()) {
            String productId = rs.getString("PRODUCT_ID");

            HDD hdd;
            if (hdds.containsKey(productId)) {
                hdd = hdds.get(productId);
            } else {
                hdd = new HDD();

                hdd.setId(rs.getString("PRODUCT_ID"));

                hdd.setVolume(rs.getInt("VOLUME"));
                hdd.setSeriulNum(rs.getString("SERIAL_NUM"));
                hdd.setProducer(rs.getString("PRODUCER"));
                hdd.setPrice(rs.getInt("PRICE"));
                hdd.setAmount(rs.getInt("AMOUNT"));
                hdds.put(productId, hdd);
            }
        }
        return new ArrayList<>(hdds.values());
    }
}
