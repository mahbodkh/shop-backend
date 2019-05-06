package app.store.service.dto;


import app.store.service.dto.enums.CartStatusDto;

import java.util.ArrayList;
import java.util.List;

public class CartDto {

    public CartDto() {
    }

    private String id;
    private String userId;
    private List<ProductCartDto> productCarts = new ArrayList<>();
    private Integer quantity = 0;
    private Double total = 0d;
    private CartStatusDto status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<ProductCartDto> getProductCarts() {
        return productCarts;
    }

    public void setProductCarts(List<ProductCartDto> productCarts) {
        this.productCarts = productCarts;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public CartStatusDto getStatus() {
        return status;
    }

    public void setStatus(CartStatusDto status) {
        this.status = status;
    }
}
