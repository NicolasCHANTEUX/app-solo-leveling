public class Progression
{
	private int niveau;
	private int xpActuelle;
	private int xpRequise;
	private double progressionForce;
	private double progressionEndurance;
	private double progressionAgilite;
	private double progressionMental;

	public Progression()
	{
		this.niveau = 1;
		this.xpActuelle = 0;
		this.xpRequise = 100; // XP de base pour le niveau 2
		this.progressionForce = 0.0;
		this.progressionEndurance = 0.0;
		this.progressionAgilite = 0.0;
		this.progressionMental = 0.0;
	}

	public void ajouterXP(int xp)
	{
		this.xpActuelle += xp;
		verifierNiveau();
	}

	private void verifierNiveau()
	{
		while (xpActuelle >= xpRequise)
		{
			niveau++;
			xpActuelle -= xpRequise;
			xpRequise = calculerProchainPalier();
		}
	}

	private int calculerProchainPalier()
	{
		return (int) (100 * Math.pow(1.5, niveau - 1));
	}

	public void updateProgression(String type, double valeur)
	{
		switch (type.toLowerCase())
		{
		case "force":
			progressionForce = Math.min(progressionForce + valeur, 100.0);
			break;
		case "endurance":
			progressionEndurance = Math.min(progressionEndurance + valeur, 100.0);
			break;
		case "agilite":
			progressionAgilite = Math.min(progressionAgilite + valeur, 100.0);
			break;
		case "mental":
			progressionMental = Math.min(progressionMental + valeur, 100.0);
			break;
		}
	}

	// Getters
	public int getNiveau()
	{
		return niveau;
	}

	public int getXpActuelle()
	{
		return xpActuelle;
	}

	public int getXpRequise()
	{
		return xpRequise;
	}

	public double getProgressionForce()
	{
		return progressionForce;
	}

	public double getProgressionEndurance()
	{
		return progressionEndurance;
	}

	public double getProgressionAgilite()
	{
		return progressionAgilite;
	}

	public double getProgressionMental()
	{
		return progressionMental;
	}

	public double getPourcentageProgression()
	{
		return (double) xpActuelle / xpRequise * 100;
	}
}
