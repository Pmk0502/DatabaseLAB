package model;

import java.sql.Timestamp;


public class Article {
    private String titreart;
    private Integer nbrpages;
    private Timestamp datesoumission;
    private Chercheur chercheurByAuteur;
    private Chercheur chercheurByCoauteur;

    public String getTitreart() {
        return titreart;
    }

    public void setTitreart(String titreart) {
        this.titreart = titreart;
    }


    public Integer getNbrpages() {
        return nbrpages;
    }

    public void setNbrpages(Integer nbrpages) {
        this.nbrpages = nbrpages;
    }


    public Timestamp getDatesoumission() {
        return datesoumission;
    }

    public void setDatesoumission(Timestamp datesoumission) {
        this.datesoumission = datesoumission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (titreart != null ? !titreart.equals(article.titreart) : article.titreart != null) return false;
        if (nbrpages != null ? !nbrpages.equals(article.nbrpages) : article.nbrpages != null) return false;
        if (datesoumission != null ? !datesoumission.equals(article.datesoumission) : article.datesoumission != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = titreart != null ? titreart.hashCode() : 0;
        result = 31 * result + (nbrpages != null ? nbrpages.hashCode() : 0);
        result = 31 * result + (datesoumission != null ? datesoumission.hashCode() : 0);
        return result;
    }

    public Chercheur getChercheurByAuteur() {
        return chercheurByAuteur;
    }

    public void setChercheurByAuteur(Chercheur chercheurByAuteur) {
        this.chercheurByAuteur = chercheurByAuteur;
    }


    public Chercheur getChercheurByCoauteur() {
        return chercheurByCoauteur;
    }

    public void setChercheurByCoauteur(Chercheur chercheurByCoauteur) {
        this.chercheurByCoauteur = chercheurByCoauteur;
    }
}
