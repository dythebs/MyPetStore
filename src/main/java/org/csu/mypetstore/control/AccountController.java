package org.csu.mypetstore.control;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    HttpServletRequest request;

    @GetMapping("/account/viewSignIn")
    public String viewSignIn(){
        return "account/signin";
    }

    @GetMapping("/account/register")
    public String viewRegister(){
        return "account/register";
    }

    @GetMapping("/account/viewSignIn_cn")
    public String viewSignIn_cn(){
        return "account/signin_cn";
    }

    @GetMapping("/account/register_cn")
    public String viewRegister_cn(){
        return "account/register_cn";
    }

    @PostMapping("/account/signin")
    public String signIn(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         Model model){

        Account account = accountService.getAccount(username,password);
        if(account != null){
            request.getSession().setAttribute("account",account);
            accountService.addRecord(account.getUsername(),"sign in");
            return "redirect:/catalog/main";
        }else{
            String failInfo = "Invalid username or password. Signon failed.";
            model.addAttribute("failInfo",failInfo);
            return "account/signin";
        }
    }

    @GetMapping("/account/signout")
    public String signOut(){
        accountService.addRecord(((Account)request.getSession().getAttribute("account")).getUsername(),"sign out");
        request.getSession().setAttribute("account",null);
        return "catalog/main";
    }

    @GetMapping("/account/myaccount")
    public String myaccount(Model model){
        Account account = (Account)request.getSession().getAttribute("account");
        model.addAttribute(account);
        return "account/myaccount";
    }

    @PostMapping("/account/updateaccount")
    public String updateaccount(Model model){
        Account account = (Account)request.getSession().getAttribute("account");
        account.setFirstName(request.getParameter("account.firstName"));
        account.setLastName(request.getParameter("account.lastName"));
        account.setEmail(request.getParameter("account.email"));
        account.setPhone(request.getParameter("account.phone"));
        account.setAddress1(request.getParameter("account.address1"));
        account.setAddress2(request.getParameter("account.address2"));
        account.setCity(request.getParameter("account.city"));
        account.setState(request.getParameter("account.state"));
        account.setZip(request.getParameter("account.zip"));
        account.setCountry(request.getParameter("account.country"));
        accountService.updateAccount(account);
        request.getSession().setAttribute("account",account);
        accountService.addRecord(account.getUsername(),"update account");
        return "redirect:/account/myaccount";
    }



    @PostMapping("account/regist")
    public String reigst(){
        Account account = new Account();
        account.setUsername(request.getParameter("username"));
        account.setPassword(request.getParameter("password"));
        account.setFirstName(request.getParameter("account.firstName"));
        account.setLastName(request.getParameter("account.lastName"));
        account.setEmail(request.getParameter("account.email"));
        account.setPhone(request.getParameter("account.phone"));
        account.setAddress1(request.getParameter("account.address1"));
        account.setAddress2(request.getParameter("account.address2"));
        account.setCity(request.getParameter("account.city"));
        account.setState(request.getParameter("account.state"));
        account.setZip(request.getParameter("account.zip"));
        account.setCountry(request.getParameter("account.country"));
        account.setLanguagePreference(request.getParameter("account.languagePreference"));
        account.setFavouriteCategoryId(request.getParameter("account.favouriteCategoryId"));
        account.setListOption(Boolean.valueOf(request.getParameter("account.listOption")));
        account.setBannerOption(Boolean.valueOf(request.getParameter("account.bannerOption")));
        request.getSession().setAttribute("account",account);
        accountService.insertAccount(account);
        accountService.addRecord(account.getUsername(),"regist");
        return "redirect:/catalog/main";
    }

    @GetMapping("/account/checkusername")
    @ResponseBody
    public String checkusername(@RequestParam("username") String username){
        System.out.println("------------------");
        System.out.println(username);
        System.out.println("------------------");
        if(accountService.getAccount(username)==null){
            return "true";
        }else{
            return "false";
        }
    }
}
