package panda.netease.course.web.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import panda.netease.course.meta.ProductTo;
import panda.netease.course.meta.User;
import panda.netease.course.service.ProductService;

/**
 * 商品展示controller
 * @author panda
 *
 */
@Controller
public class ShowController {
	
	private static Logger logger = Logger.getLogger(ShowController.class);
	
	@Autowired
	private ProductService productService;

	/**
	 * 网站首页
	 * @param user, product, request, session, model
	 * @return String
	 */
	@RequestMapping("/")
	public String showHomePage(User user, ProductTo product, HttpServletRequest request, HttpSession session, Model model) {
		logger.info("**访问首页**");
		// 1.获取用户session数据
		user = (User)session.getAttribute("user");
		// 2.获取及初始化内容数据
		List<ProductTo> productList = productService.getProductList();
		if ( user != null ) {
			// 如果为买家，判断是否已购买
			if ( user.getUserType()==0 ) {
				for( ProductTo p : productList ) {
					if ( p.getId()==p.getContentId() ) {
						p.setBuy(true);
					} else {
						p.setBuy(false);
					}
				}
			}
			// 如果为卖家，判断是否已售出
			if ( user.getUserType()==1 ) {
				for( ProductTo p : productList ) {
					if ( p.getId()==p.getContentId() ) {
						p.setSell(true);
					} else {
						p.setSell(false);
					}
				}
			}
		}
		// 3.处理用户类别参数 type
		HashMap<String, Integer> rp = new HashMap<String, Integer>();
		int type = 0;
		if ( request.getParameter("type") == null ) {
			type = 0;
		} else {
			type = Integer.parseInt(request.getParameter("type"));
		}
		rp.put("type", type);
		// 4.将数据添加到模型中，并返回视图
		model.addAttribute("user", user);
		model.addAttribute("RequestParameters", rp);
		model.addAttribute("productList", productList);
		return "index";
	}
	
	/**
	 * 商品查看页面
	 * @param id,model
	 * @return String
	 */
	@RequestMapping("/show")
	public String show(@RequestParam("id") int id, Model model) {
		logger.info("**访问商品明细页面**");
		int count = 0;	// 记录商品购买数量
		ProductTo product = null;	// 返回给页面的product对象
		List<ProductTo> productInfoList = productService.getProductInfoList(id);	// 获取该商品所有成交记录与信息
		// 1.计算商品购买数量
		for( ProductTo p : productInfoList ) {
			if ( p.getId()==p.getContentId() ) {
				count++;
			}
		}
		// 2.获取商品信息
		if ( !productInfoList.isEmpty() ) {
			product = productInfoList.get(0);
		}
		// 3.确认商品购买数量
		product.setBuyNum(count);
		// 4.确认商品是否已购买
		if ( product.getId()==product.getContentId() ) {
			product.setBuy(true);
		} else {
			product.setBuy(false);
		}
		// 5.返回模型与视图
		model.addAttribute("product", product);
		return "show";
	}
}
