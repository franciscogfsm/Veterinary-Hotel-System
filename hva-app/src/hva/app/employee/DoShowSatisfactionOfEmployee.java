package hva.app.employee;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import hva.exceptions.CoreUnknownEmployeeKeyException;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.*;

class DoShowSatisfactionOfEmployee extends Command<Hotel> {

    DoShowSatisfactionOfEmployee(Hotel receiver) {
        super(Label.SHOW_SATISFACTION_OF_EMPLOYEE, receiver);
        addStringField("Employee Id", Prompt.employeeKey());
    }

    @Override
    protected void execute() throws CommandException {
        String employeeId = stringField("Employee Id");

        try {
            _display.popup(_receiver.calculateSatisfactionOfEmployee(employeeId));
        } catch (CoreUnknownEmployeeKeyException e) {
            throw new UnknownEmployeeKeyException(employeeId);
        }
    }
}
