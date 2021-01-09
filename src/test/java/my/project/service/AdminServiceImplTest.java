package my.project.service;

import my.project.domain.dto.Admin;
import my.project.domain.entity.AdminEntity;
import my.project.exception.customException.NonUniqueException;
import my.project.repository.AdminRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceImplTest {

    @Autowired
    private AdminService adminService;

    @MockBean
    private AdminRepository adminRepository;

    private Admin admin;
    private AdminEntity adminEntity;

    @Before
    public void init() {
        admin = new Admin(1, "login", "login");
        adminEntity = new AdminEntity(1, "login", "login");
    }

    @Test
    public void saveAdminTest() {

        Mockito.when(adminRepository.existsByLogin(Mockito.anyString())).thenReturn(false);
        Mockito.when(adminRepository.save(Mockito.eq(adminEntity))).thenReturn(adminEntity);

        Assert.assertEquals(admin, adminService.save(admin));
    }

    @Test(expected = NonUniqueException.class)
    public void whenAdminExists_ThenThrowNonUniqueException() {

        Mockito.when(adminRepository.existsByLogin(Mockito.anyString())).thenReturn(true);
        adminService.save(admin);
    }
}
