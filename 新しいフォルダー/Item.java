package kadai7add;

public class Item {
	private String name;
	private int price;
	
	// デフォルトコンストラクタ
	Item() {
		
	}
	
	Item(String name, int price) {
		this.name = name;
		this.price = price;
	}
	
	// ゲッター・セッター
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setItem(String name, int price) {
		this.name = name;
		this.price = price;
	}
	
	public void showInfo() {
		System.out.println(name + "/" + price);
	}
}
