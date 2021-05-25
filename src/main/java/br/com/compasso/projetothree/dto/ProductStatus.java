package br.com.compasso.projetothree.dto;

import br.com.compasso.projetothree.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductStatus {
    private Product product;
    private String status;
    private String message;

    public ProductStatus() {
    }

    public ProductStatus(Product product, String status, String msg) {
        this.product = product;
        this.message = msg;
        this.status = status;
    }

    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    @Override
    public String toString() {
        return "status == " + this.status +"\n" +
        "message == " + this.message + "\n" +
        "product == " + this.product + "\n";
    }
    
}
