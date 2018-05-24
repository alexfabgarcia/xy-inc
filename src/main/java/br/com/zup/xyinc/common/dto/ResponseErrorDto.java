package br.com.zup.xyinc.common.dto;

import lombok.Value;

/**
 * Data Transfer Object utilizado para retorno de erros.
 */
@Value
public class ResponseErrorDto {

    private int status;

    private String mensagem;

}