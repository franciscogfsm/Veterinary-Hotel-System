package hva;

import java.io.Serializable;
import java.util.List;

public abstract class Tree implements Serializable {
    /*Just an example change */
    private String _treeId;
    private String _treeName;
    private int _treeAge;
    private int _difficulty;
    private String _biologicalCicle;
    private int _seasonChangeCounter = 0;
    

    public Tree(String treeId, String treeName, int treeAge,
            int difficulty) {
        _treeId = treeId;
        _treeName = treeName;
        _treeAge = treeAge;
        _difficulty = difficulty;
    }

    public abstract void NextSeason();
    public abstract int getCleaningEffort();

    public void CheckForAgeChange() {
        _seasonChangeCounter++;
        if (_seasonChangeCounter == 4) {
            _treeAge++;
            _seasonChangeCounter = 0;
        }
    }

    
    public int getDifficulty() {
        return _difficulty;
    }
    public void setDifficulty(int difficulty) {
        _difficulty = difficulty;
    }
    
    public String getTreeId() {
        return _treeId;
    }
    public void setTreeId(String treeId) {
        _treeId = treeId;
    }

    public String getTreeName() {
        return _treeName;
    }
    public void setTreeName(String treeName) {
        _treeName = treeName;
    }
    public int getTreeAge() {
        return _treeAge;
    }
    public void setTreeAge(int treeAge) {
        _treeAge = treeAge;
    }
    public String getBiologicalCicle(String type) {
        return _biologicalCicle;
    }
    public void setBiologicalCicle(String biologicalCicle) {
        _biologicalCicle = biologicalCicle;
    }

    @Override
    public abstract String toString();
    
    
    /*As árvores são identificadas por uma chave única (cadeia de caracteres arbitrária definida na altura da criação). Cada árvore tem ainda nome (cadeia de caracteres; não única) e idade em anos (número inteiro). As árvores podem ser de folha caduca ou perene e são caracterizadas pela dificuldade base de limpeza (número inteiro) que induzem no habitat onde estão implantadas (definido no momento da introdução da árvore no habitat).

A vida das árvores segue o ciclo definido pelas estações do ano. Assim, por exemplo, as árvores de folha caduca perdem as folhas principalmente durante o Outono, ficando sem folhas no Inverno. As árvores de folha perene perdem algumas folhas durante todas as estações, mas mais durante o Inverno.

O esforço total de limpeza de uma árvore é assim determinado pelo produto de três factores: o primeiro corresponde à dificuldade base de limpeza da árvore; o segundo depende da estação do ano e do tipo de árvore, tal como indicado na tabela seguinte (função esforço_sazonal); e o terceiro corresponde a um factor que aumenta com a idade da árvore (ver abaixo).  */
}
