package hva.app.employee;

import hva.Hotel;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.exceptions.CoreDuplicateEmployeeKeyException;
import hva.app.exceptions.DuplicateEmployeeKeyException;

class DoRegisterEmployee extends Command<Hotel> {

    DoRegisterEmployee(Hotel receiver) {
        super(Label.REGISTER_EMPLOYEE, receiver);
        this.addStringField("Employee Id", Prompt.employeeKey());
        this.addStringField("Employee Name", Prompt.employeeName());
    }

    @Override
    protected void execute() throws CommandException {
        String employeeType;
        do {

            employeeType = Form.requestString(Prompt.employeeType());

        } while (!_receiver.ValidEmployeeType(employeeType));

        try {
            _receiver.registerEmployee(
                    stringField("Employee Id"),
                    stringField("Employee Name"),
                    employeeType);

        } catch (CoreDuplicateEmployeeKeyException e) {
            throw new DuplicateEmployeeKeyException(stringField("Employee Id"));
        }
    }

}
