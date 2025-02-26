import java.util.ArrayList;
import java.util.HashMap;

public class GestionnaireRecompenses
{
	private ArrayList<Badge> badges;
	private HashMap<Integer, String> titres;
	private String titreActuel;

	public GestionnaireRecompenses()
	{
		this.badges = new ArrayList<>();
		this.titres = new HashMap<>();
		initialiserBadges();
		initialiserTitres();
		this.titreActuel = "Novice";
	}

	private void initialiserBadges()
	{
		// Badges de force
		badges.add(new Badge("Première Pompe", "Complétez votre premier exercice de pompes", "BRONZE", "force",
				"pompes_bronze.png"));
		badges.add(new Badge("Force Montante", "Atteignez 50 pompes en une séance", "ARGENT", "force",
				"pompes_argent.png"));
		badges.add(new Badge("Maître de la Force", "Complétez 1000 pompes au total", "OR", "force", "pompes_or.png"));

		// Badges d'endurance
		badges.add(new Badge("Coureur Débutant", "Courez 1km", "BRONZE", "endurance", "course_bronze.png"));
		badges.add(new Badge("Marathon", "Courez 42km au total", "OR", "endurance", "marathon_or.png"));
	}

	private void initialiserTitres()
	{
		titres.put(1, "Novice");
		titres.put(5, "Apprenti");
		titres.put(10, "Adepte");
		titres.put(20, "Expert");
		titres.put(30, "Maître");
		titres.put(50, "Grand Maître");
		titres.put(100, "Légende");
	}

	public void verifierBadgesForce(double totalForce, int repetitions)
	{
		for (Badge badge : badges)
		{
			if (badge.getCategorie().equals("force") && !badge.estDebloque())
			{
				if (badge.getNom().equals("Première Pompe") && repetitions > 0)
				{
					badge.debloquer();
				}
				else if (badge.getNom().equals("Force Montante") && repetitions >= 50)
				{
					badge.debloquer();
				}
				else if (badge.getNom().equals("Maître de la Force") && totalForce >= 1000)
				{
					badge.debloquer();
				}
			}
		}
	}

	public void verifierBadgesEndurance(double distanceTotal, double distanceSession)
	{
		for (Badge badge : badges)
		{
			if (badge.getCategorie().equals("endurance") && !badge.estDebloque())
			{
				if (badge.getNom().equals("Coureur Débutant") && distanceSession >= 1)
				{
					badge.debloquer();
				}
				else if (badge.getNom().equals("Marathon") && distanceTotal >= 42)
				{
					badge.debloquer();
				}
			}
		}
	}

	public void mettreAJourTitre(int niveau)
	{
		for (int niveauRequis : titres.keySet())
		{
			if (niveau >= niveauRequis)
			{
				titreActuel = titres.get(niveauRequis);
			}
		}
	}

	public ArrayList<Badge> getBadgesDebloques()
	{
		ArrayList<Badge> badgesDebloques = new ArrayList<>();
		for (Badge badge : badges)
		{
			if (badge.estDebloque())
			{
				badgesDebloques.add(badge);
			}
		}
		return badgesDebloques;
	}

	public String getTitreActuel()
	{
		return titreActuel;
	}
}
