public class ExerciceDetails
{
	private String nom;
	private String categorie;
	private String description;
	private String urlVideo;
	private String[] niveauxDifficulte;
	private int[] objectifsParNiveau;

	public ExerciceDetails(String nom, String categorie, String description, String urlVideo,
			String[] niveauxDifficulte, int[] objectifsParNiveau)
	{
		this.nom = nom;
		this.categorie = categorie;
		this.description = description;
		this.urlVideo = urlVideo;
		this.niveauxDifficulte = niveauxDifficulte;
		this.objectifsParNiveau = objectifsParNiveau;
	}

	public String getNom()
	{
		return nom;
	}

	public String getCategorie()
	{
		return categorie;
	}

	public String getDescription()
	{
		return description;
	}

	public String getUrlVideo()
	{
		return urlVideo;
	}

	public int getObjectifPourNiveau(String niveau)
	{
		for (int i = 0; i < niveauxDifficulte.length; i++)
		{
			if (niveauxDifficulte[i].equals(niveau))
			{
				return objectifsParNiveau[i];
			}
		}
		return objectifsParNiveau[0]; // retourne l'objectif débutant par défaut
	}
}
