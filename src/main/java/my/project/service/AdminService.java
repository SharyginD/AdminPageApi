package my.project.service;

import my.project.domain.dto.Admin;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    Admin save(Admin admin);
}
