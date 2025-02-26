
/*
 * PROFIL
	- pseudo / identifiant 
	- poids 
	- taille
	- age
	- photo de profil
	- date de création du compte
	- (niveau) 						NIVEAU
	- (badges de défis complété)	DEFIS

	- pourcentage de chaque partie du corps 

 */
import java.util.ArrayList;
import java.time.LocalDate;

public class Profil
{
	private String pseudo;
	private double poids;
	private double taille;
	private String pdp;
	private LocalDate dateCompte;
	private LocalDate dateNaissance;

	private int nbXp;

	private double pointsEndurance;
	private double pointsForce;
	private double pointsAgilite;
	private double pointsMental;

	private ArrayList<Exercice> list_Exercices = new ArrayList<>();
	private ArrayList<Quete> list_Quetes = new ArrayList<>();
	// private ArrayList<Defis> list_Defis = new ArrayList<>();
	// private ArrayList<Badge> list_Badges = new ArrayList<>();
	// private ArrayList<PartieCorps> list_PartiCorps = new ArrayList<>();

	private Progression progression;
	private GestionnaireRecompenses gestionnaireRecompenses;
	private StatistiquesProgression statistiques;

	public Profil(String pseudo, double poids, double taille, String pdp, LocalDate dateNaissance)
	{
		this.pseudo = pseudo;
		this.poids = poids;
		this.taille = taille;
		this.pdp = pdp;
		this.dateCompte = LocalDate.now();
		this.dateNaissance = dateNaissance;
		this.nbXp = 0;

		this.pointsAgilite = 0;
		this.pointsEndurance = 0;
		this.pointsForce = 0;
		this.pointsMental = 0;

		this.progression = new Progression();
		this.gestionnaireRecompenses = new GestionnaireRecompenses();
		this.statistiques = new StatistiquesProgression();
	}

	public String getPseudo()
	{
		return this.pseudo;
	}

	public double getPoids()
	{
		return this.poids;
	}

	public double getTaille()
	{
		return this.taille;
	}

	public String getPdp()
	{
		return this.pdp;
	}

	public LocalDate getDateCompte()
	{
		return this.dateCompte;
	}

	public LocalDate getDateNaissance()
	{
		return this.dateNaissance;
	}

	public int getNbXp()
	{
		return this.nbXp;
	}

	public double getPointsEndurance()
	{
		return this.pointsEndurance;
	}

	public double getPointsForce()
	{
		return this.pointsForce;
	}

	public double getPointsMental()
	{
		return this.pointsMental;
	}

	public double getPointsAgilite()
	{
		return this.pointsAgilite;
	}

	public boolean addQuete(Quete quete)
	{
		if (quete.queteTerminee())
		{
			nbXp += quete.getNbXpQuete();
			double[] tabPointsQuete = quete.getNbPointsStats();
			this.pointsEndurance += tabPointsQuete[0];
			this.pointsForce += tabPointsQuete[1];
			this.pointsMental += tabPointsQuete[2];
			this.pointsAgilite += tabPointsQuete[3];
			this.list_Quetes.add(quete);
		}
		return true;
	}

	public Quete[] getListQuetes()
	{
		int nbQuetes = this.list_Quetes.size();
		Quete[] tabQuete = new Quete[nbQuetes];
		for (int i = 0; i < nbQuetes; i++)
		{
			tabQuete[i] = this.list_Quetes.get(i);
		}
		return tabQuete;
	}

	public boolean afficheListQuetes()
	{
		for (int i = 0; i < this.list_Quetes.size(); i++)
		{
			System.out.println(this.list_Quetes.get(i).toString());
		}
		return true;
	}

	public boolean addExercice(Exercice exercice)
	{
		nbXp += exercice.getNbXpExercice();
		double[] tabPointsEx = exercice.getNbPointsStats();
		this.pointsEndurance += tabPointsEx[0];
		this.pointsForce += tabPointsEx[1];
		this.pointsMental += tabPointsEx[2];
		this.pointsAgilite += tabPointsEx[3];
		this.list_Exercices.add(exercice);

		// Vérifier les badges après chaque exercice
		if (exercice.getType().equals("force"))
		{
			gestionnaireRecompenses.verifierBadgesForce(this.pointsForce, exercice.getRepetitionsEffectuees());
		}
		else if (exercice.getType().equals("endurance"))
		{
			gestionnaireRecompenses.verifierBadgesEndurance(this.pointsEndurance, exercice.getDuree());
		}

		// Mettre à jour le titre selon le niveau
		gestionnaireRecompenses.mettreAJourTitre(this.getNiveau());

		// Ajouter le suivi des statistiques
		statistiques.enregistrerSeance(LocalDate.now(), exercice);

		return true;
	}

