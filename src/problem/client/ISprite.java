package problem.client;

import java.awt.Dimension;
import java.awt.Shape;

public interface ISprite extends Iterable<ISprite>{
	public void move(Dimension space);
	public Shape getShape();
	public void addSprite(ISprite s);
	public void removeSprite(ISprite s);
	public ISprite getChild(int i);
}
