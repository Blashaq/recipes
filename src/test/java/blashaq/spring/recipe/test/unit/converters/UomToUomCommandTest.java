package blashaq.spring.recipe.test.unit.converters;

import blashaq.spring.recipe.commands.UnitOfMeasureCommand;
import blashaq.spring.recipe.converters.UomToUomCommand;
import blashaq.spring.recipe.entities.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UomToUomCommandTest {

    private static final String DESCRIPTION = "description";
    private static final Long LONG_VALUE = Long.valueOf(1L);

    UomToUomCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new UomToUomCommand();
    }

    @Test
    public void testNullObjectConvert() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObj() throws Exception {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert() throws Exception {
        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(LONG_VALUE);
        uom.setUnit(DESCRIPTION);
        //when
        UnitOfMeasureCommand uomc = converter.convert(uom);

        //then
        assertEquals(LONG_VALUE, uomc.getId());
        assertEquals(DESCRIPTION, uomc.getUnit());
    }

}