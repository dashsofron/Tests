package com.cft.shiftlab.back.shop.repositories;

import com.cft.shiftlab.back.shop.models.Monitor;
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
@ConditionalOnProperty(name = "useM.database", havingValue = "true")
public class DatabaseMonitorRepository implements MonitorRepository {
    private MonitorExtractor monitorExtractor;
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseMonitorRepository(MonitorExtractor monitorExtractor, NamedParameterJdbcTemplate jdbcTemplate) {
        this.monitorExtractor = monitorExtractor;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void initialize() {
        String createGenerateMonitorIdSequenceSql = "create sequence MONITOR_ID_GENERATOR";

        String createMonitorTableSql = "create table MONITORS (" +
                "PRODUCT_ID   VARCHAR(64) default MONITOR_ID_GENERATOR.nextval," +
                "SERIAL_NUM         VARCHAR(64)," +
                "PRODUCER  VARCHAR(64)," +
                "PRICE  INTEGER," +
                "AMOUNT  INTEGER," +
                "DIAGONAL  DOUBLE," +
                ");";
        jdbcTemplate.update(createGenerateMonitorIdSequenceSql, new MapSqlParameterSource());

        jdbcTemplate.update(createMonitorTableSql, new MapSqlParameterSource());
        // Заполним тестовыми данными
        addProduct(new Monitor("1", "C24G1", "AOC   ", 14999, 15, 24));
        addProduct(new Monitor("2", "S24F354FHI ", "Samsung    ", 6999, 10, 23));

    }

    @Override
    public Monitor fetchMonitor(String id) {

        String sql = "select PRODUCT_ID, SERIAL_NUM, PRICE,PRODUCER,AMOUNT,DIAGONAL " +
                "from MONITORS " +
                "where PRODUCT_ID=:productId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("productId", id);
        List<Monitor> monitors = jdbcTemplate.query(sql, params, monitorExtractor);
        if (monitors.isEmpty()) {
            return null;
        }
        return monitors.get(0);

    }

    @Override
    public Collection<Monitor> getAllMonitors() {
        String sql = "select PRODUCT_ID, SERIAL_NUM, PRICE,PRODUCER,AMOUNT,DIAGONAL " +
                "from MONITORS ";
        MapSqlParameterSource params = new MapSqlParameterSource();
        return jdbcTemplate.query(sql, params, monitorExtractor);
    }

    @Override
    public Monitor updateProduct(String id, Monitor product) {
        //Monitor productPrev=fetchMonitor(id);
        //product.checkFill(productPrev);

        String updateProductSql = "update MONITORS " +
                "set PRODUCT_ID=:productId, " +
                "SERIAL_NUM=:seriulNum, " +
                "PRODUCER=:producer, " +
                "PRICE=:price, " +
                "AMOUNT=:amount, " +
                "DIAGONAL=:diagonal " +
                "where PRODUCT_ID=:productId";
        MapSqlParameterSource productParams = new MapSqlParameterSource()
                .addValue("productId", id)
                .addValue("seriulNum", product.getSeriulNum())
                .addValue("producer", product.getProducer())
                .addValue("price", product.getPrice())
                .addValue("amount", product.getAmount())
                .addValue("diagonal", product.getDiagonal());
        jdbcTemplate.update(updateProductSql, productParams);
        return product;
    }

    @Override
    public Monitor addProduct(Monitor product) {

        String insertProductSql = "insert into MONITORS (SERIAL_NUM,PRODUCER,PRICE,AMOUNT,DIAGONAL) values" +
                "(:seriulNum, :producer, :price, :amount,:diagonal)";
        MapSqlParameterSource productParams = new MapSqlParameterSource()
                .addValue("seriulNum", product.getSeriulNum())
                .addValue("producer", product.getProducer())
                .addValue("price", product.getPrice())
                .addValue("amount", product.getAmount())
                .addValue("diagonal", product.getDiagonal());
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(insertProductSql, productParams, generatedKeyHolder);
        String productId = generatedKeyHolder.getKeys().get("PRODUCT_ID").toString();
        product.setId(productId);

        return product;
    }
}
