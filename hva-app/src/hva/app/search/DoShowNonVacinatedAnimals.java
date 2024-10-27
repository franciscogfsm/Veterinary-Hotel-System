package hva.app.search;

import hva.Hotel;
import hva.exceptions.CoreUnknownAnimalKeyException;
import hva.exceptions.CoreUnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.*;

class DoShowAnimalsInHabitat extends Command<Hotel> {

    DoShowAnimalsInHabitat(Hotel receiver) {
        super(Label.ANIMALS_WITHOUT_VACCINATIONS, receiver);
        addStringField("Animal Id", hva.app.animal.Prompt.animalKey());
    }

    @Override
    protected void execute() throws CommandException {
        try {
            _display.popup(_receiver.showNonVacinatedAnimals(stringField("Animal Id")));
        } catch (CoreUnknownAnimalKeyException e) {
            throw new UnknownAnimalKeyException(stringField("Animal Id"));
        }

    }

}
