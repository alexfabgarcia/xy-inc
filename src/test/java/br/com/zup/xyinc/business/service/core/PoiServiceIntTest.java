package br.com.zup.xyinc.business.service.core;

import br.com.zup.xyinc.business.service.PoiService;
import br.com.zup.xyinc.common.dto.PoiDto;
import br.com.zup.xyinc.common.entity.Poi;
import br.com.zup.xyinc.repository.PoiRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Teste de integração dos serviços de POI, garantindo a cobertura a partir da comada de serviço.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PoiServiceIntTest {

    @Autowired
    private PoiService poiService;

    @Autowired
    private PoiRepository poiRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        mongoTemplate.remove(Poi.class).findAndRemove();
        poiRepository.initSampleData();
    }

    @Test
    public void shouldListPoisFromInitialization() {
        List<PoiDto> pois = poiService.list();

        assertNotNull(pois);
        assertEquals("A lista deve conter 7 elementos", 7, pois.size());

        PoiDto fisrt = pois.get(0);
        assertEquals("O primeiro ponto deve ser a Lanchonete.", "Lanchonete", fisrt.getName());
        assertEquals("A coordenada x da Lanchonete deve ser 27.", 27, fisrt.getX().intValue());
        assertEquals("A coordenada x da Lanchonete deve ser 12.", 12, fisrt.getY().intValue());

        PoiDto last = pois.get(6);
        assertEquals("O último ponto deve ser a Churrascaria.", "Churrascaria", last.getName());
        assertEquals("A coordenada x da Churrascaria deve ser 28.", 28, last.getX().intValue());
        assertEquals("A coordenada x da Churrascaria deve ser 2.", 2, last.getY().intValue());
    }

    @Test
    public void shouldListNearPoisWhenNearReferenceIsProvided() {
        List<PoiDto> nearPois = poiService.listNear(20, 10, 10);

        assertNotNull(nearPois);
        assertEquals("A lista deve conter 4 POIs.", 4, nearPois.size());
        assertEquals("O primeiro POI deve ser a Lanchonete.", "Lanchonete", nearPois.get(0).getName());
        assertEquals("O primeiro POI deve ser a Joalheria.", "Joalheria", nearPois.get(1).getName());
        assertEquals("O primeiro POI deve ser a Pub.", "Pub", nearPois.get(2).getName());
        assertEquals("O primeiro POI deve ser a Supermercado.", "Supermercado", nearPois.get(3).getName());
    }

    @Test
    public void shouldReturnOneNearPoiWhenCloseReferenceIsProvided() {
        List<PoiDto> nearPois = poiService.listNear(20, 20, 2);

        assertNotNull(nearPois);
        assertEquals("A lista deve conter 1 POI.", 1, nearPois.size());

        PoiDto uniquePoi = nearPois.get(0);
        assertEquals("O único POI próximo deve ser a Floricultura.", "Floricultura", uniquePoi.getName());
        assertEquals("A coordenada x da Floricultura deve ser 19.", 19, uniquePoi.getX().intValue());
        assertEquals("A coordenada x da Floricultura deve ser 21.", 21, uniquePoi.getY().intValue());
    }

    @Test
    public void shouldReturnNonePoiWhenDistantReferenceIsProvided() {
        List<PoiDto> nearPois = poiService.listNear(30, 30, 2);

        assertNotNull(nearPois);
        assertTrue("A lista deve estar vazia.", nearPois.isEmpty());
    }

    @Test
    public void shouldCreatePoiWhenValidObjectIsProvided() {
        List<PoiDto> previousPois = poiService.list();
        assertNotNull(previousPois);
        assertEquals("A lista anterior deve conter 7 elementos", 7, previousPois.size());

        PoiDto stadium = new PoiDto("Estádio", 14, 15);
        stadium = poiService.save(stadium);

        List<PoiDto> newPoiList = poiService.list();
        assertNotNull(newPoiList);
        assertEquals("A nova lista deve conter 8 elementos", 8, newPoiList.size());
        assertEquals("O último elemento deve ser o Estádio.", stadium, newPoiList.get(7));
    }

    @Test
    public void shouldFailWhenCreatingPoiWithoutName() {
        expectedException.expect(ConstraintViolationException.class);
        expectedException.expectMessage("name: xyinc.poi.name.mandatory");

        PoiDto stadium = new PoiDto("", 14, 15);
        poiService.save(stadium);
        fail("O teste deveria ter falhado dada a falta do nome do POI");
    }
}
