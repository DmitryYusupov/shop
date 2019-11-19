package ru.yusdm.shop.admin.api.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = AdminRestController.ADMIN_API)
public class AdminRestController {
    public static final String ADMIN_API = "/admin";

    @GetMapping
    public String getAdminInAction() {
        return "ADMIN IN ACTION";
    }
}
