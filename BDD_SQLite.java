import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.ResultSet;

public class BDD_SQLite
{

	// Méthode pour se connecter à la base de données
	public static Connection connect()
	{
		String url = "jdbc:sqlite:/home/etudiant/cn230854/Documents/app solo leveling/base_de_donnees.db";
		Connection conn = null;
		try
		{
			Class.forName("org.sqlite.JDBC"); // Charge explicitement le driver
												// JDBC
			conn = DriverManager.getConnection(url);
			System.out.println("Connexion à SQLite établie.");
		} catch (ClassNotFoundException e)
		{
			System.out.println("Erreur : Driver JDBC SQLite non trouvé.");
		} catch (SQLException e)
		{
			System.out.println("Erreur de connexion : " + e.getMessage());
		}
		return conn;
	}

	// Méthode pour créer les tables
	public static void createTable()
	{
		String sqlCreationTableProfil = "CREATE TABLE IF NOT EXISTS profil (\n"
				+ " id INTEGER PRIMARY KEY AUTOINCREMENT,\n" + " pseudo TEXT UNIQUE,\n" + " poids REAL,\n"
				+ " taille REAL,\n" + " pdp TEXT,\n" + " dateCompte DATE,\n" + " dateNaissance DATE,\n"
				+ " nbXp INTEGER,\n" + " pointsEndurance INTEGER,\n" + " pointsForce INTEGER,\n"
				+ " pointsAgilite INTEGER,\n" + " pointsMental INTEGER\n" + ");";

		String sqlCreationTableExo = "CREATE TABLE IF NOT EXISTS exercice (\n"
				+ " id INTEGER PRIMARY KEY AUTOINCREMENT,\n" + " nom TEXT,\n" + " type TEXT,\n" + " discipline TEXT,\n"
				+ " dateExo DATE,\n" + " duree INTEGER,\n" + " nbXp REAL\n" + ");";

		String sqlCreationTableQuete = "CREATE TABLE IF NOT EXISTS quete (\n"
				+ " id INTEGER PRIMARY KEY AUTOINCREMENT,\n" + " type TEXT,\n" + " nb_xp_quete INTEGER,\n"
				+ " duree INTEGER,\n" + " objectifs TEXT,\n" + " nb_objectifs INTEGER,\n"
				+ " nb_objectifs_reussi INTEGER,\n" + " dateQuete DATE\n" + ");";

		String sqlDel = "DELETE FROM profil;";

		try (Connection conn = connect(); Statement stmt = conn.createStatement())
		{
			stmt.execute(sqlCreationTableProfil);
			System.out.println("Table PROFIL créée avec succès.");
			stmt.execute(sqlCreationTableExo);
			System.out.println("Table EXERCICE créée avec succès.");
			stmt.execute(sqlCreationTableQuete);
			System.out.println("Table QUETE créée avec succès.");
			stmt.execute(sqlDel);
		} catch (SQLException e)
		{
			System.out.println("Erreur lors de la création des tables : " + e.getMessage());
		}
	}

	// Méthode pour insérer des données dans la table profil
	public static void insertDataProfil(String pseudo, double poids, double taille, String pdp, LocalDate dateCompte,
			LocalDate dateNaissance, int nbXp, double pointsEndurance, double pointsForce, double pointsAgilite,
			double pointsMental)
	{
		String sqlInsertion = "INSERT INTO profil(pseudo, poids, taille, pdp, dateCompte, dateNaissance, nbXp, pointsEndurance, pointsForce, pointsAgilite, pointsMental) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = connect(); java.sql.PreparedStatement pstmt = conn.prepareStatement(sqlInsertion))
		{
			pstmt.setString(1, pseudo);
			pstmt.setDouble(2, poids);
			pstmt.setDouble(3, taille);
			pstmt.setString(4, pdp);
			pstmt.setDate(5, java.sql.Date.valueOf(dateCompte));
			pstmt.setDate(6, java.sql.Date.valueOf(dateNaissance));
			pstmt.setInt(7, nbXp);
			pstmt.setDouble(8, pointsEndurance);
			pstmt.setDouble(9, pointsForce);
			pstmt.setDouble(10, pointsAgilite);
			pstmt.setDouble(11, pointsMental);
			pstmt.executeUpdate();
			System.out.println("Données insérées avec succès.");
		} catch (SQLException e)
		{
			System.out.println("Erreur lors de l'insertion de données : " + e.getMessage());
		}
	}

