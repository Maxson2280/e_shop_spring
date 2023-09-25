package com.vyatsu.task14.controllers;

import com.vyatsu.task14.entities.Filter;
import com.vyatsu.task14.entities.Product;
import com.vyatsu.task14.services.ProductsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;

    private boolean showFilter = false;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public String showProductsList(Model model, @RequestParam(value = "page", required = false) Integer page,
                                   @RequestParam(value = "title", required = false) String title,
                                   @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
                                   @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice) {

        Product product = new Product();

        if (page == null) {
            page = 1;
        }

        Filter filter = new Filter(title, minPrice, maxPrice);

        model.addAttribute("title", title);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("product", product);
        model.addAttribute("products", productsService.getFilteredProductsAndPage(filter, PageRequest.of(page - 1, 5)).getContent());
        model.addAttribute("topProducts", productsService.getTopProducts());
        model.addAttribute("page", page);
        model.addAttribute("pages", productsService.getPages());



        return "products";
    }

    @GetMapping("/show/{id}")
    public String showProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productsService.read(id);

        productsService.viewProduct(product);

        model.addAttribute("product", product);

        return "product-page";
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        Product product = new Product();

        model.addAttribute("product", product);

        return "edit-add-product";
    }

    @PostMapping("/add")
    @Secured(value = "ROLE_ADMIN")
    public String addProduct(@ModelAttribute(value = "product") Product product) {
        productsService.saveOrUpdate(product);

        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id) {
        productsService.delete(id);

        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable(value = "id") Long id, Model model) {
        Product product = productsService.read(id);

        model.addAttribute("product", product);

        return "edit-add-product";
    }

    @GetMapping("/buy/{id}")
    public String buyProduct(@PathVariable Long id, @RequestParam("quantity") int quantity) {
        try {
            productsService.buyProduct(id, quantity);
            return "redirect:/";
        } catch (RuntimeException e) {
            return "redirect:/products/show/" + id;
        }
    }

}
