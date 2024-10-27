package hva;

import java.io.Serializable;

public abstract class Employees implements Serializable {
    private String _employeeId;
    private String _Name;
    private String _Type;

    public Employees(String employeeId, String name, String type) {
        _employeeId = employeeId;
        _Name = name;
        _Type = type;
    }

    public String getEmployeeId() {
        return _employeeId;
    }

    public void setEmployeeId(String employeeId) {
        _employeeId = employeeId;
    }

    public String getEmployeeName() {
        return _Name;
    }

    public abstract String getType();

    // tipo|id|nome|idResponsabildades
    @Override
    public abstract String toString();

}
