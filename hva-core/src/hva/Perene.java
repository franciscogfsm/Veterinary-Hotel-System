package hva;

import java.io.Serializable;
import java.util.List;

public class Perene extends Tree{
    TreeStateMachine _stateMachine;

    public Perene(String treeId, String treeName, 
        int treeAge, int difficulty,TreeStateMachine stateMachine) {
        super(treeId, treeName, treeAge, difficulty);
        _stateMachine = stateMachine;
    }
    @Override
    public void NextSeason(){
        _stateMachine.Advance_Season();
    }
    @Override
    public  int getCleaningEffort(){
        return _stateMachine.getCleaningEffort();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ÁRVORE|")
          .append(getTreeId()).append("|")
          .append(getTreeName()).append("|")
          .append(getTreeAge()).append("|")
          .append(getDifficulty()).append("|")
          .append("PERENE").append("|")
          .append(_stateMachine.getStateAction());
        
        return sb.toString();
    }

}
