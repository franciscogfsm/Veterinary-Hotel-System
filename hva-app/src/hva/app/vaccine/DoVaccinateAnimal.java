package hva.app.vaccine;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import hva.app.exceptions.*;
import hva.exceptions.*;

class DoVaccinateAnimal extends Command<Hotel> {

    DoVaccinateAnimal(Hotel receiver) {
        super(Label.VACCINATE_ANIMAL, receiver);
        addStringField("Vaccine Id", Prompt.vaccineKey());
        addStringField("Veterinarian Id", Prompt.veterinarianKey());
        addStringField("Animal Id", hva.app.animal.Prompt.animalKey());

    }

    @Override
    protected final void execute() throws CommandException {
        String speciesId = _receiver.getAnimalSpecieId(stringField("Animal Id"));
        try {
            if (!_receiver.vaccinate(stringField("Vaccine Id"), stringField("Veterinarian Id"),
                    stringField("Animal Id"))) {
                _display.popup(Message.wrongVaccine(stringField("Vaccine Id"), stringField("Animal Id")));
            }
        } catch (CoreUnknownVeterinarianKeyException e) {
            throw new UnknownVeterinarianKeyException(stringField("Veterinarian Id"));
        } catch (CoreVeterinarianNotAuthorizedException e1) {
            throw new VeterinarianNotAuthorizedException(stringField("Veterinarian Id"), speciesId);
        } catch (CoreUnknownVaccineKeyException e2) {
            throw new UnknownVaccineKeyException(stringField("Vaccine Id"));
        }

    }

}
