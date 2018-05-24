package br.com.zup.xyinc.presentation.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Endpoint para acesso a funcionalidades de pontos de interesse (POIs).
 */
@RestController
@RequestMapping("/pois")
public class PoiEndpoint {

    /**
     * Realiza a listagem de POIs.
     * @return Lista de POIs.
     */
    @GetMapping
    public ResponseEntity<List<String>> list() {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

}
