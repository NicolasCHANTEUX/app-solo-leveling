import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ThemeManager
{
	private static HashMap<String, ThemeColors> themes;
	private static String currentTheme;

	public static class ThemeColors
	{
		Color background;
		Color foreground;
		Color accent;
		Color secondary;
		Color text;

		public ThemeColors(Color background, Color foreground, Color accent, Color secondary, Color text)
		{
			this.background = background;
			this.foreground = foreground;
			this.accent = accent;
			this.secondary = secondary;
			this.text = text;
		}
	}

	public ThemeManager()
	{
		themes = new HashMap<>();
		initializeThemes();
		currentTheme = "light";
	}

	private void initializeThemes()
	{
		// Thème clair
		themes.put("light", new ThemeColors(new Color(240, 240, 240), // background
				new Color(255, 255, 255), // foreground
				new Color(66, 133, 244), // accent
				new Color(189, 189, 189), // secondary
				new Color(33, 33, 33) // text
		));

		// Thème sombre
		themes.put("dark", new ThemeColors(new Color(33, 33, 33), // background
				new Color(50, 50, 50), // foreground
				new Color(103, 58, 183), // accent
				new Color(66, 66, 66), // secondary
				new Color(255, 255, 255) // text
		));

		// Thème "gaming"
		themes.put("gaming", new ThemeColors(new Color(18, 18, 18), // background
				new Color(30, 30, 30), // foreground
				new Color(255, 0, 128), // accent
				new Color(0, 255, 255), // secondary
				new Color(240, 240, 240) // text
		));
	}

	public static ThemeColors getCurrentThemeColors()
	{
		return themes.get(currentTheme);
	}

	public static void setTheme(String themeName)
	{
		if (themes.containsKey(themeName))
		{
			currentTheme = themeName;
		}
	}
}
