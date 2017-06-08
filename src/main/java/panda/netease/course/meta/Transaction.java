package panda.netease.course.meta;

/**
 * 订单实体类
 * @author panda
 *
 */
public class Transaction {

	private int id; //ID
	private int contentId; //商品ID
	private int userId; //用户ID
	private int price; //购买价格
	private long time; //购买时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

}
