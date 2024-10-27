package hva.app.animal;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;

class DoShowAllAnimals extends Command<Hotel> {

    DoShowAllAnimals(Hotel receiver) {
        super(Label.SHOW_ALL_ANIMALS, receiver);
    }

    @Override
    protected void execute() {
        _display.popup(_receiver.animals());
        _display.popup(_receiver.animalsNumber());
    }

}
