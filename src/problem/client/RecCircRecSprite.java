package problem.client;

public class RecCircRecSprite extends ComplexSprite {

	public RecCircRecSprite(double x, double y, double width, double height) {
		super(x, y, width, height);
//		RectangleSprite r1 = new RectangleSprite(x, y, width, height);
//		this.addSprite(r1);
//		CircleSprite c1 = new CircleSprite(x, y, width, height);  //Circle inside the rectangle
//		this.addSprite(c1);
//		RectangleSprite r2 = new RectangleSprite(x+5, y - SpriteFactory.HEIGHT, width - 10, height + 5);
//		this.addSprite(r2);
		TowerSprite sp1 = new TowerSprite(x, y, width, height);
		this.addSprite(sp1);
		CircleSprite r1 = new CircleSprite(x + SpriteFactory.WIDTH, y, width, height);
		this.addSprite(r1);
//		System.out.println(this.toString());
	}

}
