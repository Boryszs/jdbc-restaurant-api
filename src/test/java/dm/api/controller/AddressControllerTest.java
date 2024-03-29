package dm.api.controller;

import dm.api.repository.AddressRepository;
import dm.api.service.AddressService;
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
class AddressControllerTest {

    @Autowired
    private AddressService addressService;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private AddressRepository addressRepository;


}
