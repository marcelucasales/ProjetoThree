package br.com.compasso.projetothree.model;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import br.com.compasso.projetothree.dto.ProductDto;

@Document("product")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private Double price;
    
    public Product() {}

    public Product(String id, String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public void setId(String id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setDescription(String description) { this.description = description; }

    public void setPrice(Double price) { this.price = price; }

    public String getId() { return this.id; }

    public String getName() { return this.name; }

    public String getDescription() { return this.description; }

    public Double getPrice() { return this.price; }

    public static List<ProductDto> toDto(List<Product> p) {
        return p.stream().map( u -> new ProductDto(u.getId(), u.getName(), u.getDescription(), u.getPrice()) ).collect(Collectors.toList());
    }

    public ProductDto toDto() {
        return new ProductDto(this.id, this.name, this.description, this.price);
    }

    @Override
    public String toString() {
        return "id == " + this.id + "\n" +
        "description == " + this.description + "\n" +
        "name == " + this.name + "\n" +
        "price == " + this.price;
    }
}
