package hva.app.animal;

import hva.Hotel;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.exceptions.CoreUnknownSpeciesKeyException;
import hva.exceptions.CoreDuplicateSpeciesKeyException;
import hva.exceptions.CoreUnknownHabitatKeyException;
import hva.exceptions.CoreDuplicateAnimalKeyException;
import hva.app.exceptions.*;

class DoRegisterAnimal extends Command<Hotel> {

    DoRegisterAnimal(Hotel receiver) {
        super(Label.REGISTER_ANIMAL, receiver);
        this.addStringField("Animal Id", Prompt.animalKey());
        this.addStringField("Animal Name", Prompt.animalName());
        this.addStringField("Species Id", Prompt.speciesKey());
        this.addStringField("Habitat Id", hva.app.habitat.Prompt.habitatKey());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _receiver.registerAnimal(
                    stringField("Animal Id"),
                    stringField("Animal Name"),
                    stringField("Species Id"),
                    stringField("Habitat Id"));

        } catch (CoreDuplicateAnimalKeyException e) {
            throw new DuplicateAnimalKeyException(stringField("Animal Id"));
        } catch (CoreUnknownSpeciesKeyException e) {
            Form form = new Form();
            form.addStringField("Species Name", Prompt.speciesName());
            form.parse();
            try {
                _receiver.registerSpecies(
                        stringField("Species Id"),
                        form.stringField("Species Name"));
                _receiver.registerAnimal(
                    stringField("Animal Id"),
                    stringField("Animal Name"),
                    stringField("Species Id"),
                    stringField("Habitat Id"));
            } catch (CoreDuplicateSpeciesKeyException e1) {
                e1.printStackTrace();
            } catch (CoreUnknownHabitatKeyException e1) {
                throw new UnknownHabitatKeyException(stringField("Habitat Id"));
            } catch (CoreDuplicateAnimalKeyException e2) {
                throw new DuplicateAnimalKeyException(stringField("Animal Id"));
            } catch (CoreUnknownSpeciesKeyException e3) {}
        } catch (CoreUnknownHabitatKeyException e1) {
            throw new UnknownHabitatKeyException(stringField("Habitat Id"));
        }
    }
}