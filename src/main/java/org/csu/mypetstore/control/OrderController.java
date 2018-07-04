package org.csu.mypetstore.control;

import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.scene.chart.Chart;
import org.csu.mypetstore.domain.*;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    OrderService orderService;
    @Autowired
    CatalogService catalogService;
    @Autowired
    AccountService accountService;

    @GetMapping("/order/cart")
    public String viewCart(Model model){
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        if(cart==null){
            cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        List<CartItem> cartItemList = cart.getCartItemList();
        model.addAttribute("cart",cart);
        model.addAttribute("cartItemList",cartItemList);
        return "order/cart";
    }

    @PostMapping("/order/updatecart")
    public String updatecart(){
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        List<CartItem> cartItemList = cart.getCartItemList();
        for(CartItem cartItem : cartItemList){
            cartItem.setQuantity(Integer.valueOf(request.getParameter(cartItem.getItem().getItemId())));
        }
        request.getSession().setAttribute("cart",cart);
        return "redirect:/order/cart";
    }

    @GetMapping("/order/addtocart")
    public String addtocart(String itemId){
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        cart.addItem(catalogService.getItem(itemId),true);
        request.getSession().setAttribute("cart",cart);
        Account account = (Account)request.getSession().getAttribute("account");
        if(account!=null){
            accountService.addRecord(account.getUsername(),"add "+itemId+" to cart");
        }
        return "redirect:/order/cart";
    }

    @GetMapping("/order/removefromcart")
    public String removefromcart(String itemId){
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        cart.removeItemById(itemId);
        request.getSession().setAttribute("cart",cart);
        Account account = (Account)request.getSession().getAttribute("account");
        if(account!=null){
            accountService.addRecord(account.getUsername(),"remove "+itemId+" from cart");
        }
        return "redirect:/order/cart";
    }

    @GetMapping("/order/checkout")
    public String checkout(Model model){
        if(request.getSession().getAttribute("account") == null){
            return "redirect:/account/viewSignIn";
        }else{
            Account account = (Account)request.getSession().getAttribute("account");
            model.addAttribute(account);
            return "order/checkout";
        }
    }

    @PostMapping("/order/makeorder")
    public String makeOrder(Model model){
        Order order = new Order();
        order.setCardType(request.getParameter("order.cardType"));
        order.setCreditCard(request.getParameter("order.creditCard"));
        order.setExpiryDate(request.getParameter("order.expiryDate"));
        order.setBillToFirstName(request.getParameter("order.billToFirstName"));
        order.setBillToLastName(request.getParameter("order.billToLastName"));
        order.setBillAddress1(request.getParameter("order.billAddress1"));
        order.setBillAddress2(request.getParameter("order.billAddress2"));
        order.setBillCity(request.getParameter("order.billCity"));
        order.setBillState(request.getParameter("order.billState"));
        order.setBillZip(request.getParameter("order.billZip"));
        order.setBillCountry(request.getParameter("order.billCountry"));
        order.setOrderDate(new Date());
        order.setTotalPrice(((Cart)request.getSession().getAttribute("cart")).getSubTotal());
        model.addAttribute(order);
        request.getSession().setAttribute("order",order);
        Account account = (Account)request.getSession().getAttribute("account");
        if(account!=null){
            accountService.addRecord(account.getUsername(),"make order");
        }
        return "order/printorder";
    }

    @GetMapping("/order/confirm")
    public String confirmOrder(Model model){
        Order order = (Order)request.getSession().getAttribute("order");
        model.addAttribute(order);
        Account account = (Account)request.getSession().getAttribute("account");
        order.setUsername(account.getUsername());
        order.setShipAddress1(order.getBillAddress1());
        order.setShipAddress2(order.getBillAddress2());
        order.setShipCity(order.getBillCity());
        order.setShipCountry(order.getBillCountry());
        order.setShipState(order.getBillState());
        order.setShipZip(order.getBillZip());
        order.setShipToFirstName(order.getBillToFirstName());
        order.setShipToLastName(order.getBillToLastName());
        order.setCourier("courier");
        order.setLocale("locale");
        order.setOrderId(orderService.getNextId("ordernum"));
        orderService.insertOrder(order);
        return "order/ordersuccess";
    }

    @GetMapping("/order/myorder")
    public String viewMyOrder(Model model){
        List<Order> orderList = orderService.getOrdersByUsername("j2ee");
        model.addAttribute(orderList);
        return "order/myorder";
    }

    @GetMapping("/order/seeorder")
    public String seeOrder(@RequestParam("orderId") int orderId,Model model){
        Order order = orderService.getOrder(orderId);
        model.addAttribute(order);
        return "order/printorder";
    }
}
