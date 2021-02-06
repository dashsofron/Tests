package com.cft.shiftlab.back.shop.repositories;

import com.cft.shiftlab.back.shop.exceptions.SizeException;
import com.cft.shiftlab.back.shop.models.Laptop;
import com.cft.shiftlab.back.shop.models.Size;
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
@ConditionalOnProperty(name = "useL.database", havingValue = "true")
public class DatabaseLaptopRepository implements LaptopRepository {
    private LaptopExtractor laptopExtractor;
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseLaptopRepository(LaptopExtractor laptopExtractor, NamedParameterJdbcTemplate jdbcTemplate) {
        this.laptopExtractor = laptopExtractor;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void initialize() {
        String createGenerateLaptopIdSequenceSql = "create sequence LAPTOP_ID_GENERATOR";
        String createLaptopTableSql = "create table LAPTOPS (" +
                "PRODUCT_ID   VARCHAR(64) default LAPTOP_ID_GENERATOR.nextval," +
                "SERIAL_NUM         VARCHAR(64)," +
                "PRODUCER  VARCHAR(64)," +
                "PRICE  INTEGER," +
                "AMOUNT  INTEGER," +
                "SIZE_  VARCHAR(64)," +
                ");";
        jdbcTemplate.update(createGenerateLaptopIdSequenceSql, new MapSqlParameterSource());
        jdbcTemplate.update(createLaptopTableSql, new MapSqlParameterSource());
        // Заполним тестовыми данными
        addProduct(new Laptop("1", "F509FA-BQ878", "ASUS  ", 31999, 30, Size.INCH_15));
        addProduct(new Laptop("2", "15s-eq1041ur-BQ878", "HP   ", 30599, 10, Size.INCH_13));

    }

    @Override
    public Laptop fetchLaptop(String id) {

        String sql = "select PRODUCT_ID, SERIAL_NUM, PRICE,PRODUCER,AMOUNT,SIZE_ " +
                "from LAPTOPS " +
                "where PRODUCT_ID=:productId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("productId", id);
        List<Laptop> laptops = jdbcTemplate.query(sql, params, laptopExtractor);
        if (laptops.isEmpty()) {
            return null;
        }
        return laptops.get(0);
    }

    @Override
    public Collection<Laptop> getAllLaptops() {

        String sql = "select PRODUCT_ID, SERIAL_NUM, PRICE,PRODUCER,AMOUNT,SIZE_ " +
                "from LAPTOPS ";
        MapSqlParameterSource params = new MapSqlParameterSource();
        return jdbcTemplate.query(sql, params, laptopExtractor);
    }

    @Override
    public Laptop updateProduct(String id, Laptop product) {
        if (!Laptop.enumContains(product.getSize())) {
            throw new SizeException();
        }
        String updateProductSql = "update LAPTOPS " +
                "set PRODUCT_ID=:productId, " +
                "SERIAL_NUM=:seriulNum, " +
                "PRODUCER=:producer, " +
                "PRICE=:price, " +
                "AMOUNT=:amount, " +
                "SIZE_=:size " +
                "where PRODUCT_ID=:productId";
        MapSqlParameterSource productParams = new MapSqlParameterSource()
                .addValue("productId", id)
                .addValue("seriulNum", product.getSeriulNum())
                .addValue("producer", product.getProducer())
                .addValue("price", product.getPrice())
                .addValue("amount", product.getAmount())
                .addValue("size", product.getSize());
        jdbcTemplate.update(updateProductSql, productParams);
        return product;
    }

    @Override
    public Laptop addProduct(Laptop product) {
        if (!Laptop.enumContains(product.getSize())) {
            throw new SizeException();
        }
        String insertProductSql = "insert into LAPTOPS (SERIAL_NUM,PRODUCER,PRICE,AMOUNT,SIZE_) values" +
                "( :seriulNum, :producer, :price, :amount,:size)";
        MapSqlParameterSource productParams = new MapSqlParameterSource()
                .addValue("seriulNum", product.getSeriulNum())
                .addValue("producer", product.getProducer())
                .addValue("price", product.getPrice())
                .addValue("amount", product.getAmount())
                .addValue("size", product.getSize());
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(insertProductSql, productParams, generatedKeyHolder);
        String productId = generatedKeyHolder.getKeys().get("PRODUCT_ID").toString();
        product.setId(productId);

        return product;
    }
}