	public Exercice[] getListExercices()
	{
		int nbExercice = this.list_Exercices.size();
		Exercice[] tabExercice = new Exercice[nbExercice];
		for (int i = 0; i < nbExercice; i++)
		{
			tabExercice[i] = list_Exercices.get(i);
		}
		return tabExercice;
	}

	public boolean afficheListExo()
	{
		for (int i = 0; i < this.list_Exercices.size(); i++)
		{
			System.out.println(this.list_Exercices.get(i).toString());
		}
		return true;
	}

	public void addExperience(int xp)
	{
		progression.ajouterXP(xp);
	}

	public void updateStatistique(String type, double valeur)
	{
		progression.updateProgression(type, valeur);
	}

	public int getNiveau()
	{
		return progression.getNiveau();
	}

	public double getProgressionCompetence(String type)
	{
		switch (type.toLowerCase())
		{
		case "force":
			return progression.getProgressionForce();
		case "endurance":
			return progression.getProgressionEndurance();
		case "agilite":
			return progression.getProgressionAgilite();
		case "mental":
			return progression.getProgressionMental();
		default:
			return 0.0;
		}
	}

	public ArrayList<Badge> getBadgesDebloques()
	{
		return gestionnaireRecompenses.getBadgesDebloques();
	}

	public String getTitreActuel()
	{
		return gestionnaireRecompenses.getTitreActuel();
	}

	public String getStatistiquesDetaillees(String nomExercice)
	{
		ArrayList<Double> progressions = statistiques.getProgressionExercice(nomExercice);
		if (progressions.isEmpty())
		{
			return "Aucune donnée disponible pour cet exercice.";
		}

		double max = progressions.stream().mapToDouble(Double::doubleValue).max().getAsDouble();
		double moyenne = progressions.stream().mapToDouble(Double::doubleValue).average().getAsDouble();

		return String.format(
				"\nStatistiques pour %s:\nMeilleure performance: %.2f\nMoyenne: %.2f\nNombre de séances: %d",
				nomExercice, max, moyenne, progressions.size());
	}

	public String toString()
	{
		String retour = "################################################## \n     PROFIL \n################################################## \n";
		retour += "# Pseudo            : " + getPseudo() + '\n';
		retour += "# Poids             : " + getPoids() + '\n';
		retour += "# Taille            : " + getTaille() + '\n';
		retour += "# Date de compte    : " + getDateCompte() + '\n';
		retour += "# Date de naissance : " + getDateNaissance() + '\n';
		retour += "# Nombre d'Xp       : " + this.nbXp + '\n' + '\n';

		retour += "#      STATISTIQUES \n";
		retour += "# Points endurance  : " + (double) (int) (pointsEndurance * 100) / 100 + '\n';
		retour += "# Points force      : " + (double) (int) (pointsForce * 100) / 100 + '\n';
		retour += "# Points agilité    : " + (double) (int) (pointsMental * 100) / 100 + '\n';
		retour += "# Points mental     : " + (double) (int) (pointsAgilite * 100) / 100 + '\n' + '\n' + '\n';

		retour += "\nNiveau: " + progression.getNiveau();
		retour += "\nXP: " + progression.getXpActuelle() + "/" + progression.getXpRequise();
		retour += "\nProgression Force: " + String.format("%.2f", progression.getProgressionForce()) + "%";
		retour += "\nProgression Endurance: " + String.format("%.2f", progression.getProgressionEndurance()) + "%";
		retour += "\nProgression Agilité: " + String.format("%.2f", progression.getProgressionAgilite()) + "%";
		retour += "\nProgression Mental: " + String.format("%.2f", progression.getProgressionMental()) + "%";
		retour += "\nTitre actuel: " + getTitreActuel();
		retour += "\nBadges débloqués: ";
		for (Badge badge : getBadgesDebloques())
		{
			retour += "\n  - " + badge.getNom() + " (" + badge.getNiveau() + ")";
		}

		retour += statistiques.genererRapportProgression();

		return retour;
	}
}