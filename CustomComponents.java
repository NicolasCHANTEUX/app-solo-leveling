import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class CustomComponents
{

	public static class RoundedButton extends JButton
	{
		private Color backgroundColor;
		private Color hoverColor;

		public RoundedButton(String text)
		{
			super(text);
			setOpaque(false);
			setContentAreaFilled(false);
			setFocusPainted(false);
			setBorderPainted(false);

			ThemeManager.ThemeColors colors = ThemeManager.getCurrentThemeColors();
			backgroundColor = colors.accent;
			hoverColor = colors.accent.brighter();

			setForeground(Color.WHITE);

			addMouseListener(new java.awt.event.MouseAdapter()
			{
				public void mouseEntered(java.awt.event.MouseEvent evt)
				{
					backgroundColor = hoverColor;
					repaint();
				}

				public void mouseExited(java.awt.event.MouseEvent evt)
				{
					backgroundColor = colors.accent;
					repaint();
				}
			});
		}

		@Override
		protected void paintComponent(Graphics g)
		{
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			// Mise à jour des couleurs avec le thème actuel
			ThemeManager.ThemeColors colors = ThemeManager.getCurrentThemeColors();
			backgroundColor = colors.accent;
			hoverColor = colors.accent.brighter();

			// Dessiner le fond arrondi
			g2.setColor(backgroundColor);
			g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));

			// Centrer le texte
			FontMetrics metrics = g2.getFontMetrics(getFont());
			int x = (getWidth() - metrics.stringWidth(getText())) / 2;
			int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
			g2.setColor(Color.WHITE);
			g2.drawString(getText(), x, y);

			g2.dispose();
		}
	}

	public static class ProgressBar extends JProgressBar
	{
		private Color progressColor;

		public ProgressBar()
		{
			setOpaque(false);
			setBorderPainted(false);
			ThemeManager.ThemeColors colors = ThemeManager.getCurrentThemeColors();
			progressColor = colors.accent;
		}

		@Override
		protected void paintComponent(Graphics g)
		{
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			// Mise à jour de la couleur avec le thème actuel
			ThemeManager.ThemeColors colors = ThemeManager.getCurrentThemeColors();
			progressColor = colors.accent;

			// Fond
			g2.setColor(colors.secondary); // Utiliser la couleur secondaire du
											// thème
			g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 10, 10));

			// Barre de progression
			g2.setColor(progressColor);
			int width = (int) (getWidth() * (getPercentComplete()));
			g2.fill(new RoundRectangle2D.Float(0, 0, width, getHeight(), 10, 10));

			// Afficher le pourcentage si stringPainted est true
			if (isStringPainted())
			{
				String progressString = getString();
				FontMetrics metrics = g2.getFontMetrics(getFont());
				int x = (getWidth() - metrics.stringWidth(progressString)) / 2;
				int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
				g2.setColor(colors.text);
				g2.drawString(progressString, x, y);
			}

			g2.dispose();
		}
	}
}
