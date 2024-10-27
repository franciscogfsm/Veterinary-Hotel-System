package hva;
import hva.Hotel;

enum Season {
    INVERNO, PRIMAVERA, VERAO, OUTONO
}

enum TreeType {
    CADUCA, PERENE
}

class TreeStateMachine {
    private TreeType _treeType;
    Season _currentSeason;

    public TreeStateMachine(Season currentSeason) {
        _currentSeason = currentSeason;
    }
    
    public Season getSeason() {
        return _currentSeason;
    }

    public void setTreeType(TreeType treeType) {
        _treeType = treeType;
    }
    
    public String getStateAction() {
        switch (_treeType) {
            case CADUCA:
                return getCaducaAction();
            case PERENE:
                return getPereneAction();
            default:
                return "";
        }
    }
    public String getCaducaAction() {
        switch (_currentSeason) {
            case INVERNO:
                return "SEMFOLHAS";
            case PRIMAVERA:
                return "GERARFOLHAS";
            case VERAO:
                return "COMFOLHAS";
            case OUTONO:
                return "LARGARFOLHAS";
            default:
                return "";
        }
    }
    public String getPereneAction() {
        switch (_currentSeason) {
            case INVERNO:
                return "LARGARFOLHAS";
            case PRIMAVERA:
                return "GERARFOLHAS";
            case VERAO:
                return "COMFOLHAS";
            case OUTONO:
                return "COMFOLHAS";
            default:
                return "";
        }
    }
    public void Advance_Season() {
        switch (_currentSeason) {
            case INVERNO:
                _currentSeason = Season.PRIMAVERA;
                break;
            case PRIMAVERA:
                _currentSeason = Season.VERAO;
                break;
            case VERAO:
                _currentSeason = Season.OUTONO;
                break;
            case OUTONO:
                _currentSeason = Season.INVERNO;
                break;
        }
    }

    public int getCleaningEffort() {
        switch (_treeType) {
            case CADUCA:
                switch (_currentSeason) {
                    case INVERNO:
                        return 0;
                    case PRIMAVERA:
                        return 1;
                    case VERAO:
                        return 2;
                    case OUTONO:
                        return 5;
                    default:
                        return 0;
                }
            case PERENE:
                switch (_currentSeason) {
                    case INVERNO:
                        return 2;
                    case PRIMAVERA:
                        return 1;
                    case VERAO:
                        return 1;
                    case OUTONO:
                        return 1;
                    default:
                        return 0;
                }
            default:
                return 0;
        }
    }
    
}


// talvez fazer um enumerado para as estações, e usar isto para saber
// qual o biological cycle da arvore, com um state também