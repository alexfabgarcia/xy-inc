package br.com.zup.xyinc.presentation.endpoint;

import br.com.zup.xyinc.business.service.PoiService;
import br.com.zup.xyinc.common.dto.PoiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Endpoint para acesso a funcionalidades de pontos de interesse (POIs).
 */
@RestController
@RequestMapping("/pois")
public class PoiEndpoint {

    private final PoiService poiService;

    @Autowired
    public PoiEndpoint(PoiService poiService) {
        this.poiService = poiService;
    }

    /**
     * Realiza a listagem de POIs.
     * @return Lista de POIs.
     */
    @GetMapping
    public ResponseEntity<List<PoiDto>> list() {
        return new ResponseEntity<>(poiService.list(), HttpStatus.OK);
    }

    /**
     * Realiza a listagem de POIs.
     * @return Lista de POIs.
     */
    @GetMapping("/near")
    public ResponseEntity<List<PoiDto>> listNear(@RequestParam("x") int x, @RequestParam("y") int y,
                                              @RequestParam("radius") int radius) {
        return new ResponseEntity<>(poiService.listNear(x, y, radius), HttpStatus.OK);
    }

    /**
     * Realiza a criação de um ponto de interesse.
     * @param dto O DTO de um ponto de interesse.
     * @return DTo de um ponto de interesse criado.
     */
    @PostMapping
    public ResponseEntity<PoiDto> create(@RequestBody @Valid PoiDto dto) {
        return new ResponseEntity<>(poiService.save(dto), HttpStatus.CREATED);
    }

}
