package hva.app.animal;

import hva.Hotel;
import hva.exceptions.CoreUnknownAnimalKeyException;
import hva.exceptions.CoreUnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import hva.app.exceptions.*;

class DoTransferToHabitat extends Command<Hotel> {

    DoTransferToHabitat(Hotel hotel) {
        super(Label.TRANSFER_ANIMAL_TO_HABITAT, hotel);
        addStringField("Animal Id", Prompt.animalKey());
        addStringField("Habitat Id", hva.app.habitat.Prompt.habitatKey());

    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _receiver.transferAnimalToHabitat(stringField("Animal Id"),
                    stringField("Habitat Id"));
        } catch (CoreUnknownAnimalKeyException e) {
            throw new UnknownAnimalKeyException(stringField("Animal Id"));

        } catch (CoreUnknownHabitatKeyException e1) {
            throw new UnknownHabitatKeyException(stringField("Habitat Id"));
        }
    }
}
