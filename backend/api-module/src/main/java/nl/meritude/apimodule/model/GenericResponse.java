package nl.meritude.apimodule.model;

import lombok.Getter;

@Getter
public class GenericResponse {
    private int statuscode;
    private Object body;

    public GenericResponse(int statuscode, Object body) {
        this.statuscode = statuscode;
        this.body = body;
    }

    public GenericResponse(int statuscode) {
        this.statuscode = statuscode;
        this.body = null;
    }
}
