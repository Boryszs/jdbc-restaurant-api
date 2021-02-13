package dm.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dm.api.dto.response.DtoAdresResponse;
import dm.api.model.Adres;
import dm.api.repository.AdresRepository;
import dm.api.service.AdresService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


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

    @Test
    void sizeTest() throws Exception {
        when(adresRepository.count()).thenReturn(3056);
        assertEquals(3056,adresService.count());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/adres/all").accept(MediaType.APPLICATION_JSON)).andReturn();
        verify(adresRepository).findAll();
        ObjectMapper mapper = new ObjectMapper();
        List<DtoAdresResponse> actual = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<DtoAdresResponse>>() {});
        assertEquals(actual,adresService.findAll());
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/adres/size").accept(MediaType.APPLICATION_JSON)).andReturn();
        String size = mvcResult.getResponse().getContentAsString();
        assertEquals(Integer.parseInt(size),adresService.count());
        when(adresRepository.findById(1)).thenReturn(Optional.of(new Adres(1,"Restauracja",null,"1",null)));
    }
}