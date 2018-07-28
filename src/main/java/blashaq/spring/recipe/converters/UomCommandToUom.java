package blashaq.spring.recipe.converters;

import blashaq.spring.recipe.commands.UnitOfMeasureCommand;
import blashaq.spring.recipe.entities.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UomCommandToUom implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand uomCommand) {
        if (uomCommand == null)
            return null;
        var uom = new UnitOfMeasure();
        uom.setUnit(uomCommand.getUnit());
        uom.setId(uomCommand.getId());
        return uom;
    }
}
