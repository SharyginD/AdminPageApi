package my.project.service.impl;

import my.project.domain.dto.Admin;
import my.project.exception.customException.NonUniqueException;
import my.project.repository.AdminRepository;
import my.project.service.AdminService;
import my.project.util.mapStruct.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin save(Admin admin) {
        if (adminRepository.existsByLogin(admin.getLogin())) {
            throw new NonUniqueException("Admin with login " + admin.getLogin() + " already exists");
        } else {
            return AdminMapper.INSTANCE.toDTO(adminRepository.save(AdminMapper.INSTANCE.toEntity(admin)));
        }
    }
}
