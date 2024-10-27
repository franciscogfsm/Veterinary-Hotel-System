package hva.app.main;

import hva.HotelManager;
import hva.app.exceptions.FileOpenFailedException;
import hva.exceptions.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;

import java.io.FileNotFoundException;
import java.io.IOException;

class DoSaveFile extends Command<HotelManager> {
    DoSaveFile(HotelManager receiver) {
        super(Label.SAVE_FILE, receiver, r -> r.getHotel() != null);
    }

    @Override
    protected final void execute() {
    try {
        _receiver.save(); 
    } catch (MissingFileAssociationException e) {
        
        try {
            _receiver.saveAs(Form.requestString(Prompt.newSaveAs()));
        } catch (MissingFileAssociationException | IOException e1) {
            e1.printStackTrace(); 
        }
    } catch (FileNotFoundException e2) {
        e2.printStackTrace();
    } catch (IOException e3) {
        e3.printStackTrace();
    }
}

}
