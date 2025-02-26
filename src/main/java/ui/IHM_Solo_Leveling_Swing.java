import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class IHM_Solo_Leveling_Swing extends JFrame
{
	private Profil profil;
	private ThemeManager themeManager;
	private JPanel mainPanel;
	private JTabbedPane tabbedPane;

	public IHM_Solo_Leveling_Swing(Profil profil)
	{
		this.profil = profil;
		this.themeManager = new ThemeManager();
		initializeUI();
	}

	private void initializeUI()
	{
		setTitle("Solo Leveling - " + profil.getPseudo());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		setLocationRelativeTo(null);

		mainPanel = new JPanel(new BorderLayout());
		tabbedPane = new JTabbedPane();

		// Ajouter les onglets
		tabbedPane.addTab("Profil", createProfilPanel());
		tabbedPane.addTab("Exercices", createExercicesPanel());
		tabbedPane.addTab("Statistiques", createStatsPanel());
		tabbedPane.addTab("Récompenses", createRecompensesPanel());

		// Barre d'outils
		JToolBar toolBar = createToolBar();
		mainPanel.add(toolBar, BorderLayout.NORTH);
		mainPanel.add(tabbedPane, BorderLayout.CENTER);

		applyTheme();
		add(mainPanel);
		setVisible(true);
	}

	private JToolBar createToolBar()
	{
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);

		JButton themeButton = new CustomComponents.RoundedButton("Changer de thème");
		themeButton.addActionListener(e -> cycleTheme());

		toolBar.add(themeButton);
		return toolBar;
	}

	private JPanel createProfilPanel()
	{
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Panel pour les informations personnelles
		JPanel infoPanel = new JPanel(new GridLayout(0, 2, 10, 5));
		infoPanel.setBorder(BorderFactory.createTitledBorder("Informations personnelles"));

		// Informations de base
		infoPanel.add(new JLabel("Pseudo:"));
		infoPanel.add(new JLabel(profil.getPseudo()));
		infoPanel.add(new JLabel("Poids:"));
		infoPanel.add(new JLabel(profil.getPoids() + " kg"));
		infoPanel.add(new JLabel("Taille:"));
		infoPanel.add(new JLabel(profil.getTaille() + " cm"));
		infoPanel.add(new JLabel("Date de naissance:"));
		infoPanel.add(new JLabel(profil.getDateNaissance().toString()));
		infoPanel.add(new JLabel("Date de création du compte:"));
		infoPanel.add(new JLabel(profil.getDateCompte().toString()));
		infoPanel.add(new JLabel("Niveau:"));
		infoPanel.add(new JLabel(String.valueOf(profil.getNiveau())));
		infoPanel.add(new JLabel("Expérience:"));
		infoPanel.add(new JLabel(String.valueOf(profil.getNbXp()) + " XP"));
		infoPanel.add(new JLabel("Titre actuel:"));
		infoPanel.add(new JLabel(profil.getTitreActuel()));

		// Panel pour les statistiques
		JPanel statsPanel = new JPanel(new GridLayout(0, 1, 5, 5));
		statsPanel.setBorder(BorderFactory.createTitledBorder("Statistiques"));

		// Barres de progression
		addProgressBar(statsPanel, "Force", profil.getPointsForce());
		addProgressBar(statsPanel, "Endurance", profil.getPointsEndurance());
		addProgressBar(statsPanel, "Agilité", profil.getPointsAgilite());
		addProgressBar(statsPanel, "Mental", profil.getPointsMental());

		// Organisation du panneau principal
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(infoPanel, BorderLayout.NORTH);

		panel.add(topPanel, BorderLayout.NORTH);
		panel.add(statsPanel, BorderLayout.CENTER);

		return panel;
	}

	private void addProgressBar(JPanel panel, String label, double value)
	{
		JPanel barPanel = new JPanel(new BorderLayout(5, 0));
		barPanel.add(new JLabel(label), BorderLayout.WEST);

		CustomComponents.ProgressBar progressBar = new CustomComponents.ProgressBar();
		progressBar.setValue((int) value);
		progressBar.setStringPainted(true);

		barPanel.add(progressBar, BorderLayout.CENTER);
		panel.add(barPanel);
	}

	private JPanel createExercicesPanel()
	{
		// Implémentez le panneau des exercices
		return new JPanel();
	}

	private JPanel createStatsPanel()
	{
		// Implémentez le panneau des statistiques
		return new JPanel();
	}

	private JPanel createRecompensesPanel()
	{
		// Implémentez le panneau des récompenses
		return new JPanel();
	}

	private void cycleTheme()
	{
		String[] themes = { "light", "dark", "gaming" };
		String currentTheme = ThemeManager.getCurrentThemeColors().toString();
		int nextIndex = 0;
		for (int i = 0; i < themes.length; i++)
		{
			if (themes[i].equals(currentTheme))
			{
				nextIndex = (i + 1) % themes.length;
				break;
			}
		}
		ThemeManager.setTheme(themes[nextIndex]);
		applyTheme();
	}

	private void applyTheme()
	{
		ThemeManager.ThemeColors colors = ThemeManager.getCurrentThemeColors();
		mainPanel.setBackground(colors.background);
		tabbedPane.setBackground(colors.background);
		tabbedPane.setForeground(colors.text);

		// Mettre à jour tous les composants
		SwingUtilities.updateComponentTreeUI(this);
	}
}
