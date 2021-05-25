package br.com.compasso.projetothree.dto;

public class ProductDto {
    private String id;
    private String name;
    private String description;
    private Double price;
    
    public ProductDto() {}

    public ProductDto(String id, String name, String description, Double price) {
        this.id = id;
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
}
