package newRacing;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.net.URI;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGUniverse;

public class ImageLoader {

	private final static AffineTransform scaleXform = new AffineTransform();
	private final static boolean scaleToFit = true;
	private final static boolean antiAlias = true;

	/**
	 * @param pathToSVGFile
	 *            e.g. "/tracks/monaco/track.svg"
	 * @param width
	 *            int declaring required width
	 * @param height
	 *            int declaring required height
	 * @return the successfully rendered BufferedImage, null otherwise
	 */
	public static BufferedImage getSVGImage(final String pathToSVGFile,
			final int width, final int height) {
		final BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		try {
			final SVGUniverse uni = new SVGUniverse();
			final URI svgURI = uni.loadSVG(ImageLoader.class
					.getResource(pathToSVGFile));
			paintImage((Graphics2D) image.getGraphics(), new Dimension(width,
					height), uni, svgURI);
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
		return createCompatibleImage(image, true);
	}

	private static void paintImage(final Graphics2D g, final Dimension dim,
			final SVGUniverse uni, final URI svgURI) {
		final Object oldAliasHint = g
				.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				antiAlias ? RenderingHints.VALUE_ANTIALIAS_ON
						: RenderingHints.VALUE_ANTIALIAS_OFF);

		final SVGDiagram diagram = uni.getDiagram(svgURI);
		if (diagram == null) {
			return;
		}

		if (!scaleToFit) {
			try {
				diagram.render(g);
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						oldAliasHint);
			} catch (final SVGException e) {
				throw new RuntimeException(e);
			}
			return;
		}

		final int width = dim.width;
		final int height = dim.height;

		final Rectangle2D.Double rect = new Rectangle2D.Double();
		diagram.getViewRect(rect);

		scaleXform.setToScale(width / rect.width, height / rect.height);

		final AffineTransform oldXform = g.getTransform();
		g.transform(scaleXform);

		try {
			diagram.render(g);
		} catch (final SVGException e) {
			throw new RuntimeException(e);
		}

		g.setTransform(oldXform);

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, oldAliasHint);
	}

	private static BufferedImage createCompatibleImage(final BufferedImage img,
			final boolean translucent) {
		final GraphicsEnvironment e = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		final GraphicsDevice d = e.getDefaultScreenDevice();
		final GraphicsConfiguration c = d.getDefaultConfiguration();
		final int t = translucent ? Transparency.TRANSLUCENT
				: Transparency.BITMASK;

		final BufferedImage ret = c.createCompatibleImage(img.getWidth(),
				img.getHeight(), t);

		final Graphics2D g = ret.createGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();
		return ret;
	}
}
