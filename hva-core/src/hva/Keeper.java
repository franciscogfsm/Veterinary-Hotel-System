package hva;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Keeper extends Employees {
    private Map<String, Habitat> _Keeperhabitats = new TreeMap<String, Habitat>();
    private int _satisfaction;
    private String _type;

    public Keeper(String employeeId, String name, String type) {
        super(employeeId, name, "TRT");
        _type = type;
    }

    public void addHabitat(String id, Habitat habitat) {
        if (!_Keeperhabitats.containsKey(id))
            _Keeperhabitats.put(id, habitat);
    }

    public void removeHabitat(String id) {
        if (_Keeperhabitats.containsKey(id))
            _Keeperhabitats.remove(id);
    }

    public boolean hasHabitat(String id) {
        return _Keeperhabitats.containsKey(id);
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
    public  Map<String, Habitat> getKeeperHabitats() {
        return _Keeperhabitats;
    }
    

    public String getHabitatIds() {
        List<String> habitatIds = new ArrayList<>(_Keeperhabitats.keySet());
        return String.join(",", habitatIds);

    }

    public List<Habitat> getAssignedHabitats() {
        return new ArrayList<>(_Keeperhabitats.values());
    }
    public boolean hasHabitatAssigned(String habitatId) {
        return _Keeperhabitats.containsKey(habitatId);
    }
    

    public String toString() {
        String habitatIds = getHabitatIds();
        StringBuilder sb = new StringBuilder();
        sb.append(_type).append("|")
                .append(getEmployeeId()).append("|")
                .append(getEmployeeName());

        if (!habitatIds.isEmpty()) {
            sb.append("|").append(habitatIds);
        }

        return sb.toString();
    }

}