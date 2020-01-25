package blashaq.spring.recipe.test.unit.converters;

import blashaq.spring.recipe.commands.UnitOfMeasureCommand;
import blashaq.spring.recipe.converters.UomCommandToUom;
import blashaq.spring.recipe.entities.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = Long.valueOf(1L);

    UomCommandToUom converter;

    @Before
    public void setUp() throws Exception {
        converter = new UomCommandToUom();

    }

    @Test
    public void testNullParamter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(LONG_VALUE);
        command.setUnit(DESCRIPTION);

        //when
        UnitOfMeasure uom = converter.convert(command);

        //then
        assertNotNull(uom);
        assertEquals(LONG_VALUE, uom.getId());
        assertEquals(DESCRIPTION, uom.getUnit());

    }

}