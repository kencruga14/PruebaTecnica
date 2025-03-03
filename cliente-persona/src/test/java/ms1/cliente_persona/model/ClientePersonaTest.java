package ms1.cliente_persona.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientePersonaTest {
    @Test
    public void testPersonaSettersAndGetters() {
        Persona persona = new Persona();
        persona.setPersonaid(1L);
        persona.setNombre("Kenny Cruz");
        persona.setGenero("Masculino");
        persona.setEdad(30L);
        persona.setIdentificacion("0918035676");
        persona.setDireccion("Conocoto");
        persona.setTelefono("0996714438");

        assertEquals(1L, persona.getPersonaid());
        assertEquals("Kenny Cruz", persona.getNombre());
        assertEquals("Masculino", persona.getGenero());
        assertEquals(30L, persona.getEdad());
        assertEquals("0918035676", persona.getIdentificacion());
        assertEquals("Conocoto", persona.getDireccion());
        assertEquals("0996714438", persona.getTelefono());
    }

    @Test
    public void testClienteSettersAndGetters() {
        Cliente cliente = new Cliente();
        cliente.setPersonaid(1L);
        cliente.setNombre("Kenny Cruz");
        cliente.setContrasena("123456");
        cliente.setEstado(true);

        assertEquals(1L, cliente.getPersonaid());
        assertEquals("Kenny Cruz", cliente.getNombre());
        assertEquals("123456", cliente.getContrasena());
        assertTrue(cliente.getEstado());
    }

}
