package model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Departement {
    private String nomdpt;
    private Date datecreationdpt;
    private String adressedpt;
    private String telephonedpt;
    private Collection<Equipe> equipesByNomdpt;

    @Id
    @Column(name = "nomdpt", nullable = false, length = 20)
    public String getNomdpt() {
        return nomdpt;
    }

    public void setNomdpt(String nomdpt) {
        this.nomdpt = nomdpt;
    }

    @Column(name = "datecreationdpt", nullable = true)
    public Date getDatecreationdpt() {
        return datecreationdpt;
    }

    public void setDatecreationdpt(Date datecreationdpt) {
        this.datecreationdpt = datecreationdpt;
    }

    @Column(name = "adressedpt", nullable = true, length = 20)
    public String getAdressedpt() {
        return adressedpt;
    }

    public void setAdressedpt(String adressedpt) {
        this.adressedpt = adressedpt;
    }

    @Column(name = "telephonedpt", nullable = true, length = 20)
    public String getTelephonedpt() {
        return telephonedpt;
    }

    public void setTelephonedpt(String telephonedpt) {
        this.telephonedpt = telephonedpt;
    }

    @OneToMany(mappedBy = "departementByNomdpt")
    public Collection<Equipe> getEquipesByNomdpt() {
        return equipesByNomdpt;
    }

    public void setEquipesByNomdpt(Collection<Equipe> equipesByNomdpt) {
        this.equipesByNomdpt = equipesByNomdpt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Departement that = (Departement) o;

        if (nomdpt != null ? !nomdpt.equals(that.nomdpt) : that.nomdpt != null) return false;
        if (datecreationdpt != null ? !datecreationdpt.equals(that.datecreationdpt) : that.datecreationdpt != null)
            return false;
        if (adressedpt != null ? !adressedpt.equals(that.adressedpt) : that.adressedpt != null) return false;
        if (telephonedpt != null ? !telephonedpt.equals(that.telephonedpt) : that.telephonedpt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nomdpt != null ? nomdpt.hashCode() : 0;
        result = 31 * result + (datecreationdpt != null ? datecreationdpt.hashCode() : 0);
        result = 31 * result + (adressedpt != null ? adressedpt.hashCode() : 0);
        result = 31 * result + (telephonedpt != null ? telephonedpt.hashCode() : 0);
        return result;
    }

}
