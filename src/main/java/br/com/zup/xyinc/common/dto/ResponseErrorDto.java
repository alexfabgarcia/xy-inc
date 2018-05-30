package br.com.zup.xyinc.common.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Data Transfer Object utilizado para retorno de erros.
 */
@Getter
public class ResponseErrorDto {

    private final Long timestamp;

    private final Integer status;

    private final String error;

    private final String message;

    public ResponseErrorDto(HttpStatus status, String message) {
        this.timestamp = Timestamp.valueOf(LocalDateTime.now()).getTime();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
    }
}