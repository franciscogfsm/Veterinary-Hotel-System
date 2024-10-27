package hva.app.search;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.*;
import hva.exceptions.CoreUnknownVeterinarianKeyException;

class DoShowMedicalActsByVeterinarian extends Command<Hotel> {

    DoShowMedicalActsByVeterinarian(Hotel receiver) {
        super(Label.MEDICAL_ACTS_BY_VET, receiver);
        addStringField("Vet Id", hva.app.employee.Prompt.employeeKey());
    }

    @Override
    protected void execute() throws CommandException {
        try {
            _display.popup(_receiver.ShowMedicalActsByVeterinarian(stringField("Vet Id")));
        } catch (CoreUnknownVeterinarianKeyException e) {
            throw new UnknownVeterinarianKeyException(stringField("Vet Id"));
        }

    }

}
