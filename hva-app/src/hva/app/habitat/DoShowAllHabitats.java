package hva.app.habitat;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;

class DoShowAllHabitats extends Command<Hotel> {

    DoShowAllHabitats(Hotel receiver) {
        super(Label.SHOW_ALL_HABITATS, receiver);
    }

    @Override
    protected void execute() {
        _display.popup(_receiver.habitats());
    }
}
