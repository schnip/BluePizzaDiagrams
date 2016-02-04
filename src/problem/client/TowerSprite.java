package problem.client;

public class TowerSprite extends ComplexSprite {

	public TowerSprite(double x, double y, double width, double height) {
		super(x, y, width, height);
		RectangleSprite r1 = new RectangleSprite(x, y, width, height);
		this.addSprite(r1);
		RectangleSprite r2 = new RectangleSprite(x + 5, y - SpriteFactory.HEIGHT, width - 10, height+5);
		this.addSprite(r2);
		RectangleSprite r3 = new RectangleSprite(x + 10, y - (2 * SpriteFactory.HEIGHT), width - (2*10), height+10);
		this.addSprite(r3);
		
	}

}
