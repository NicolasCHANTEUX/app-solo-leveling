import java.util.HashMap;
import java.util.ArrayList;

public class BibliothequeExercices
{
	private static HashMap<String, ArrayList<ExerciceDetails>> exercicesParCategorie;

	public BibliothequeExercices()
	{
		exercicesParCategorie = new HashMap<>();
		initialiserExercices();
	}

	private void initialiserExercices()
	{
		// Exercices de Force
		ArrayList<ExerciceDetails> exercicesForce = new ArrayList<>();
		exercicesForce.add(new ExerciceDetails("Pompes", "force", "Exercice de push-up classique",
				"https://example.com/pompes.mp4", new String[] { "débutant", "intermédiaire", "avancé" },
				new int[] { 10, 20, 30 }));
		exercicesForce.add(new ExerciceDetails("Tractions", "force", "Tractions à la barre fixe",
				"https://example.com/tractions.mp4", new String[] { "débutant", "intermédiaire", "avancé" },
				new int[] { 5, 10, 15 }));
		exercicesParCategorie.put("force", exercicesForce);

		// Exercices d'Endurance
		ArrayList<ExerciceDetails> exercicesEndurance = new ArrayList<>();
		exercicesEndurance
				.add(new ExerciceDetails("Course", "endurance", "Course à pied", "https://example.com/course.mp4",
						new String[] { "débutant", "intermédiaire", "avancé" }, new int[] { 10, 20, 30 } // minutes
				));
		exercicesParCategorie.put("endurance", exercicesEndurance);
	}

	public ArrayList<ExerciceDetails> getExercicesParCategorie(String categorie)
	{
		return exercicesParCategorie.getOrDefault(categorie, new ArrayList<>());
	}

	public ExerciceDetails getExerciceParNom(String nom)
	{
		for (ArrayList<ExerciceDetails> categorie : exercicesParCategorie.values())
		{
			for (ExerciceDetails exercice : categorie)
			{
				if (exercice.getNom().equalsIgnoreCase(nom))
				{
					return exercice;
				}
			}
		}
		return null;
	}
}
