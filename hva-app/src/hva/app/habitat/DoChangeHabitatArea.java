package hva.app.habitat;

import hva.Hotel;
import hva.app.exceptions.UnknownHabitatKeyException;
import hva.exceptions.CoreDuplicateTreeKeyException;
import hva.exceptions.CoreUnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;

class DoChangeHabitatArea extends Command<Hotel> {

    DoChangeHabitatArea(Hotel receiver) {
        super(Label.CHANGE_HABITAT_AREA, receiver);

    }

    @Override
    protected void execute() throws CommandException {
        Form form = new Form();
        form.addStringField("Habitat Id", Prompt.habitatKey());
        form.addIntegerField("New Habitat Area", Prompt.habitatArea());
        form.parse();

        try {
            _receiver.changeHabitatArea(
                    form.stringField("Habitat Id"),
                    form.integerField("New Habitat Area"));

        } catch (CoreUnknownHabitatKeyException e) {
            throw new UnknownHabitatKeyException(form.stringField("Habitat Id"));
        }
    }

}
