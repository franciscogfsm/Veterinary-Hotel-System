package hva.app.habitat;

import hva.Hotel;
import hva.exceptions.CoreUnknownHabitatKeyException;
import hva.exceptions.CoreUnknownSpeciesKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import hva.app.exceptions.*;

class DoChangeHabitatInfluence extends Command<Hotel> {

    DoChangeHabitatInfluence(Hotel receiver) {
        super(Label.CHANGE_HABITAT_INFLUENCE, receiver);
        this.addStringField("Habitat Id", Prompt.habitatKey());
        this.addStringField("Specie Id", hva.app.animal.Prompt.speciesKey());
    }

    @Override
    protected void execute() throws CommandException {
        String influence;
        do {

            influence = Form.requestString(Prompt.habitatInfluence());
        } while (!_receiver.ValidHabitatInfluence(influence));

        try {
            _receiver.changeHabitatInfluence(
                    stringField("Habitat Id"),
                    stringField("Specie Id"),
                    influence);
        } catch (CoreUnknownHabitatKeyException e) {
            throw new UnknownHabitatKeyException(stringField("Habitat Id"));
        } catch (CoreUnknownSpeciesKeyException e1) {
            throw new UnknownSpeciesKeyException(stringField("Specie Id"));
        }
    }

}
