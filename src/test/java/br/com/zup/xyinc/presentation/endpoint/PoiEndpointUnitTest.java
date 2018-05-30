package br.com.zup.xyinc.presentation.endpoint;

import br.com.zup.xyinc.business.service.PoiService;
import br.com.zup.xyinc.common.dto.PoiDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Teste unitário de {@link PoiEndpoint} garantindo binding de objetos JSON e verificação de mensagens de validação.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PoiEndpoint.class)
public class PoiEndpointUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PoiService poiService;

    @Test
    public void shouldListPoisWhenPoisFound() throws Exception {

        // Mock
        when(poiService.list()).thenReturn(Arrays.asList(
                new PoiDto("Lanchonete", 10, 15),
                new PoiDto("Pub", 16, 20)
        ));

        // Call and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/pois"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Lanchonete")))
                .andExpect(jsonPath("$[0].x", is(10)))
                .andExpect(jsonPath("$[0].y", is(15)))
                .andExpect(jsonPath("$[1].name", is("Pub")))
                .andExpect(jsonPath("$[1].x", is(16)))
                .andExpect(jsonPath("$[1].y", is(20)));

        // Verify
        verify(poiService, times(1)).list();
        verifyNoMoreInteractions(poiService);
    }

    @Test
    public void shouldListNoneWhenPoisNotFound() throws Exception {

        // Mock
        when(poiService.list()).thenReturn(Collections.emptyList());

        // Call and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/pois"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(0)));

        // Verify
        verify(poiService, times(1)).list();
        verifyNoMoreInteractions(poiService);
    }

    @Test
    public void shouldListNearPoisWhenNearPoisFound() throws Exception {

        // Mock
        when(poiService.listNear(anyInt(), anyInt(), anyInt())).thenReturn(Arrays.asList(
                new PoiDto("Lanchonete", 10, 15),
                new PoiDto("Pub", 16, 20)
        ));

        // Call and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/pois/near")
                .param("x", "0")
                .param("y", "0")
                .param("radius", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)));

        // Verify
        verify(poiService, times(1)).listNear(anyInt(), anyInt(), anyInt());
        verifyNoMoreInteractions(poiService);
    }

    @Test
    public void shouldFailListNearWhenXIsMissing() throws Exception {

        // Call and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/pois/near")
                .param("y", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.error", is("Bad Request")))
                .andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.value())))
                .andExpect(jsonPath("$.message", containsString("x")));

        // Verify
        verifyNoMoreInteractions(poiService);
    }

    @Test
    public void shouldFailListNearWhenYIsMissing() throws Exception {

        // Call and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/pois/near")
                .param("x", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.error", is("Bad Request")))
                .andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.value())))
                .andExpect(jsonPath("$.message", containsString("y")));

        // Verify
        verifyNoMoreInteractions(poiService);
    }

    @Test
    public void shouldFailListNearWhenRadiusIsMissing() throws Exception {

        // Call and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/pois/near")
                .param("x", "0")
                .param("y", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.error", is("Bad Request")))
                .andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.value())))
                .andExpect(jsonPath("$.message", containsString("radius")));

        // Verify
        verifyNoMoreInteractions(poiService);
    }

    @Test
    public void shouldCreatePoiWhenValidPoiSubmitted() throws Exception {
        PoiDto poiToSave = new PoiDto("Shopping", 30, 18);

        // Mock
        when(poiService.save(any(PoiDto.class))).thenReturn(poiToSave);

        // Call and assert
        mockMvc.perform(MockMvcRequestBuilders.post("/pois")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(poiToSave)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.name", is(poiToSave.getName())))
                .andExpect(jsonPath("$.x", is(poiToSave.getX())))
                .andExpect(jsonPath("$.y", is(poiToSave.getY())));

        // Verify
        verify(poiService, times(1)).save(any(PoiDto.class));
        verifyNoMoreInteractions(poiService);
    }

    @Test
    public void shouldFailWhenCreatingPoiWithMissingProperties() throws Exception {
        PoiDto poiToSave = new PoiDto("", null, null);

        // Mock
        when(poiService.save(any(PoiDto.class))).thenReturn(poiToSave);

        // Call and assert
        mockMvc.perform(MockMvcRequestBuilders.post("/pois")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(poiToSave)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.value())))
                .andExpect(jsonPath("$.error", is("Bad Request")))
                .andExpect(jsonPath("$.message",
                        containsString("O nome do ponto de interesse é obrigatório.")))
                .andExpect(jsonPath("$.message",
                        containsString("A coordenada x do ponto de interesse é obrigatória.")))
                .andExpect(jsonPath("$.message",
                        containsString("A coordenada y do ponto de interesse é obrigatória.")));

        // Verify
        verifyNoMoreInteractions(poiService);
    }

    @Test
    public void shouldFailWhenCreatingPoiWithNegativeCoordinates() throws Exception {
        PoiDto poiToSave = new PoiDto("Cafeteria", -10, -18);

        // Mock
        when(poiService.save(any(PoiDto.class))).thenReturn(poiToSave);

        // Call and assert
        mockMvc.perform(MockMvcRequestBuilders.post("/pois")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(poiToSave)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.value())))
                .andExpect(jsonPath("$.error", is("Bad Request")))
                .andExpect(jsonPath("$.message",
                        containsString("A coordenada x do ponto de interesse não pode ser negativa.")))
                .andExpect(jsonPath("$.message",
                        containsString("A coordenada y do ponto de interesse não pode ser negativa.")));

        // Verify
        verifyNoMoreInteractions(poiService);
    }

}
