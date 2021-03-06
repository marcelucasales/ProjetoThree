package br.com.compasso.projetothree.controller;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.compasso.projetothree.config.MessageConfig;
import br.com.compasso.projetothree.dto.ProductDto;
import br.com.compasso.projetothree.dto.ProductStatus;
import br.com.compasso.projetothree.form.ProductForm;
import br.com.compasso.projetothree.model.Product;
import br.com.compasso.projetothree.repository.ProductRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Api rest produtos")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private ProductRepository repository;

    @ApiOperation(value = "Cria um produto no banco de dados.")
    @PostMapping
    @Transactional
    public ResponseEntity<ProductDto> cadastra(@Valid @RequestBody ProductForm form, UriComponentsBuilder u) {
        Product p = form.toProduct();
        this.repository.save(p);
        URI uri = u.path("/products/{id}").buildAndExpand(p.getId()).toUri();
        ProductStatus s = new ProductStatus(p, "sucess", "produto salvo no banco");
        template.convertAndSend(MessageConfig.EXCHANGE, MessageConfig.ROUTING_KEY, s);
        return ResponseEntity.created(uri).body(new ProductDto(p.getId(), p.getName(), p.getDescription(), p.getPrice()));
    }

    @ApiOperation(value = "Altera um produto espec??fico no banco de dados.")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProductDto> altera(@PathVariable("id") String id, @Valid @RequestBody ProductForm form) {
        if(repository.findById(id).isPresent()) {
            var p = form.atualiza(repository, id);
            ProductStatus s = new ProductStatus(p, "sucess", "produto atualizado no banco");
            template.convertAndSend(MessageConfig.EXCHANGE, MessageConfig.ROUTING_KEY, s);
            return ResponseEntity.ok().body(p.toDto());
        }
        ProductStatus s = new ProductStatus(null, "error", "ocorreu um erro");
        template.convertAndSend(MessageConfig.EXCHANGE, MessageConfig.ROUTING_KEY, s);
        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Busca um produto espec??fico no banco de dados.")
    @GetMapping("{id}")
    public ResponseEntity<ProductDto> listar(@PathVariable("id") String id) {
        if(repository.findById(id).isPresent()) {
            return ResponseEntity.ok().body(repository.findById(id).get().toDto());    
        }
        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Lista os produtos que est??o no banco de dados.")
    @GetMapping
    public List<ProductDto> listar() {
        return Product.toDto(repository.findAll());
    }

    @ApiOperation(value = "Lista os produtos que est??o no banco de dados atrav??s de um filtro.")
    @GetMapping("/search")
    public List<ProductDto> listar(@RequestParam(value = "min_price", defaultValue = "0") Double min_price, @RequestParam(value = "max_price", defaultValue = "99999999999999999") Double max_price, @RequestParam(value = "q", defaultValue = "") String q) {
        return Product.toDto(this.repository.search(min_price, max_price, q));
    }    

    @ApiOperation(value = "Remove um produto espec??fico que est?? no banco de dados.")
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable("id") String id) {
        if(repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
