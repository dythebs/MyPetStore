package org.csu.mypetstore.control;

import org.csu.mypetstore.domain.*;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CatalogController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private AccountService accountService;

    @GetMapping("/catalog/main")
    public String viewMain(){

        if(request.getSession().getAttribute("cart")==null){
            Cart cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        return "catalog/main";
    }

    //如何从客户端网页获取值 @RequestParam；如何将控制器的值传递给网页，Model
    @GetMapping("/catalog/category")
    public String viewCategory(@RequestParam("categoryId") String categoryId, Model model){
        if(categoryId != null) {
            Category category = catalogService.getCategory(categoryId);
            List<Product> productList = catalogService.getProductListByCategory(categoryId);
            model.addAttribute(category);
            model.addAttribute(productList);
            Account account = (Account)request.getSession().getAttribute("account");
            if(account != null){
                accountService.addRecord(account.getUsername(),"view "+categoryId);
            }

        }
        return "catalog/category";
    }

    @GetMapping("/catalog/product")
    public String viewProduct(@RequestParam("productId") String productId,Model model){
        if(productId != null){
            Product product = catalogService.getProduct(productId);
            List<Item> itemList = catalogService.getItemListByProduct(productId);
            model.addAttribute(product);
            model.addAttribute(itemList);
            Account account = (Account)request.getSession().getAttribute("account");
            if(account!=null){
                accountService.addRecord(account.getUsername(),"view "+productId);
            }
        }
        return "catalog/product";
    }

    @GetMapping("/catalog/item")
    public String viewItem(@RequestParam("itemId") String itemId,
                           Model model){
        if(itemId != null){
            Item item = catalogService.getItem(itemId);
            Product product = catalogService.getProduct(item.getProductId());
            model.addAttribute(item);
            model.addAttribute(product);
            Account account = (Account)request.getSession().getAttribute("account");
            if(account!=null){
                accountService.addRecord(account.getUsername(),"view "+itemId);
            }
        }
        return "catalog/item";
    }
}
