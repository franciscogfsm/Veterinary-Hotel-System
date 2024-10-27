package hva.app.habitat;

import hva.Hotel;
import hva.exceptions.CoreDuplicateHabitatKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.*;

class DoRegisterHabitat extends Command<Hotel> {

    DoRegisterHabitat(Hotel receiver) {
        super(Label.REGISTER_HABITAT, receiver);

    }

    @Override
    protected void execute() throws CommandException {
        Form form = new Form();
        form.addStringField("Habitat Id", Prompt.habitatKey());
        form.addStringField("Habitat Name", Prompt.habitatName());
        form.addIntegerField("Habitat Area", Prompt.habitatArea());
        form.parse();

        try {
            _receiver.registerHabitat(
                    form.stringField("Habitat Id"),
                    form.stringField("Habitat Name"),
                    form.integerField("Habitat Area"),
                    new String[0]);

        } catch (CoreDuplicateHabitatKeyException e) {
            throw new DuplicateHabitatKeyException(form.stringField("Habitat Id"));
        }
    }
}
