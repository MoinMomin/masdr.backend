package interaction.cx.masdr.sa.backend.service;

import interaction.cx.masdr.sa.backend.config.DataSourceConfig;
import interaction.cx.masdr.sa.backend.config.TenantRoutingDataSource;
import interaction.cx.masdr.sa.backend.model.Product;
import interaction.cx.masdr.sa.backend.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product getProductByProductId(long productId) {
        Optional<Product> product1= productRepository.findById(productId);
        if(product1.isPresent()){
            return product1.get();
        }
        return null;
    }
    @Override
    public Product getProductByName(String name) {
        Optional<Product> product1= productRepository.findByName(name);
        if(product1.isPresent()){
            return product1.get();
        }
        return null;
    }

}
