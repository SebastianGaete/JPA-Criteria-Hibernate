package com.sebastian.apicriteria.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

/* Una clase Embeddable quiere decir que puede ser utilizable o embebida en otra clase Entity con la finalidad de reutilizar
 * atributos que tengan en común algunas clases Entitys, como tambien sirve para alojar los métodos con las anotaciones de
 * los eventos del ciclo de vida de las clases.
 */
@Embeddable
public class ManejoFechaEmbebible {

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    /* Podemos manejar el ciclo de vida en nuestra clase Entity para que cada vez que creemos un registro o actualicemos o eliminemos
     * se ejecute algo en nuestro código o se inserte algo en algún campo de nuestra BBDD; como una fecha.
     */

    @PrePersist
    public void prePersist(){
        fechaCreacion = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        fechaActualizacion = LocalDateTime.now();
    }


    public String getFechaCreacion() {
        if(fechaActualizacion != null){
            return fechaActualizacion.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm"));
        }
        return "No Asignado";
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaActualizacion() {
        if(fechaActualizacion != null){
            return fechaActualizacion.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm"));
        }
        return "No actualizado";
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}