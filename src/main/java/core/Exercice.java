import java.time.LocalDate;

public class Exercice
{
	private String nom;
	private String type;
	private String discipline;
	private LocalDate dateExo;
	private int duree; // en minutes
	private double nbXp;
	private ExerciceDetails details;
	private int repetitionsEffectuees;
	private String niveauChoisi;
	private boolean exerciceComplete;

	public Exercice(String nom, String type, String discipline, int duree)
	{
		this.nom = nom;
		this.type = type;
		this.discipline = discipline;
		this.duree = duree;
		this.dateExo = LocalDate.now();
		this.details = null;
		this.repetitionsEffectuees = 0;
		this.niveauChoisi = "débutant";
		this.exerciceComplete = false;
	}

	public String getNom()
	{
		return this.nom;
	}

	public String getType()
	{
		return this.type;
	}

	public String getDiscipline()
	{
		return this.discipline;
	}

	public int getDuree()
	{
		return this.duree;
	}

	public double getNbXp()
	{
		return this.nbXp;
	}

	public LocalDate getDateExo()
	{
		return this.dateExo;
	}

	public double getNbXpExercice()
	{
		double coeffMultiXp = 0;
		switch (this.type)
		{
		case "endurance":
			coeffMultiXp = 5.5;
			this.nbXp = this.duree * coeffMultiXp;
			break;

		case "force":
			coeffMultiXp = 8.3;
			this.nbXp = this.duree * coeffMultiXp;
			break;

		default:
			break;
		}
		return this.nbXp;
	}

	public double[] getNbPointsStats()
	{
		double tabPoints[] = new double[4];
		double tabCoeffPoints[] = new double[4]; // e/f/m/a

		switch (this.type)
		{
		case "endurance":
			tabCoeffPoints[0] = 0.012;
			tabCoeffPoints[1] = 0.002;
			tabCoeffPoints[2] = 0.006;
			break;

		case "force":
			tabCoeffPoints[0] = 0.002;
			tabCoeffPoints[1] = 0.03;
			tabCoeffPoints[2] = 0.005;
			break;

		default:
			break;
		}

		switch (this.discipline)
		{
		case "course à pied":
			tabCoeffPoints[3] = 0.005;
			break;

		case "musculation":
			tabCoeffPoints[3] = 0.003;
			break;

		case "escalade":
			tabCoeffPoints[3] = 0.008;

		default:
			tabCoeffPoints[3] = 0.003;
			break;
		}

		for (int i = 0; i < tabPoints.length; i++)
		{
			tabPoints[i] = this.duree * tabCoeffPoints[i];
		}

		return tabPoints;
	}

	public void setExerciceDetails(ExerciceDetails details)
	{
		this.details = details;
	}

	public void setNiveau(String niveau)
	{
		this.niveauChoisi = niveau;
	}

	public void enregistrerRepetitions(int repetitions)
	{
		this.repetitionsEffectuees = repetitions;
		if (details != null && repetitions >= details.getObjectifPourNiveau(niveauChoisi))
		{
			this.exerciceComplete = true;
			// Bonus d'XP pour avoir complété l'objectif
			this.nbXp += 50;
		}
	}

	public int getRepetitionsEffectuees()
	{
		return this.repetitionsEffectuees;
	}

	public boolean isExerciceComplete()
	{
		return this.exerciceComplete;
	}

	public String toString()
	{
		double[] tabPointsEx = getNbPointsStats();
		String retour = "##################################################" + "\n EXERCICE \n"
				+ "################################################## \n";
		retour += "# Nom                  : " + this.nom + "\n";
		retour += "# Type                 : " + this.type + "\n";
		retour += "# Discipline           : " + this.discipline + "\n";
		retour += "# Duree                : " + this.duree + "\n";
		retour += "# Nombre d'Xp          : " + getNbXpExercice() + '\n';
		retour += "# Points               : " + tabPointsEx[0] + "\n#                        " + tabPointsEx[1]
				+ "\n#                        " + tabPointsEx[2] + "\n#                        " + tabPointsEx[3] + "\n"
				+ "\n" + "\n";
		if (details != null)
		{
			retour += "\nObjectif: " + details.getObjectifPourNiveau(niveauChoisi) + " " + details.getDescription()
					+ "\nRépétitions effectuées: " + repetitionsEffectuees + "\nStatut: "
					+ (exerciceComplete ? "Complété" : "En cours");
		}
		return retour;
	}
}
