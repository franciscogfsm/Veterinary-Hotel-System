package hva.app.search;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.*;
import hva.exceptions.CoreUnknownAnimalKeyException;


class DoShowMedicalActsOnAnimal extends Command<Hotel> {

    DoShowMedicalActsOnAnimal(Hotel receiver) {
        super(Label.MEDICAL_ACTS_ON_ANIMAL, receiver);
        addStringField("Animal Id",hva.app.animal.Prompt.animalKey());
    }

    @Override
    protected void execute() throws CommandException {
        try{
            _display.popup(_receiver.ShowMedicalActsOnAnimal(stringField("Animal Id")));
        } catch (CoreUnknownAnimalKeyException e ) {
            throw new UnknownAnimalKeyException(stringField("Animal Id"));
        }
        
    }

}
