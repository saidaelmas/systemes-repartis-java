package ma.projet.bean;

import ma.projet.inter.IPersonne;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Personne implements IPersonne {

    private int id;
    private String nom;
    private String prenom;
    private Date dateNais;
    private double salaire;
    private Profil profil;

    public Personne(int id, String nom, String prenom, Date dateNais, double salaire, Profil profil) {
        if (nom == null || nom.trim().isEmpty())
            throw new IllegalArgumentException("Le nom est vide ou null.");
        if (prenom == null || prenom.trim().isEmpty())
            throw new IllegalArgumentException("Le prénom est vide ou null.");
        if (salaire < 0)
            throw new IllegalArgumentException("Le salaire ne peut pas être négatif.");
        if (dateNais == null)
            throw new IllegalArgumentException("La date de naissance est null.");
        if (dateNais.after(new Date()))
            throw new IllegalArgumentException("La date de naissance est dans le futur.");
        if (profil == null)
            throw new IllegalArgumentException("Le profil est null.");

        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNais = dateNais;
        this.salaire = salaire;
        this.profil = profil;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public Date getDateNais() { return dateNais; }
    public double getSalaire() { return salaire; }
    public Profil getProfil() { return profil; }

    private boolean estDirecteur() {
        return profil.getLibelle() != null && profil.getLibelle().equalsIgnoreCase("directeur");
    }

    @Override
    public double calculerSalaire() {
        // Directeur : +20 %, autres : +10 %
        return estDirecteur() ? salaire * 1.20 : salaire * 1.10;
    }

    @Override
    public void affiche() {
        String date = new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH).format(dateNais);
        NumberFormat nf = NumberFormat.getIntegerInstance(Locale.FRANCE);
        System.out.println("Je suis le " + profil.getLibelle().toLowerCase() + " "
                + nom.toUpperCase() + " " + prenom
                + " né le " + date
                + " mon salaire est " + nf.format(Math.round(salaire)).replace('\u202f', ' ').replace('\u00a0', ' ') + "dh");
    }
}
