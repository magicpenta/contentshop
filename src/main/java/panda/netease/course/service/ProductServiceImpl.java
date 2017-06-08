package panda.netease.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import panda.netease.course.dao.ProductDao;
import panda.netease.course.meta.ProductTo;
import panda.netease.course.meta.ProductPo;
import panda.netease.course.meta.Transaction;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao productDao;
	
	public List<ProductTo> getProductList() {
		return productDao.getProductList();
	}
	
	public void addProduct(ProductPo product) {
		productDao.addProduct(product);
	}

	public List<ProductTo> getProductInfoList(int id) {
		return productDao.getProductInfoList(id);
	}

	public ProductPo getProduct(int id) {
		return productDao.getProduct(id);
	}
	
	public List<ProductTo> getProductListById(int personId) {
		return productDao.getProductListById(personId);
	}

	public void editProduct(ProductPo product) {
		productDao.editProduct(product);
	}
	
	public int deleteProduct(int id) {
		return productDao.deleteProduct(id);
	}
	
	public int buyProduct(List<Transaction> list) {
		return productDao.buyProduct(list);
	}
}
