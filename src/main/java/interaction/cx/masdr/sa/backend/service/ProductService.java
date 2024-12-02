package interaction.cx.masdr.sa.backend.service;

import interaction.cx.masdr.sa.backend.model.Product;

public interface ProductService {
    public void createProduct(Product product);

    Product getProductByProductId(long productId);

    public Product getProductByName(String name);
}
