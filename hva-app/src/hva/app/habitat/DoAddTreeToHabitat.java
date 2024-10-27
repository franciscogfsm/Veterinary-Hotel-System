package hva.app.habitat;

import com.sun.source.tree.Tree;

import hva.Hotel;
import hva.exceptions.CoreDuplicateHabitatKeyException;
import hva.exceptions.CoreDuplicateTreeKeyException;
import hva.exceptions.CoreUnknownHabitatKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.*;

class DoAddTreeToHabitat extends Command<Hotel> {

    DoAddTreeToHabitat(Hotel receiver) {
        super(Label.ADD_TREE_TO_HABITAT, receiver);
        this.addStringField("Habitat Id", Prompt.habitatKey());
        this.addStringField("Tree Id", Prompt.treeKey());
        this.addStringField("Tree Name", Prompt.treeName());
        this.addIntegerField("Tree Age", Prompt.treeAge());
        this.addIntegerField("Tree Difficulty", Prompt.treeDifficulty());

    }

    @Override
    protected void execute() throws CommandException {
        String treeType;

        do {

            treeType = Form.requestString(Prompt.treeType());
        } while (!_receiver.ValidTreeType(treeType));

        try {
            _display.popup(_receiver.AppRegistryTree(
                    stringField("Habitat Id"),
                    stringField("Tree Id"),
                    stringField("Tree Name"),
                    integerField("Tree Age"),
                    integerField("Tree Difficulty"),
                    treeType));
            
        } catch (CoreUnknownHabitatKeyException e1) {
            throw new UnknownHabitatKeyException(stringField("Habitat Id"));
        } catch (CoreDuplicateTreeKeyException e) {
            throw new DuplicateTreeKeyException(stringField("Tree Id"));
        }
    }

}

// arranjar maneira de puxar o biological cycle para a app com uma funcao do
// core,
// dando apenas a estacao inicial a arvore
