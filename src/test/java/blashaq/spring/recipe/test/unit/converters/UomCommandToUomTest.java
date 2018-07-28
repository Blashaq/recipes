package blashaq.spring.recipe.test.unit.converters;

import blashaq.spring.recipe.converters.UomCommandToUom;
import blashaq.spring.recipe.entities.UnitOfMeasure;
import blashaq.spring.recipe.commands.UnitOfMeasureCommand;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class UomCommandToUomTest {

    private UnitOfMeasureCommand uomCommand;
    private UomCommandToUom uomConverter;
    private long id = 1;
    private String unit = "unit";

    @Before
    public void setup() {
        uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(id);
        uomCommand.setUnit(unit);
        uomConverter = new UomCommandToUom();
    }

    @Test
    public void correctDataAfterConvert() {
        UnitOfMeasure uom = uomConverter.convert(uomCommand);
        assertEquals(uom.getUnit(), uomCommand.getUnit());
        assertEquals(uom.getId(), uomCommand.getId());
    }

    @Test
    public void nullWhenNullCommand() {
        var uom = uomConverter.convert(null);
        assertNull(uom);
    }
}
