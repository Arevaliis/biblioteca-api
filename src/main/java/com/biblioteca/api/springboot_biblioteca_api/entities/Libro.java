package com.biblioteca.api.springboot_biblioteca_api.entities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(length = 50)
    private String autor;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "libros_categorias",

        joinColumns = @JoinColumn(
            name = "libro_id",
            foreignKey = @ForeignKey(
                foreignKeyDefinition =
                    "FOREIGN KEY (libro_id) REFERENCES libros(id) ON DELETE CASCADE"
            )
        ),
        
        inverseJoinColumns = @JoinColumn(
            name = "categoria_id",
            foreignKey = @ForeignKey(
                foreignKeyDefinition =
                    "FOREIGN KEY (categoria_id) REFERENCES categorias(id) ON DELETE CASCADE"
            )
        )
    )
    @JsonIdentityReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Categoria> categorias = new ArrayList<>();

    public Libro() {}

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public void addCategoria(Categoria categoria) {
        categorias.add(categoria);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Libro other = (Libro) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
