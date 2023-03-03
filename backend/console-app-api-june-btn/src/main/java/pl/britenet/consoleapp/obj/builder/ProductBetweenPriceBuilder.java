package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.obj.model.ProductBetweenPrice;

public class ProductBetweenPriceBuilder {

    private final ProductBetweenPrice productBetweenPrice;

    public ProductBetweenPriceBuilder(int id) {
        this.productBetweenPrice = new ProductBetweenPrice(id);
    }

    public ProductBetweenPriceBuilder setName(String name) {
        this.productBetweenPrice.setName(name);
        return this;
    }

    public ProductBetweenPriceBuilder setPrice(double price) {
        this.productBetweenPrice.setPrice(price);
        return this;
    }

    public ProductBetweenPrice getProductBetweenPrice() {
        return this.productBetweenPrice;
    }
}
