package hva.app.search;

import hva.Hotel;
import hva.exceptions.CoreUnknownAnimalKeyException;
import hva.exceptions.CoreUnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.*;

class DoShowNonVacinatedAnimals extends Command<Hotel> {

    DoShowNonVacinatedAnimals(Hotel receiver) {
        super(Label.ANIMALS_WITHOUT_VACCINATIONS, receiver);
    }

    @Override
    protected void execute() throws CommandException {
        _display.popup(_receiver.showNonVacinatedAnimals(stringField("Animal Id")));

    }

}
