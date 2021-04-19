package dm.api.controller;

import dm.api.repository.AdresRepository;
import dm.api.service.AdresService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AdresControllerTest {

    @Autowired
    private AdresService adresService;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private AdresRepository adresRepository;


}