	// Méthode pour insérer des données dans la table quete
	public static void insertDataQuete(String type, int nb_xp_quete, int duree, String[] objectifs, int nb_objectif,
			int nb_objectif_reussi, LocalDate dateQuete)
	{
		String sqlInsertion = "INSERT INTO quete(type, nb_xp_quete, duree, objectifs, nb_objectifs, nb_objectifs_reussi, dateQuete) VALUES(?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = connect(); java.sql.PreparedStatement pstmt = conn.prepareStatement(sqlInsertion))
		{
			pstmt.setString(1, type);
			pstmt.setInt(2, nb_xp_quete);
			pstmt.setInt(3, duree);
			pstmt.setString(4, String.join(",", objectifs));
			pstmt.setInt(5, nb_objectif);
			pstmt.setInt(6, nb_objectif_reussi);
			pstmt.setDate(7, java.sql.Date.valueOf(dateQuete));

			pstmt.executeUpdate();
			System.out.println("Données insérées avec succès.");
		} catch (SQLException e)
		{
			System.out.println("Erreur lors de l'insertion de données : " + e.getMessage());
		}
	}

	// Méthode pour insérer des données dans la table exercice
	public static void insertDataExercice(String nom, String type, String discipline, LocalDate dateExo, int duree,
			double nbXp)
	{
		String sqlInsertion = "INSERT INTO exercice(nom, type, discipline, dateExo, duree, nbXp) VALUES(?, ?, ?, ?, ?, ?)";

		try (Connection conn = connect(); java.sql.PreparedStatement pstmt = conn.prepareStatement(sqlInsertion))
		{
			pstmt.setString(1, nom);
			pstmt.setString(2, type);
			pstmt.setString(3, discipline);
			pstmt.setDate(4, java.sql.Date.valueOf(dateExo));
			pstmt.setInt(5, duree);
			pstmt.setDouble(6, nbXp);

			pstmt.executeUpdate();
			System.out.println("Données insérées avec succès.");
		} catch (SQLException e)
		{
			System.out.println("Erreur lors de l'insertion de données : " + e.getMessage());
		}
	}

	// Méthode pour sélectionner et retourner toutes les données de la table
	// profil pour un pseudo donné
	public static Object[] selectAllProfil(String pseudo)
	{
		String sqlSelection = "SELECT * FROM profil WHERE pseudo = ?";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sqlSelection))
		{

			// Définir le paramètre de pseudo
			pstmt.setString(1, pseudo);
			ResultSet rs = pstmt.executeQuery();

			// Si un résultat est trouvé, on initialise le tableau avec les
			// informations du
			// profil
			if (rs.next())
			{
				Object[] profilData = new Object[11]; // Crée un tableau pour
														// stocker les
														// informations

				profilData[0] = rs.getString("pseudo");
				profilData[1] = rs.getDouble("poids");
				profilData[2] = rs.getDouble("taille");
				profilData[3] = rs.getString("pdp");
				profilData[4] = rs.getDate("dateCompte").toLocalDate();
				profilData[5] = rs.getDate("dateNaissance").toLocalDate();
				profilData[6] = rs.getInt("nbXp");
				profilData[7] = rs.getInt("pointsEndurance");
				profilData[8] = rs.getInt("pointsForce");
				profilData[9] = rs.getInt("pointsAgilite");
				profilData[10] = rs.getInt("pointsMental");

				return profilData; // Retourne le tableau avec les données
			}
			else
			{
				System.out.println("Aucun profil trouvé pour le pseudo : " + pseudo);
			}

		} catch (SQLException e)
		{
			System.out.println("Erreur lors de la sélection de données : " + e.getMessage());
		}

		return null; // Retourne null si aucun profil n'a été trouvé ou en cas
						// d'erreur
	}
}
/*
 * public static void main(String[] args) { // createTable(); // Créer les
 * tables // insertDataProfil("Edwin", 55.5, 1.55, "", LocalDate.of(2005, 02,
 * 28), LocalDate.of(2005, 02, 28), 0, 0, 0, 0, 0); // Insérer des données //
 * insertDataQuete("endurance", 700, 45, new String[]{""}, 0, 0,
 * LocalDate.now());; // insertDataExercice("séance de muscu", "force",
 * "musculation", LocalDate.now(), 55, 300); selectAllProfil("Edwin"); //
 * Afficher toutes les données du profil de Edwin
 * 
 * } }
 */