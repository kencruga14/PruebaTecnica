package ms1.cliente_persona.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ms1.cliente_persona.constant.GlobalConstant;
import ms1.cliente_persona.dto.ClienteDto;
import ms1.cliente_persona.service.IClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {
    @Mock
    private IClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
        objectMapper = new ObjectMapper();
    }

    // Pruebas para getCuentas()
    @Test
    public void getCuentas() throws Exception {
        List<ClienteDto> clientes = Arrays.asList(new ClienteDto(), new ClienteDto());
        when(clienteService.getAll()).thenReturn(clientes);

        mockMvc.perform(get(GlobalConstant.V1_API_VERSION+"/clientes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    // Pruebas para saveCuenta()
    @Test
    public void SaveCuenta() throws Exception {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setNombre("Kenny Cruz");
        clienteDto.setGenero("Masculino");
        clienteDto.setEdad(30L);
        clienteDto.setIdentificacion("091835676");
        clienteDto.setDireccion("Conocoto");
        clienteDto.setTelefono("0918035676");
        clienteDto.setContrasena("123456");
        clienteDto.setEstado(true);
        when(clienteService.save(any(ClienteDto.class))).thenReturn(clienteDto);
        mockMvc.perform(post(GlobalConstant.V1_API_VERSION+"/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

}
