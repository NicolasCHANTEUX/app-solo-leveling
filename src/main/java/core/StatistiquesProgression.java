package src.main.java.core;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ArrayList;

public class StatistiquesProgression
{
	private HashMap<LocalDate, Double> historiqueForce;
	private HashMap<LocalDate, Double> historiqueEndurance;
	private HashMap<LocalDate, Integer> historiqueCalories;
	private HashMap<LocalDate, Integer> historiqueTempsEntrainement;
	private HashMap<String, ArrayList<Double>> progressionParExercice;

	public StatistiquesProgression()
	{
		this.historiqueForce = new HashMap<>();
		this.historiqueEndurance = new HashMap<>();
		this.historiqueCalories = new HashMap<>();
		this.historiqueTempsEntrainement = new HashMap<>();
		this.progressionParExercice = new HashMap<>();
	}

	public void enregistrerSeance(LocalDate date, Exercice exercice)
	{
		// Enregistre le temps d'entraînement
		historiqueTempsEntrainement.merge(date, exercice.getDuree(), Integer::sum);

		// Calcul approximatif des calories (à adapter selon vos besoins)
		int calories = calculerCalories(exercice);
		historiqueCalories.merge(date, calories, Integer::sum);

		// Enregistre les progressions spécifiques
		if (exercice.getType().equals("force"))
		{
			historiqueForce.put(date, exercice.getNbPointsStats()[1]);
		}
		else if (exercice.getType().equals("endurance"))
		{
			historiqueEndurance.put(date, exercice.getNbPointsStats()[0]);
		}

		// Enregistre la progression par exercice
		ArrayList<Double> progressions = progressionParExercice.getOrDefault(exercice.getNom(), new ArrayList<>());
		progressions.add(exercice.getNbXpExercice());
		progressionParExercice.put(exercice.getNom(), progressions);
	}

	private int calculerCalories(Exercice exercice)
	{
		// Calcul simplifié des calories (à adapter selon vos besoins)
		switch (exercice.getType().toLowerCase())
		{
		case "force":
			return exercice.getDuree() * 7;
		case "endurance":
			return exercice.getDuree() * 10;
		default:
			return exercice.getDuree() * 5;
		}
	}

	// Méthodes pour obtenir les statistiques
	public double getMoyenneTempsQuotidien()
	{
		return historiqueTempsEntrainement.values().stream().mapToInt(Integer::intValue).average().orElse(0.0);
	}

	public int getTotalCalories()
	{
		return historiqueCalories.values().stream().mapToInt(Integer::intValue).sum();
	}

	public HashMap<LocalDate, Double> getProgressionForce()
	{
		return historiqueForce;
	}

	public HashMap<LocalDate, Double> getProgressionEndurance()
	{
		return historiqueEndurance;
	}

	public ArrayList<Double> getProgressionExercice(String nomExercice)
	{
		return progressionParExercice.getOrDefault(nomExercice, new ArrayList<>());
	}

	public String genererRapportProgression()
	{
		StringBuilder rapport = new StringBuilder();
		rapport.append("\n=== RAPPORT DE PROGRESSION ===\n");
		rapport.append(String.format("Temps moyen d'entraînement: %.1f minutes/jour\n", getMoyenneTempsQuotidien()));
		rapport.append(String.format("Total calories brûlées: %d\n", getTotalCalories()));
		rapport.append(String.format("Nombre de séances: %d\n", historiqueTempsEntrainement.size()));
		return rapport.toString();
	}
}
