package com.example.FinalProject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "t_places")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Places {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description = "No description";

    @ManyToOne(fetch = FetchType.EAGER)
    private Categories category;

    @JsonIgnore
    public String getData(){
        return "#" + id + " " + name + " " + address + " " + description + " category: " + category;
    }
}
