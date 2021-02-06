package com.cft.shiftlab.back.shop.repositories;

import com.cft.shiftlab.back.shop.models.HDD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;

@Repository
@ConditionalOnProperty(name = "useH.database", havingValue = "true")
public class DatabaseHDDRepository implements HDDRepository {
    private HDDExtractor hddExtractor;
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseHDDRepository(HDDExtractor hddExtractor, NamedParameterJdbcTemplate jdbcTemplate) {
        this.hddExtractor = hddExtractor;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void initialize() {
        String createGenerateHDDIdSequenceSql = "create sequence HDD_ID_GENERATOR";

        String createHddTableSql = "create table HDDS (" +
                "PRODUCT_ID   VARCHAR(64) default HDD_ID_GENERATOR.nextval," +
                "SERIAL_NUM         VARCHAR(64)," +
                "PRODUCER  VARCHAR(64)," +
                "PRICE  INTEGER," +
                "AMOUNT  INTEGER," +
                "VOLUME  INTEGER," +
                ");";
        jdbcTemplate.update(createGenerateHDDIdSequenceSql, new MapSqlParameterSource());
        jdbcTemplate.update(createHddTableSql, new MapSqlParameterSource());
        // Заполним тестовыми данными
        addProduct(new HDD("1", "40PURZ", "WD ", 9799, 30, 4));
        addProduct(new HDD("2", "10EZEX", "WD ", 3199, 50, 1));

    }

    @Override
    public HDD fetchHDD(String id) {

        String sql = "select PRODUCT_ID, SERIAL_NUM, PRICE,PRODUCER,AMOUNT,VOLUME " +
                "from HDDS " +
                "where PRODUCT_ID=:productId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("productId", id);
        List<HDD> hdds = jdbcTemplate.query(sql, params, hddExtractor);
        if (hdds.isEmpty()) {
            return null;
        }
        return hdds.get(0);
    }

    @Override
    public Collection<HDD> getAllHDDs() {
        String sql = "select PRODUCT_ID, SERIAL_NUM, PRICE,PRODUCER,AMOUNT,VOLUME " +
                "from HDDS ";
        MapSqlParameterSource params = new MapSqlParameterSource();
        return jdbcTemplate.query(sql, params, hddExtractor);

    }

    @Override
    public HDD updateProduct(String id, HDD product) {

        String updateProductSql = "update HDDS " +
                "set PRODUCT_ID=:productId, " +
                "SERIAL_NUM=:seriulNum, " +
                "PRODUCER=:producer, " +
                "PRICE=:price, " +
                "AMOUNT=:amount, " +
                "VOLUME=:volume " +
                "where PRODUCT_ID=:productId";
        MapSqlParameterSource productParams = new MapSqlParameterSource()
                .addValue("productId", id)
                .addValue("seriulNum", product.getSeriulNum())
                .addValue("producer", product.getProducer())
                .addValue("price", product.getPrice())
                .addValue("amount", product.getAmount())
                .addValue("volume", product.getVolume());
        jdbcTemplate.update(updateProductSql, productParams);
        return product;
    }

    @Override
    public HDD addProduct(HDD product) {

        String insertProductSql = "insert into HDDS (SERIAL_NUM,PRODUCER,PRICE,AMOUNT,VOLUME) values" +
                "( :seriulNum, :producer, :price, :amount,:volume)";
        MapSqlParameterSource productParams = new MapSqlParameterSource()
                .addValue("seriulNum", product.getSeriulNum())
                .addValue("producer", product.getProducer())
                .addValue("price", product.getPrice())
                .addValue("amount", product.getAmount())
                .addValue("volume", product.getVolume());
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(insertProductSql, productParams, generatedKeyHolder);
        String productId = generatedKeyHolder.getKeys().get("PRODUCT_ID").toString();
        product.setId(productId);

        return product;
    }
}
