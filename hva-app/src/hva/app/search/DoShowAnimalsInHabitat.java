package hva.app.search;

import hva.Hotel;
import hva.exceptions.CoreUnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.*;


class DoShowAnimalsInHabitat extends Command<Hotel> {

    DoShowAnimalsInHabitat(Hotel receiver) {
        super(Label.ANIMALS_IN_HABITAT, receiver);
        addStringField("Habitat Id", hva.app.habitat.Prompt.habitatKey());
    }

    @Override
    protected void execute() throws CommandException {
        try{
            _display.popup(_receiver.showAnimalsInHabitat(stringField("Habitat Id")));
        } catch (CoreUnknownHabitatKeyException e ) {
            throw new UnknownHabitatKeyException(stringField("Habitat Id"));
        }
        
    }

}
