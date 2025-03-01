package com.riosr.sq_ch12.repositories;

import com.riosr.sq_ch12.model.Purchase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class PurchaseRepository {

    private final JdbcTemplate jdbcTemplate;

    public PurchaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void storePurchase(Purchase purchase) {
        String sql = "INSERT INTO purchase (product, price) VALUES (?, ?)";
        jdbcTemplate.update(sql, purchase.getProduct(), purchase.getPrice());
    }

    public List<Purchase> findAllPurchases() {
        String sql = "SELECT * FROM purchase";

        RowMapper<Purchase> purchaseRowMapper = (resultSet, rowNum) -> {
            Purchase rowObject = new Purchase();
            rowObject.setId(resultSet.getInt("id"));
            rowObject.setProduct(resultSet.getString("product"));
            rowObject.setPrice(resultSet.getBigDecimal("price"));
            return rowObject;
        };
        return jdbcTemplate.query(sql, purchaseRowMapper);
    }
}
