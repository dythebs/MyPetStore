package org.csu.mypetstore.control;

import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {
    @Autowired
    CatalogService catalogService;

    @PostMapping("/search")
    public String viewSearch(@RequestParam("keyword") String keyword,Model model){
        List<Product> productList = catalogService.searchProductList(keyword);
        model.addAttribute(productList);
        return "search";
    }
}
