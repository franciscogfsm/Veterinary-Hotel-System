package hva.app.main;

import java.io.IOException;

import hva.HotelManager;
import hva.exceptions.*;
import hva.app.exceptions.*;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

class DoOpenFile extends Command<HotelManager> {
    DoOpenFile(HotelManager receiver) {
        super(Label.OPEN_FILE, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        
        try {
            if (_receiver.getUnsavedChanges()&& Form.confirm(Prompt.saveBeforeExit())) {
                new DoSaveFile(_receiver).execute();
            }
            Form form = new Form();
            form.addStringField("filename", Prompt.openFile());
            form.parse();
            String filename = form.stringField("filename");
            _receiver.load(filename);
        } catch (UnavailableFileException e) {
            throw new FileOpenFailedException(e);
        }
    }
}
