package problem.client;

import java.util.Iterator;
import java.util.Stack;

public class ComplexSpriteIterator implements Iterator<ISprite>{
//	private Queue<Iterator<ISprite>> q;
	private Stack<Iterator<ISprite>> stack = new Stack<Iterator<ISprite>>();
	
	public ComplexSpriteIterator(Iterator<ISprite> iterator) {
		// TODO Auto-generated constructor stub
//		this.q = new LinkedList<Iterator<ISprite>>();
//		this.q.add(iterator);
		this.stack.push(iterator);
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
//		if (q.isEmpty()) return false; //Check for no items on queue
//		else if (!q.isEmpty()) {
//			if (!this.hasNext()) {
//				this.q.remove();
//				return hasNext();
//			} else {
//				return true;
//			}
//		}
//		return false;		
		// Slides are so useful here
		if (this.stack.isEmpty()) {
			return false;
		}
		else {
			Iterator<ISprite> it = this.stack.peek();
			if (!it.hasNext()) {
				stack.pop();
				return hasNext();
			}
			else {
				return true;
			}
		}
	}

	@Override
	public ISprite next() {
//		if (hasNext()) {
//			return this.next();
//		} else{
//			return null;
//		}
		
		if(hasNext()) {
			Iterator<ISprite> iterator = this.stack.peek();
			ISprite sprite = iterator.next();
			this.stack.push(sprite.iterator());
			return sprite;
		}
		else {
			return null;
		}
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
