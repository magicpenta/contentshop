package panda.netease.course.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import panda.netease.course.meta.BuyList;
import panda.netease.course.meta.ProductTo;
import panda.netease.course.meta.Transaction;
import panda.netease.course.meta.User;
import panda.netease.course.service.ProductService;
import panda.netease.course.service.TrxService;

/**
 * 买家相关controller
 * @author panda
 *
 */
@Controller
public class BuyerController {
	
	private static Logger logger = Logger.getLogger(BuyerController.class); 
	
	@Autowired
	private ProductService productService;	// 实现产品数据操作的service实现类
	
	@Autowired
	private TrxService trxService;	// 实现订单数据操作的service实现类

	/**
	 * 显示账务页
	 * @param user, session, model
	 * @return
	 */
	@RequestMapping("/account")
	public String account(User user, HttpSession session, Model model) {
		logger.info("**访问账务页**");
		int count = 0;	// 用于记录商品购买数量
		List<ProductTo> buyList = null;
		List<Transaction> trxList = null;
		user = (User) session.getAttribute("user");
		if ( user != null ) {
			// 1.获取商品信息与订单信息
			buyList = productService.getProductListById(user.getId());
			trxList = trxService.getTrx(user.getId());
		}
		// 2.根据订单信息确定购买数量
		for ( ProductTo to : buyList ) {
			for ( Transaction trx : trxList ) {
				if ( to.getId() == trx.getContentId() ) {
					count++;
				}
			}
			to.setBuyNum(count);
			count = 0;
		}
		// 3.返回数据与视图
		model.addAttribute("user", user);
		model.addAttribute("buyList", buyList);
		return "account";
	}
	
	/**
	 * 显示购物车
	 * @return
	 */
	@RequestMapping("/settleAccount")
	public String settleAccount() {
		logger.info("**访问购物车**");
		return "settleAccount";
	}
	
	/**
	 * 实现购买功能，生成订单信息
	 * @param buyList, user, session
	 * @return
	 */
	@RequestMapping("/api/buy")
	@ResponseBody
	public Map<String, Object> buy(@RequestBody List<BuyList> buyList, User user, HttpSession session) {
		logger.info("**购买商品**");
		user = (User) session.getAttribute("user");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Transaction> trxList = new ArrayList<Transaction>();
		Date date = new Date();
		// 1.遍历购物车中所有类型的商品
		for( BuyList b : buyList ) {
			// 2.遍历同种商品的所有数量
			for( int i=0; i<b.getNumber(); i++ ) {
				// 3.生成订单信息
				Transaction trx = new Transaction();
				trx.setContentId(b.getId());
				trx.setUserId(user.getId());
				trx.setPrice(productService.getProduct(b.getId()).getPrice());
				trx.setTime(date.getTime());
				trxList.add(trx);
			}
		}
		// 4.将订单信息插入数据库，并返回result
		int result = productService.buyProduct(trxList);
		// 5.根据result，返回数据
		if( result > 0 ) {
			map.put("code", 200);
			map.put("message", "购买成功！");
			map.put("result", true);
		} else {
			map.put("code", 400);
			map.put("message", "购买失败！");
			map.put("result", false);
		}
		return map;
	}
}
