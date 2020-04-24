package clinic;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Users {

    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final StringProperty type = new SimpleStringProperty();
    private final StringProperty Date = new SimpleStringProperty();
    private final BooleanProperty admin = new SimpleBooleanProperty();

    public String getName() {
        return name.get();
    }

    public boolean getAdmin() {
        return admin.get();
    }

    public String getDate() {
        return Date.get();
    }

    public String getPassword() {
        return password.get();
    }

    public String getType() {
        return type.get();
    }

    public Users() {
    }

    public Users(String name, String password, String type, String Date, boolean admin) {
        this.name.set(name);
        this.password.set(password);
        this.type.set(type);
        this.Date.set(Date);
        this.admin.set(admin);
    }

    public Users(String name, String password) {
        this.name.set(name);
        this.password.set(password);
    }
    
    public Users(boolean admin) {
        this.admin.set(admin);
    }

    public final void setName(String name) {
        this.name.set(name);
    }

    public final void setDate(String Date) {
        this.Date.set(Date);
    }

    public final void setPassword(String password) {
        this.password.set(password);
    }

    public final void setType(String type) {
        this.type.set(type);
    }

    public final void setAdmin(boolean admin) {
        this.admin.set(admin);
    }
}
