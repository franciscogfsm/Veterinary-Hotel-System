package hva.app.search;

import hva.Hotel;
import hva.exceptions.CoreUnknownAnimalKeyException;
import hva.exceptions.CoreUnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.*;

class DoShowHabitatsNoTrees extends Command<Hotel> {

    DoShowHabitatsNoTrees(Hotel receiver) {
        super(Label.HABITATS_WITHOUT_TREES, receiver);
        
    }

    @Override
    protected void execute() throws CommandException {
        _display.popup(_receiver.showHabitatsNonTrees(stringField("Habitat Id")));
     

    }

}
