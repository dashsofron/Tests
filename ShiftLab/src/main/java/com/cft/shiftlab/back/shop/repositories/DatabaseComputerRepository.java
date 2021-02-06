package com.cft.shiftlab.back.shop.repositories;

import com.cft.shiftlab.back.shop.exceptions.FormFactorException;
import com.cft.shiftlab.back.shop.models.Computer;
import com.cft.shiftlab.back.shop.models.FormFactor;
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
@ConditionalOnProperty(name = "useC.database", havingValue = "true")
public class DatabaseComputerRepository implements ComputerRepository {
    private ComputerExtractor computerExtractor;
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseComputerRepository(ComputerExtractor computerExtractor, NamedParameterJdbcTemplate jdbcTemplate) {
        this.computerExtractor = computerExtractor;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void initialize() throws FormFactorException {
        String createGenerateComputerIdSequenceSql = "create sequence COMPUTER_ID_GENERATOR";

        String createComputerTableSql = "create table COMPUTERS (" +
                "PRODUCT_ID   VARCHAR(64) default COMPUTER_ID_GENERATOR.nextval," +
                "SERIAL_NUM         VARCHAR(64)," +
                "PRODUCER  VARCHAR(64)," +
                "PRICE  INTEGER," +
                "AMOUNT  INTEGER," +
                "FORM_FACTOR  VARCHAR(64)," +
                ");";
        jdbcTemplate.update(createGenerateComputerIdSequenceSql, new MapSqlParameterSource());
        jdbcTemplate.update(createComputerTableSql, new MapSqlParameterSource());
        // Заполним тестовыми данными
        addProduct(new Computer("1", "H223", "DEXP ", 21699, 5, FormFactor.desktop));
        addProduct(new Computer("2", "510S-07ICK", "Lenovo  ", 28999, 15, FormFactor.desktop));
        addProduct(new Computer("3", "BOXNUC8i7BEH2", "INTEL  ", 40990, 2, FormFactor.nettop));
        addProduct(new Computer("4", "V530-24ICB", "LENOVO   ", 55220, 1, FormFactor.monoblok));

    }

    @Override
    public Computer fetchComputer(String id) {
        String sql = "select PRODUCT_ID, SERIAL_NUM, PRICE,PRODUCER,AMOUNT,FORM_FACTOR " +
                "from COMPUTERS " +
                "where PRODUCT_ID=:productId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("productId", id);
        List<Computer> computers = jdbcTemplate.query(sql, params, computerExtractor);
        if (computers.isEmpty()) {
            return null;
        }
        return computers.get(0);
    }

    @Override
    public Collection<Computer> getAllComputers() {
        String sql = "select COMPUTERS.PRODUCT_ID, SERIAL_NUM, PRICE,PRODUCER,AMOUNT,FORM_FACTOR " +
                "from COMPUTERS ";
        MapSqlParameterSource params = new MapSqlParameterSource();
        return jdbcTemplate.query(sql, params, computerExtractor);

    }

    @Override
    public Computer updateProduct(String id, Computer product) {
        if (!Computer.enumContains(product.getFormFactor())) {
            throw new FormFactorException();
        }
        String updateProductSql = "update COMPUTERS " +
                "set PRODUCT_ID=:productId, " +
                "SERIAL_NUM=:seriulNum, " +
                "PRODUCER=:producer, " +
                "PRICE=:price, " +
                "AMOUNT=:amount, " +
                "FORM_FACTOR=:formFactor " +
                "where PRODUCT_ID=:productId";
        MapSqlParameterSource productParams = new MapSqlParameterSource()
                .addValue("productId", id)
                .addValue("seriulNum", product.getSeriulNum())
                .addValue("producer", product.getProducer())
                .addValue("price", product.getPrice())
                .addValue("amount", product.getAmount())
                .addValue("formFactor", product.getFormFactor());
        jdbcTemplate.update(updateProductSql, productParams);
        return product;
    }

    @Override
    public Computer addProduct(Computer product) throws FormFactorException {
        if (!Computer.enumContains(product.getFormFactor())) {
            throw new FormFactorException();
        }
        String insertProductSql = "insert into COMPUTERS (SERIAL_NUM,PRODUCER,PRICE,AMOUNT,FORM_FACTOR) values" +
                "(:seriulNum, :producer, :price, :amount,:formFactor)";
        MapSqlParameterSource productParams = new MapSqlParameterSource()
                .addValue("seriulNum", product.getSeriulNum())
                .addValue("producer", product.getProducer())
                .addValue("price", product.getPrice())
                .addValue("amount", product.getAmount())
                .addValue("formFactor", product.getFormFactor());
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(insertProductSql, productParams, generatedKeyHolder);
        String productId = generatedKeyHolder.getKeys().get("PRODUCT_ID").toString();
        product.setId(productId);
        String insertIdSql = "insert into COMPUTERS (PRODUCT_ID) values (:productId)";
        MapSqlParameterSource idParams = new MapSqlParameterSource()
                .addValue("productId", productId);
        jdbcTemplate.update(insertIdSql, idParams);

        return product;
    }
}
