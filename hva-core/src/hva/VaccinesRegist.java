package hva;
import java.io.Serializable;

public class VaccinesRegist implements Serializable {
    private String _employeeId;
    private String _animalId;

    public VaccinesRegist(String employeeId, String animalId) {
        _employeeId = employeeId;
        _animalId = animalId;
    }

    public String getEmployeeId() {
        return _employeeId;
    }

    public void setEmployeeId(String employeeId) {
        _employeeId = employeeId;
    }

    public String getAnimalId() {
        return _animalId;
    }

    public void setAnimalId(String animalId) {
        _animalId = animalId;
    }

}
