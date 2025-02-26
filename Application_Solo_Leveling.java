/*
JEU TYPE SOLO LEVELING :

CARACTERISTIQUES PRINCIPALES
	- cota quotidient de sport ( pompes + squats + abdos + courses ) sous forme de liste à cocher 
	- stats du compte ( caractéristques perso : poids, taille, age + date compte + défis complétés + niveau + photo de profil )
	- niveau : grace aux quetes quotidiennes + les défis 
	- pourcentage sur chaque partie du corps 
	- 

PROFIL
QUETES QUOTIDIENNES
DEFIS 
NIVEAUX

 */

import java.time.LocalDate;

public class Application_Solo_Leveling
{
	public static void main(String[] args)
	{
		// TODO code application logic here
		System.out.println(toString("debut"));

		Profil profil = addProfil();
		System.out.println(profil.toString());

		addQuete(profil);
		addExercice(profil);

		System.out.println(profil.toString());
		IHM_Solo_Leveling_Swing IHM = new IHM_Solo_Leveling_Swing(profil);

		// profil.afficheListExo();
		// profil.afficheListQuetes();
	}

	public static Profil addProfil()
	{
		Profil profil = new Profil("Nicolas", 63, 177, "./images/image.png", LocalDate.of(2005, 02, 28));
		BDD_SQLite.insertDataProfil(profil.getPseudo(), profil.getPoids(), profil.getTaille(), profil.getPdp(),
				profil.getDateCompte(), profil.getDateNaissance(), profil.getNbXp(), profil.getPointsEndurance(),
				profil.getPointsForce(), profil.getPointsAgilite(), profil.getPointsMental());
		return profil;
	}

	public static void addQuete(Profil profil)
	{
		Quete quete = new Quete("intermediaire", 0, new String[] { "" }, LocalDate.now());
		System.out.println(quete.toString());
		BDD_SQLite.insertDataQuete(quete.getType(), quete.getNbXpQuete(), quete.getDuree(), quete.getObjectifs(),
				quete.getNbObjectif(), quete.getNbOgjectifReussi(), quete.getDateQuete());
		if (profil.addQuete(quete))
			System.out.println("Quêtes terminée ajoutée avec succès");
	}

	public static void addExercice(Profil profil)
	{
		Exercice exercice = new Exercice("séance de muscu", "force", "course à pied", 45);
		System.out.println(exercice.toString());
		BDD_SQLite.insertDataExercice(exercice.getNom(), exercice.getType(), exercice.getDiscipline(),
				exercice.getDateExo(), exercice.getDuree(), exercice.getNbXp());
		profil.addExercice(exercice);
	}

	public static String toString(String message)
	{
		String retour = "";
		switch (message)
		{
		case "debut":
			retour = "\nBienvenue dans le jeu, \nDonner tout ce que vous avez !\n";
			break;

		case "fin":
			retour = "\nA bientot j'espere !\n";
			break;

		default:
			break;
		}
		return retour;
	}

}