package br.com.senaiportifolio.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Deprecated
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 200)
    private String location;

    @Size(max = 150)
    private String name;

    @JsonIgnore
    @OneToOne(mappedBy = "image")
    private Trabalho trabalho_image;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Image [location=" + location + ", name=" + name + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Trabalho getTrabalho_image() {
        return trabalho_image;
    }

    public void setTrabalho_image(Trabalho trabalho_image) {
        this.trabalho_image = trabalho_image;
    }

    

    
}
