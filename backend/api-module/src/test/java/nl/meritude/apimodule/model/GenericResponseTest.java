package nl.meritude.apimodule.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GenericResponseTest {

    @Test
    void getStatuscodeTest() {
        int expected = 1;
        GenericResponse rm = new GenericResponse(expected, null);
        int actual = rm.getStatuscode();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getBodyTest() {
        int statuscode = 1;
        String expected = "Test";
        GenericResponse rm = new GenericResponse(statuscode, expected);
        Object actual = rm.getBody();

        Assertions.assertEquals(expected, actual);
    }
}