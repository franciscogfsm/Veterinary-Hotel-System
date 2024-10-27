package hva.app.habitat;

import hva.Hotel;
import hva.app.exceptions.UnknownHabitatKeyException;
import hva.exceptions.CoreUnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;

class DoShowAllTreesInHabitat extends Command<Hotel> {

    DoShowAllTreesInHabitat(Hotel receiver) {
        super(Label.SHOW_TREES_IN_HABITAT, receiver);
    }

    @Override
    protected void execute() throws CommandException {
        Form form = new Form();
        form.addStringField("Habitat Id", Prompt.habitatKey());
        form.parse();
        try {
            _display.popup(_receiver.treesInHabitat(form.stringField("Habitat Id")));
        } catch (CoreUnknownHabitatKeyException e) {
            throw new UnknownHabitatKeyException(form.stringField("Habitat Id"));

        }

    }

}
