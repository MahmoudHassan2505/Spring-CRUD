package com.example.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name = "drug")
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany()
    @JoinTable(
            name = "patient_drug",
            joinColumns = @JoinColumn(name = "drug_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id",referencedColumnName = "id")
    )
    @JsonIgnore

    private List<Patient> patients;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", patients=" + patients +
                '}';
    }
}
