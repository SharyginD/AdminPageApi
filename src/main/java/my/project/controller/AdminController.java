package my.project.controller;

import my.project.domain.dto.Admin;
import my.project.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/admin")
    public ResponseEntity<Admin> save(@Valid @RequestBody Admin admin) {
        return new ResponseEntity<>(adminService.save(admin), HttpStatus.OK);
    }
}
