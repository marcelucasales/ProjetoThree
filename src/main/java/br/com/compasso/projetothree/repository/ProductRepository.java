package br.com.compasso.projetothree.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import br.com.compasso.projetothree.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    
    @Query(value = "{ $and: [ { 'price': { $gte: ?0 } }, { 'price': { $lte: ?1 } }, { $or: [ { 'name': /?2/ }, { 'description': /?2/ } ] } ] }")
    public List<Product> search(Double min_price, Double max_price, String q);

}