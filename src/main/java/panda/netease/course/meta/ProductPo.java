package panda.netease.course.meta;

/**
 * 商品实体类po，与数据库表字段一一对应
 * @author panda
 *
 */
public class ProductPo {
	
	private int id; //内容id
	private int price; //内容价格
	private String title; //内容标题
	private String image; //图片地址
	private String abs; //内容摘要
	private String text; //内容正文
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getAbs() {
		return abs;
	}
	
	public void setAbs(String abs) {
		this.abs = abs;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
}
