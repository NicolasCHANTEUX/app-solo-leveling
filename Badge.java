public class Badge
{
	private String nom;
	private String description;
	private String niveau; // BRONZE, ARGENT, OR
	private String categorie;
	private boolean debloque;
	private String imageUrl;

	public Badge(String nom, String description, String niveau, String categorie, String imageUrl)
	{
		this.nom = nom;
		this.description = description;
		this.niveau = niveau;
		this.categorie = categorie;
		this.debloque = false;
		this.imageUrl = imageUrl;
	}

	public void debloquer()
	{
		this.debloque = true;
	}

	// Getters
	public String getNom()
	{
		return nom;
	}

	public String getDescription()
	{
		return description;
	}

	public String getNiveau()
	{
		return niveau;
	}

	public String getCategorie()
	{
		return categorie;
	}

	public boolean estDebloque()
	{
		return debloque;
	}

	public String getImageUrl()
	{
		return imageUrl;
	}
}
