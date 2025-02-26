import java.time.LocalDate;

public class Quete
{
	private String type; // quotidienne, endurance, force, ...
	private int nb_xp_quete;
	private int duree;
	private String[] objectifs;
	private int nb_objectif;
	private int nb_objectif_reussi;
	private LocalDate dateQuete;

	public Quete(String type, int nb_objectif, String[] objectifs, LocalDate dateQuete)
	{
		this.type               = type;
		this.nb_objectif        = nb_objectif;
		this.nb_objectif_reussi = 0;
		this.objectifs          = objectifs;
		this.dateQuete          = dateQuete;

		switch (type)
		{
		case "quotidienne":
			this.nb_xp_quete = 100;
			this.duree = 5;
			break;

		case "endurance":
			this.nb_xp_quete = 1000;
			this.duree = 60;
			break;

		case "force":
			this.nb_xp_quete = 500;
			this.duree = 15;
			break;

		case "finale":
			this.nb_xp_quete = 10000;
			this.duree = setDureeQueteSerie();
			break;

		case "intermediaire":
			this.nb_xp_quete = 700;
			this.duree = setDureeQueteSerie();
			break;

		default:
			System.out.println("probleme declaration de Quete avec type");
			break;
		}
	}

	public LocalDate getDateQuete() {return this.dateQuete;}
	public String[]  getObjectifs() {return this.objectifs;}
	public int       getNbXpQuete() {return this.nb_xp_quete;}
	public String    getType() {return this.type;}
	public int       getDuree() {return this.duree;}
	public int       getNbObjectif() {return this.nb_objectif;}
	public int       getNbOgjectifReussi() {return this.nb_objectif_reussi;}

	public int setDureeQueteSerie()
	{
		int rand = (int) (Math.random() * 4);
		int duree = 0;
		if (this.type == "finale")
		{
			switch (rand)
			{
			case 0:
				duree = 60;
				break;

			case 1:
				duree = 90;
				break;

			case 2:
				duree = 120;
				break;

			case 3:
				duree = 150;
				break;

			default:
				System.out.println("probleme SetDureeQueteSerie avec random (1)");
				break;
			}
		}
		else
		{
			switch (rand)
			{
			case 0:
				duree = 15;
				break;

			case 1:
				duree = 30;
				break;

			case 2:
				duree = 45;
				break;

			case 3:
				duree = 60;
				break;

			default:
				System.out.println("probleme SetDureeQueteSerie avec random (2)");
				break;
			}
		}
		return duree;
	}

	public double[] getNbPointsStats()
	{
		double tabPoints[] = new double[4];
		double tabCoeffPoints[] = new double[4]; // e/f/m/a

		switch (this.type)
		{
		case "quotidienne":
			tabCoeffPoints[0] = 0.01;
			tabCoeffPoints[1] = 0.01;
			tabCoeffPoints[2] = 0.01;
			tabCoeffPoints[3] = 0.01;
			break;

		case "endurance":
			tabCoeffPoints[0] = 0.012;
			tabCoeffPoints[1] = 0.002;
			tabCoeffPoints[2] = 0.006;
			tabCoeffPoints[3] = 0.001;
			break;

		case "force":
			tabCoeffPoints[0] = 0.002;
			tabCoeffPoints[1] = 0.06;
			tabCoeffPoints[2] = 0.005;
			tabCoeffPoints[3] = 0.002;
			break;

		case "final":
			tabPoints[0] = 5;
			tabPoints[1] = 5;
			tabPoints[2] = 5;
			tabPoints[3] = 3;
			break;

		case "intermediaire":
			tabCoeffPoints[0] = 0.04;
			tabCoeffPoints[1] = 0.04;
			tabCoeffPoints[2] = 0.04;
			tabCoeffPoints[3] = 0.04;
			break;

		default:
			System.out.println("probleme getNbPointsStats avec type");
			break;
		}

		if (this.type != "final")
		{
			for (int i = 0; i < tabPoints.length; i++)
			{
				tabPoints[i] = this.duree * tabCoeffPoints[i];
			}
			return tabPoints;
		}

		return tabPoints;
	}

	public boolean queteTerminee()
	{
		return (nb_objectif == nb_objectif_reussi);
	}

	public String toString()
	{
		double[] tabPointsEx = getNbPointsStats();
		String retour = "";

		retour += "##################################################" + "\n QUETE \n"
				+ "################################################## \n";
		retour += "# Type                 : " + this.type + '\n';
		retour += "# Xp                   : " + this.nb_xp_quete + '\n';
		retour += "# Duree                : " + this.duree + '\n';
		retour += "# Nb objectifs         : " + this.nb_objectif + '\n';
		retour += "# Nb objectifs reussis : " + this.nb_objectif_reussi + '\n';
		retour += "# Points               : " + tabPointsEx[0] + 
		"\n#                        " + tabPointsEx[1] + 
		"\n#                        " + tabPointsEx[2] + 
		"\n#                        " + tabPointsEx[3] + "\n" + "\n" + "\n";

		return retour;
	}
}
