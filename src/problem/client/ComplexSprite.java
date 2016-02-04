package problem.client;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ComplexSprite extends AbstractSprite{
	List<ISprite> spriteList;

	public ComplexSprite(double x, double y, double width, double height) {
		// TODO Auto-generated constructor stub
		super(x, y, width, height);
		this.spriteList = new ArrayList<ISprite>();
	}

	@Override
	public void move(Dimension space) {
		// TODO Auto-generated method stub
//		for (int i = 0; i < spriteList.size(); i++) {
//			this.spriteList.get(i).move(space);
//		}
		for (ISprite i : spriteList) {
			i.move(space);
		}
	}

	@Override
	public Iterator<ISprite> iterator() {
		// TODO Auto-generated method stub
		ComplexSpriteIterator it = new ComplexSpriteIterator(this.spriteList.iterator());
		return it;
//		return this.spriteList.iterator();
	}
	
	@Override
	public void addSprite(ISprite s) {
		this.spriteList.add(s);
	}

	@Override
	public void removeSprite(ISprite s) {
		this.spriteList.remove(s);
	}
	
	@Override
	public ISprite getChild(int i) {
		return this.spriteList.get(i);
	}

}
