package app.store.persistence.repository;

import app.store.persistence.domain.Product;

import java.util.List;

public interface ProductRepositoryCustom {

    List<Product> onTextValueQuery(String text);
}
