package br.com.zup.xyinc.presentation.endpoint;

import br.com.zup.xyinc.common.entity.Poi;
import br.com.zup.xyinc.repository.PoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Endpoint para acesso a funcionalidades de pontos de interesse (POIs).
 */
@RestController
@RequestMapping("/pois")
public class PoiEndpoint {

    private final PoiRepository poiRepository;

    @Autowired
    public PoiEndpoint(PoiRepository poiRepository) {
        this.poiRepository = poiRepository;
    }

    /**
     * Realiza a listagem de POIs.
     * @return Lista de POIs.
     */
    @GetMapping
    public ResponseEntity<List<Poi>> list() {
        return new ResponseEntity<>(poiRepository.findAll(), HttpStatus.OK);
    }

}
