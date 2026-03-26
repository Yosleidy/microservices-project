package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {

    @Test
    void testUserDtoConstructorAndGetters() {
        // Crear un UserDto usando el constructor con todos los campos
        UserDto user = new UserDto(1L, "Juan", "juan@example.com");

        // Verificar que los campos se hayan seteado correctamente
        assertEquals(1L, user.getId());
        assertEquals("Juan", user.getName());
        assertEquals("juan@example.com", user.getEmail());

        // Cambiar el nombre usando el setter y verificar
        user.setName("Carlos");
        assertEquals("Carlos", user.getName());
    }
}