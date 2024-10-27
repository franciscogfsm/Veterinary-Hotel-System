package hva.app.vaccine;

import hva.Hotel;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.exceptions.CoreDuplicateVaccineKeyException;
import hva.exceptions.CoreUnknownSpeciesKeyException;
import hva.app.exceptions.DuplicateVaccineKeyException;
import hva.app.exceptions.UnknownSpeciesKeyException;

class DoRegisterVaccine extends Command<Hotel> {

    DoRegisterVaccine(Hotel receiver) {
        super(Label.REGISTER_VACCINE, receiver);

    }

    @Override
    protected final void execute() throws CommandException {
        Form form = new Form();
        form.addStringField("Vaccine Id", Prompt.vaccineKey());
        form.addStringField("Vaccine Name", Prompt.vaccineName());
        form.addStringField("Species List", Prompt.listOfSpeciesKeys());
        form.parse();

        String[] speciesList = form.stringField("Species List").split(",");

        try {
            _receiver.registerVaccine(
                    form.stringField("Vaccine Id"),
                    form.stringField("Vaccine Name"),
                    speciesList);

        } catch (CoreDuplicateVaccineKeyException e) {
            throw new DuplicateVaccineKeyException(form.stringField("Vaccine Id"));
        } catch (CoreUnknownSpeciesKeyException e1) {
            throw new UnknownSpeciesKeyException(e1.getId());
        }
    }
}
