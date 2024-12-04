package co.lunadev.adoptaweb.controllers;

import co.lunadev.adoptaweb.services.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/accounts")
public class AccountsAdminController {

    AccountService accountService;

    public AccountsAdminController(AccountService accountService) {
        this.accountService = accountService;
    }

    //create an user and a refugio register
    @PostMapping("/approve/{registrationRequestId}")
    public void approveAccount(@Positive(message = "no puede ser negativo") @PathVariable Long registrationRequestId) {
        accountService.approveAccount(registrationRequestId);
    }
    //set account enable to false
    public void disableAccount(){

    }
}
