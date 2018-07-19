package blashaq.spring.recipe.test.integration.repositories;

import blashaq.spring.recipe.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {
    String teaspoon = "teaspoon";
    String dash = "dash";

    @Autowired
    UnitOfMeasureRepository uomRepo;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void findByUnit() {
        var teaspoonUom = uomRepo.findByUnit(teaspoon);
        var cupUom = uomRepo.findByUnit(dash);
        assertEquals(teaspoon, teaspoonUom.get().getUnit());
        assertEquals(dash, cupUom.get().getUnit());
    }
}