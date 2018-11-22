package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;


public class Departement {
    private String nomdpt;
    private Date datecreationdpt;
    private String adressedpt;
    private String telephonedpt;
    private Collection<Equipe> equipesByNomdpt = new HashSet<>();



    public String getNomdpt() {
        return nomdpt;
    }

    public void setNomdpt(String nomdpt) {
        this.nomdpt = nomdpt;
    }


    public Date getDatecreationdpt() {
        return datecreationdpt;
    }

    public void setDatecreationdpt(Date datecreationdpt) {
        this.datecreationdpt = datecreationdpt;
    }


    public String getAdressedpt() {
        return adressedpt;
    }

    public void setAdressedpt(String adressedpt) {
        this.adressedpt = adressedpt;
    }


    public String getTelephonedpt() {
        return telephonedpt;
    }

    public void setTelephonedpt(String telephonedpt) {
        this.telephonedpt = telephonedpt;
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


    public Collection<Equipe> getEquipesByNomdpt() {
        return equipesByNomdpt;
    }

    public void setEquipesByNomdpt(Collection<Equipe> equipesByNomdpt) {
        this.equipesByNomdpt = equipesByNomdpt;
    }
}
