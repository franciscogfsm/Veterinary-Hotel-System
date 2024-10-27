package hva.app.animal;

import hva.Hotel;
import hva.exceptions.CoreUnknownAnimalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import hva.app.exceptions.*;

class DoShowSatisfactionOfAnimal extends Command<Hotel> {

    DoShowSatisfactionOfAnimal(Hotel receiver) {
        super(Label.SHOW_SATISFACTION_OF_ANIMAL, receiver);

    }

    @Override
    protected final void execute() throws CommandException {
        Form form = new Form();
        form.addStringField("Animal Id", Prompt.animalKey());
        form.parse();
        try {
            _display.popup(_receiver.CalculateSatisfaction(form.stringField("Animal Id")));
        } catch (CoreUnknownAnimalKeyException e) {
            throw new UnknownAnimalKeyException(form.stringField("Animal Id"));

        }

    }

}
