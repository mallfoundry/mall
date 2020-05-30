package org.mallfoundry.catalog;

import org.mallfoundry.data.SliceList;
import org.mallfoundry.inventory.InventoryAdjustment;

import java.util.Optional;

public interface ProductService {

    ProductQuery createProductQuery();

    ProductId createProductId(String id);

    Product createProduct();

    Optional<Product> getProduct(String id);

    SliceList<Product> getProducts(ProductQuery query);

    Product addProduct(Product product);

    Product saveProduct(Product product);

    void adjustInventory(InventoryAdjustment adjustment);
}
