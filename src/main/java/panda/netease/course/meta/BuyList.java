package panda.netease.course.meta;

/**
 * 商品购买传输对象，用于接收购物车信息
 * @author panda
 *
 */
public class BuyList {

	private int id; //记录购买的商品ID
	
	private int number; //记录购买的商品数量

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
}
