package hva;

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

public class Veterinarian extends Employees {
    private Map<String, Species> _Vetspecies = new TreeMap<String, Species>();
    private int _satisfaction;
    private String _type;

    public Veterinarian(String employeeId, String name, String type) {
        super(employeeId, name, "VET");
        _type = type;
    }

    public void addSpecies(String id, Species specie) {
        if (!_Vetspecies.containsKey(id))
            _Vetspecies.put(id, specie);
    }

    public void removeSpecies(String id) {
        if (_Vetspecies.containsKey(id))
            _Vetspecies.remove(id);
    }

    public boolean hasSpecies(String id) {
        return _Vetspecies.containsKey(id);
    }

    public int getSatisfaction() {
        return _satisfaction;
    }

    public void setSatisfaction(int satisfaction) {
        _satisfaction = satisfaction;
    }

    public String getType() {
        return _type;
    }

    public void setType(String type) {
        _type = type;
    }

    public int getPopulation() {
        return _Vetspecies.size(); 
    }

    public String getSpeciesIds() {
        List<String> speciesIds = new ArrayList<>(_Vetspecies.keySet());
        return String.join(",", speciesIds);
    }

    public Map<String, Species> getVetspecies() {
        return _Vetspecies;
    }    

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_type).append("|")
                .append(getEmployeeId()).append("|")
                .append(getEmployeeName());
        if (!_Vetspecies.isEmpty()) {
            sb.append("|").append(getSpeciesIds());
        }
        return sb.toString();
    }
}
