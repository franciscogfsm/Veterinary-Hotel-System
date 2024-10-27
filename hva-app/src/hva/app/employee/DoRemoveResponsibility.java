package hva.app.employee;

import hva.Hotel;
import hva.exceptions.CoreNoResponsibilityException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import hva.app.exceptions.*;

class DoRemoveResponsibility extends Command<Hotel> {

    DoRemoveResponsibility(Hotel receiver) {
        super(Label.REMOVE_RESPONSABILITY, receiver);

    }

    @Override
    protected void execute() throws CommandException {
        Form form = new Form();
        form.addStringField("Employee Id", Prompt.employeeKey());
        form.addStringField("Responsibility Id", Prompt.responsibilityKey());
        form.parse();
        try {
            _receiver.RemoveResponsibility(form.stringField("Employee Id"), form.stringField("Responsibility Id"));
        } catch (CoreNoResponsibilityException e) {
            throw new NoResponsibilityException(form.stringField("Employee Id"), form.stringField("Responsibility Id"));
        }
    }

}
