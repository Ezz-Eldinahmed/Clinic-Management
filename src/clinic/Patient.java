package clinic;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Patient {

    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty kind = new SimpleStringProperty();
    private final StringProperty Todayvisit = new SimpleStringProperty();
    private final StringProperty dr = new SimpleStringProperty();
    private final StringProperty phone = new SimpleStringProperty();
    private final StringProperty r = new SimpleStringProperty();
    private final DoubleProperty weight = new SimpleDoubleProperty();
    private final DoubleProperty age = new SimpleDoubleProperty();
    private final IntegerProperty idOfPatient = new SimpleIntegerProperty();

    public Patient() {
    }

    public Patient(String name, String kind, String Todayvisit, Double weight, String dr, Double age, String phone, String r) {
        this.name.set(name);
        this.kind.set(kind);
        this.Todayvisit.set(Todayvisit);
        this.weight.set(weight);
        this.dr.set(dr);
        this.age.set(age);
        this.phone.set(phone);
        this.r.set(r);
    }

    public Patient(int idOfPatient, String name, String kind, String Todayvisit, Double weight, String dr, Double age, String phone, String r) {
        this.idOfPatient.set(idOfPatient);
        this.name.set(name);
        this.kind.set(kind);
        this.Todayvisit.set(Todayvisit);
        this.weight.set(weight);
        this.dr.set(dr);
        this.age.set(age);
        this.phone.set(phone);
        this.r.set(r);
    }

    public IntegerProperty idProperty() {
        return idOfPatient;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty kindProperty() {
        return kind;
    }

    public StringProperty TodayvisitProperty() {
        return Todayvisit;
    }

    public DoubleProperty weightProperty() {
        return weight;
    }

    public StringProperty drProperty() {
        return dr;
    }

    public DoubleProperty ageProperty() {
        return age;
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public StringProperty rProperty() {
        return r;
    }

    public final String getName() {
        return name.get();
    }

    public final void setName(String name) {
        this.name.set(name);
    }

    public final String getkind() {
        return kind.get();
    }

    public final void setkind(String kind) {
        this.kind.set(kind);
    }

    public final String getR() {
        return r.get();
    }

    public final void setR(String r) {
        this.r.set(r);
    }

    public final String getTodayvisit() {
        return name.get();
    }

    public final void setTodayvisit(String todaydate) {
        this.Todayvisit.set(todaydate);
    }

    public final Double getage() {
        return age.get();
    }

    public final void setage(Double age) {
        this.age.set(age);
    }

    public final Double getWeight() {
        return weight.get();
    }

    public final void setWeight(Double weight) {
        this.weight.set(weight);
    }

    public final int getId() {
        return idOfPatient.get();
    }

    public final void setid(int id) {
        this.idOfPatient.set(id);
    }

    public final String getDr() {
        return dr.get();
    }

    public final void setdr(String dr) {
        this.dr.set(dr);
    }

    public final String getphone() {
        return phone.get();
    }

    public final void setphone(String phone) {
        this.phone.set(phone);
    }

}
