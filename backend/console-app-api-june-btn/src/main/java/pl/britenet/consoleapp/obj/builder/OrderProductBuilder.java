package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.Constants;
import pl.britenet.consoleapp.obj.model.CartProduct;
import pl.britenet.consoleapp.obj.model.OrderProduct;

public class OrderProductBuilder {

    private final OrderProduct orderProduct;

    public OrderProductBuilder(int id){
        this.orderProduct = new OrderProduct(id);
    }

    public OrderProductBuilder(){
        this.orderProduct = new OrderProduct();
    }

    public OrderProductBuilder setOrdersId(int ordersId) {
        this.orderProduct.setOrdersId(ordersId);
        return this;
    }

    public OrderProductBuilder setProductId(int productId) {
        this.orderProduct.setProductId(productId);
        return this;
    }

    public OrderProductBuilder setQuantity(int quantity) {
        this.orderProduct.setQuantity(quantity);
        return this;
    }

    public OrderProductBuilder setPrice(double price) {
        this.orderProduct.setPrice(price);
        return this;
    }

    public OrderProduct getOrderProduct() {
        return this.orderProduct;
    }

}
