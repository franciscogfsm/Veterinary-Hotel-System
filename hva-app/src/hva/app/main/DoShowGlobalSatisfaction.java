package hva.app.main;

import hva.HotelManager;
import hva.exceptions.CoreUnknownAnimalKeyException;
import hva.exceptions.CoreUnknownEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.*;

class DoShowGlobalSatisfaction extends Command<HotelManager> {
    DoShowGlobalSatisfaction(HotelManager receiver) {
        super(hva.app.main.Label.SHOW_GLOBAL_SATISFACTION, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _display.popup(_receiver.getGlobalSatisfaction());
        }catch(CoreUnknownEmployeeKeyException e){
            throw new UnknownEmployeeKeyException(e.getId());

        }catch(CoreUnknownAnimalKeyException e1){
            throw new UnknownAnimalKeyException(e1.getId());
        }
        
    }
}
