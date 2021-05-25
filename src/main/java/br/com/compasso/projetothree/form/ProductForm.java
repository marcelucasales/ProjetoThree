package br.com.compasso.projetothree.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import br.com.compasso.projetothree.model.Product;
import br.com.compasso.projetothree.repository.ProductRepository;

public class ProductForm {
    @NotBlank(message = "campo não pode estar vazio ou nulo.")
    private String name;
    @NotBlank(message = "campo não pode estar vazio ou nulo.")
    private String description;
    //@NotBlank(message = "campo não pode estar vazio ou nulo.")
    @Positive(message = "Preço deve ser positivo.")
    private double price;
    
    public ProductForm() {}

    public ProductForm(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public void setName(String name) { this.name = name; }

    public void setDescription(String description) { this.description = description; }

    public void setPrice(double price) { this.price = price; }

    public String getName() { return this.name; }

    public String getDescription() { return this.description; }

    public double getPrice() { return this.price; }

    public Product toProduct() {
        return new Product(this.getName(), this.getDescription(), this.getPrice());
    }

    public Product atualiza(ProductRepository repository, String id) {
        var x = repository.findById(id).get();
        x.setName(this.name);
        x.setDescription(this.description);
        x.setPrice(this.price);
        repository.save(x);
        return x;
    }
}
