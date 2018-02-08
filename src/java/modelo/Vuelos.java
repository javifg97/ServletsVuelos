/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author javier.fernandez3
 */
@Entity
@Table(name = "vuelos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vuelos.findAll", query = "SELECT v FROM Vuelos v"),
    @NamedQuery(name = "Vuelos.findByIdVuelo", query = "SELECT v FROM Vuelos v WHERE v.idVuelo = :idVuelo"),
    @NamedQuery(name = "Vuelos.findByDiayhora", query = "SELECT v FROM Vuelos v WHERE v.diayhora = :diayhora"),
    @NamedQuery(name = "Vuelos.findByOrigen", query = "SELECT v FROM Vuelos v WHERE v.origen = :origen"),
    @NamedQuery(name = "Vuelos.findByDestino", query = "SELECT v FROM Vuelos v WHERE v.destino = :destino"),
    @NamedQuery(name = "Vuelos.findByPrecio", query = "SELECT v FROM Vuelos v WHERE v.precio = :precio"),
    @NamedQuery(name = "Vuelos.findByPlazasTotales", query = "SELECT v FROM Vuelos v WHERE v.plazasTotales = :plazasTotales"),
    @NamedQuery(name = "Vuelos.findByPlazasLibres", query = "SELECT v FROM Vuelos v WHERE v.plazasLibres = :plazasLibres")})
public class Vuelos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idVuelo")
    private Integer idVuelo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "diayhora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date diayhora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "origen")
    private String origen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "destino")
    private String destino;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private double precio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "plazas_totales")
    private int plazasTotales;
    @Basic(optional = false)
    @NotNull
    @Column(name = "plazas_libres")
    private int plazasLibres;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVuelo")
    private Collection<Compra> compraCollection;

    public Vuelos() {
    }

    public Vuelos(Integer idVuelo) {
        this.idVuelo = idVuelo;
    }

    public Vuelos(Integer idVuelo, Date diayhora, String origen, String destino, double precio, int plazasTotales, int plazasLibres) {
        this.idVuelo = idVuelo;
        this.diayhora = diayhora;
        this.origen = origen;
        this.destino = destino;
        this.precio = precio;
        this.plazasTotales = plazasTotales;
        this.plazasLibres = plazasLibres;
    }

    public Integer getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(Integer idVuelo) {
        this.idVuelo = idVuelo;
    }

    public Date getDiayhora() {
        return diayhora;
    }

    public void setDiayhora(Date diayhora) {
        this.diayhora = diayhora;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getPlazasTotales() {
        return plazasTotales;
    }

    public void setPlazasTotales(int plazasTotales) {
        this.plazasTotales = plazasTotales;
    }

    public int getPlazasLibres() {
        return plazasLibres;
    }

    public void setPlazasLibres(int plazasLibres) {
        this.plazasLibres = plazasLibres;
    }

    @XmlTransient
    public Collection<Compra> getCompraCollection() {
        return compraCollection;
    }

    public void setCompraCollection(Collection<Compra> compraCollection) {
        this.compraCollection = compraCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVuelo != null ? idVuelo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vuelos)) {
            return false;
        }
        Vuelos other = (Vuelos) object;
        if ((this.idVuelo == null && other.idVuelo != null) || (this.idVuelo != null && !this.idVuelo.equals(other.idVuelo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Vuelos[ idVuelo=" + idVuelo + " ]";
    }
    
}
