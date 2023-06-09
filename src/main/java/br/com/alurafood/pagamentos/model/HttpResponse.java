package br.com.alurafood.pagamentos.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HttpResponse {

    private int status;
    private String message;
    private List<String> erros;
}
