package model;

import java.sql.Date;
import java.util.Collection;


public class Equipe {
    private String nomeq;
    private Date datecreationeq;
    private Integer nbrprojets;
    private String responsable;
    private Collection<Chercheur> chercheursByNomeq;
    private Departement departementByNomdpt;


    public String getNomeq() {
        return nomeq;
    }

    public void setNomeq(String nomeq) {
        this.nomeq = nomeq;
    }


    public Date getDatecreationeq() {
        return datecreationeq;
    }

    public void setDatecreationeq(Date datecreationeq) {
        this.datecreationeq = datecreationeq;
    }


    public Integer getNbrprojets() {
        return nbrprojets;
    }

    public void setNbrprojets(Integer nbrprojets) {
        this.nbrprojets = nbrprojets;
    }


    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Equipe equipe = (Equipe) o;

        if (nomeq != null ? !nomeq.equals(equipe.nomeq) : equipe.nomeq != null) return false;
        if (datecreationeq != null ? !datecreationeq.equals(equipe.datecreationeq) : equipe.datecreationeq != null)
            return false;
        if (nbrprojets != null ? !nbrprojets.equals(equipe.nbrprojets) : equipe.nbrprojets != null) return false;
        if (responsable != null ? !responsable.equals(equipe.responsable) : equipe.responsable != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nomeq != null ? nomeq.hashCode() : 0;
        result = 31 * result + (datecreationeq != null ? datecreationeq.hashCode() : 0);
        result = 31 * result + (nbrprojets != null ? nbrprojets.hashCode() : 0);
        result = 31 * result + (responsable != null ? responsable.hashCode() : 0);
        return result;
    }


    public Collection<Chercheur> getChercheursByNomeq() {
        return chercheursByNomeq;
    }

    public void setChercheursByNomeq(Collection<Chercheur> chercheursByNomeq) {
        this.chercheursByNomeq = chercheursByNomeq;
    }


    public Departement getDepartementByNomdpt() {
        return departementByNomdpt;
    }

    public void setDepartementByNomdpt(Departement departementByNomdpt) {
        this.departementByNomdpt = departementByNomdpt;
    }
}
