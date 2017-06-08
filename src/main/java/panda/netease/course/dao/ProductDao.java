package panda.netease.course.dao;

import java.util.List;

import panda.netease.course.meta.ProductTo;
import panda.netease.course.meta.ProductPo;
import panda.netease.course.meta.Transaction;

/**
 * 商品数据访问对象
 * @author panda
 *
 */
public interface ProductDao {

	/**
	 * 获取所有商品信息
	 * @return
	 */
	public List<ProductTo> getProductList();
	
	/**
	 * 获取指定id商品包含成交记录的所有信息（用于商品查看页）
	 * @param id
	 * @return
	 */
	public List<ProductTo> getProductInfoList(int id);
	
	/**
	 * 获取指定id商品信息
	 * @param id
	 * @return
	 */
	public ProductPo getProduct(int id);
	
	/**
	 * 获取买家购买的商品列表
	 * @param personId
	 * @return
	 */
	public List<ProductTo> getProductListById(int personId);
	
	/**
	 * 新增商品
	 * @param product
	 */
	public void addProduct(ProductPo product);
	
	/**
	 * 修改商品信息
	 * @param product
	 */
	public void editProduct(ProductPo product);
	
	/**
	 * 删除商品
	 * @param id
	 * @return
	 */
	public int deleteProduct(int id);
	
	/**
	 * 购买商品
	 * @param list
	 * @return
	 */
	public int buyProduct(List<Transaction> list);
}
