package model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Chercheur {
    private String matriculecher;
    private String prenomcher;
    private String nomcher;
    private String positioncher;
    private Integer salairecher;
    private Collection<Article> articlesByMatriculecher;
    private Collection<Article> articlesByMatriculecher_0;
    private Equipe equipeByNomeq;

    @Id
    @Column(name = "matriculecher", nullable = false, length = 6)
    public String getMatriculecher() {
        return matriculecher;
    }

    public void setMatriculecher(String matriculecher) {
        this.matriculecher = matriculecher;
    }

    @Column(name = "prenomcher", nullable = false, length = 20)
    public String getPrenomcher() {
        return prenomcher;
    }

    public void setPrenomcher(String prenomcher) {
        this.prenomcher = prenomcher;
    }

    @Column(name = "nomcher", nullable = false, length = 20)
    public String getNomcher() {
        return nomcher;
    }

    public void setNomcher(String nomcher) {
        this.nomcher = nomcher;
    }

    @Column(name = "positioncher", nullable = true, length = 10)
    public String getPositioncher() {
        return positioncher;
    }

    public void setPositioncher(String positioncher) {
        this.positioncher = positioncher;
    }

    @Column(name = "salairecher", nullable = true)
    public Integer getSalairecher() {
        return salairecher;
    }

    public void setSalairecher(Integer salairecher) {
        this.salairecher = salairecher;
    }

    @OneToMany(mappedBy = "chercheurByAuteur")
    public Collection<Article> getArticlesByMatriculecher() {
        return articlesByMatriculecher;
    }

    public void setArticlesByMatriculecher(Collection<Article> articlesByMatriculecher) {
        this.articlesByMatriculecher = articlesByMatriculecher;
    }

    @OneToMany(mappedBy = "chercheurByCoauteur")
    public Collection<Article> getArticlesByMatriculecher_0() {
        return articlesByMatriculecher_0;
    }

    public void setArticlesByMatriculecher_0(Collection<Article> articlesByMatriculecher_0) {
        this.articlesByMatriculecher_0 = articlesByMatriculecher_0;
    }

    @ManyToOne
    @JoinColumn(name = "nomeq", referencedColumnName = "nomeq")
    public Equipe getEquipeByNomeq() {
        return equipeByNomeq;
    }

    public void setEquipeByNomeq(Equipe equipeByNomeq) {
        this.equipeByNomeq = equipeByNomeq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chercheur chercheur = (Chercheur) o;

        if (matriculecher != null ? !matriculecher.equals(chercheur.matriculecher) : chercheur.matriculecher != null)
            return false;
        if (prenomcher != null ? !prenomcher.equals(chercheur.prenomcher) : chercheur.prenomcher != null) return false;
        if (nomcher != null ? !nomcher.equals(chercheur.nomcher) : chercheur.nomcher != null) return false;
        if (positioncher != null ? !positioncher.equals(chercheur.positioncher) : chercheur.positioncher != null)
            return false;
        if (salairecher != null ? !salairecher.equals(chercheur.salairecher) : chercheur.salairecher != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = matriculecher != null ? matriculecher.hashCode() : 0;
        result = 31 * result + (prenomcher != null ? prenomcher.hashCode() : 0);
        result = 31 * result + (nomcher != null ? nomcher.hashCode() : 0);
        result = 31 * result + (positioncher != null ? positioncher.hashCode() : 0);
        result = 31 * result + (salairecher != null ? salairecher.hashCode() : 0);
        return result;
    }


}
