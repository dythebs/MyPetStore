package org.csu.mypetstore.control;

import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class CatalogRestController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/catalog/category/{categoryId}")
    public Category category(@PathVariable("categoryId") String categoryId){
        return catalogService.getCategory(categoryId);
    }

    @GetMapping("/catalog/category/{categoryId}/products")
    public List<Product> productList(@PathVariable("categoryId") String categoryId){
        return catalogService.getProductListByCategory(categoryId);
    }

    @GetMapping("/catalog/product/{productId}")
    public Product product(@PathVariable("productId") String productId){
        return catalogService.getProduct(productId);
    }

    @GetMapping("/catalog/product/{productId}/items")
    public List<Item> itemList(@PathVariable("productId") String productId){
        return catalogService.getItemListByProduct(productId);
    }

    @GetMapping("/catalog/item/{itemId}")
    public Item item(@PathVariable("itemId") String itemId){
        return catalogService.getItem(itemId);
    }

    @GetMapping("/catalog/search/{keyword}")
    public List<Product> searchProductList(@PathVariable("keyword") String keyword){
        return catalogService.searchProductList(keyword);
    }

    @GetMapping("/catalog/search/")
    public List<Product> searchProductList2(){
        return catalogService.searchProductList("");
    }

}

