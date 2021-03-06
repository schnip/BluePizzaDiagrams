package problem.client;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class AbstractSprite implements ISprite {
	protected double dx;
	protected double dy;
	protected Shape shape;
	protected ArrayList<ISprite> sprites;

	// Subclasses need to chain this constructor
	public AbstractSprite(double x, double y, double width, double height) {
		this.dx = SpriteFactory.DX;
		this.dy = SpriteFactory.DY;
		this.sprites = new ArrayList<ISprite>();
	}

	
	// Designed to be used by subclasses
	protected final Rectangle2D computeNewBoundsAfterMoving(Dimension space) {
		Rectangle2D bounds = shape.getBounds2D();
		
		if(bounds.getX() < 0 || bounds.getX() > space.getWidth())
			dx = -dx;

		if(bounds.getY() < 0 || bounds.getY() > space.getHeight())
			dy = -dy;
		
		Rectangle2D newBounds = new Rectangle2D.Double(bounds.getX() + dx,
														bounds.getY() + dy,
														bounds.getWidth(),
														bounds.getHeight());
		return newBounds;
	}
	
	@Override
	public final Shape getShape() {
		return this.shape;
	}
	
	@Override
	public Iterator<ISprite> iterator() {
		// TODO Auto-generated method stub
		return new NullIterator();
	}
	
	@Override
	public void addSprite(ISprite s) {
		// TODO Auto-generated method stub
		// Doesn't do anything
		this.sprites.add(s);

	}

	@Override
	public void removeSprite(ISprite s) {
		// TODO Auto-generated method stub
		// Doesn't do anything
		this.sprites.add(s);

	}
	
	@Override
	public ISprite getChild(int i) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public abstract void move(Dimension space);
}
