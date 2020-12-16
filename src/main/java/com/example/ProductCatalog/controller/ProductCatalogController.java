package com.example.ProductCatalog.controller;

import com.example.ProductCatalog.model.Product;
import com.example.ProductCatalog.repository.ProductsRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductCatalogController {

    private ProductsRepo productsRepo;

    public ProductCatalogController(ProductsRepo productsRepo) {
        this.productsRepo = productsRepo;
    }

    @GetMapping("/")
    public String showAllProducts(Model model) {
        List<Product> products = new ArrayList<>();
        productsRepo.findAll().forEach(i -> products.add(i));
        model.addAttribute("products",products);
        model.addAttribute("searchProduct",new Product());
        return  "products";
    }
    @GetMapping("/add")
    public String showAddProductsForm(Product product) {
        return "addProduct";
    }
    @PostMapping("/add")
    public String addProduct(Product product, Model model) {
        System.out.println(productsRepo.findMaxId());
        Long newId = productsRepo.findMaxId() + 1;
        System.out.println(newId);
        product.setId(newId);
        productsRepo.save(product);
        model.addAttribute("products", productsRepo.findAll());
        return "redirect:/products/";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        productsRepo.deleteById(id);
        model.addAttribute("products", productsRepo.findAll());
        return "redirect:/products/";
    }
    @PostMapping("/search")
    public String showSearchResult(Product searchProduct, Model model) {
        String name = searchProduct.getName();
        List<Product> products = productsRepo.findProductByName(name);
        model.addAttribute("products", products);
        return "search";
    }
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Optional<Product> product = productsRepo.findById(id);
        model.addAttribute("updatedProduct", product.get());
        return "updateProduct";
    }
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, Product product, Model model) {
        productsRepo.save(product);
        model.addAttribute("products", productsRepo.findAll());
        return "redirect:/products/";
    }
}
