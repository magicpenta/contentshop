package panda.netease.course.web.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import panda.netease.course.meta.ProductPo;
import panda.netease.course.service.ProductService;

/**
 * 卖家相关controller
 * @author panda
 *
 */
@Controller
public class SellerController {
	
	private static Logger logger = Logger.getLogger(SellerController.class);
	
	@Autowired
	private ProductService productService;
	
	/**
	 * 显示发布页
	 * @return
	 */
	@RequestMapping("/public")
	public String getPublicPage() {
		logger.info("**发布商品**");
		return "public";
	}
	
	/**
	 * 显示发布提交页
	 * @param product,request,model
	 * @return
	 */
	@RequestMapping("/publicSubmit")
	public String publicSubmit(ProductPo product, HttpServletRequest request, Model model) {
		logger.info("**提交商品信息**");
		// 1.获取内容信息
		String title = request.getParameter("title");
		String summary = request.getParameter("summary");
		String image = request.getParameter("image");
		String detail = request.getParameter("detail");
		int price = Integer.parseInt(request.getParameter("price"));
		// 2.提交内容信息到数据库，并返回相应视图
		product = new ProductPo();
		product.setTitle(title);
		product.setAbs(summary);
		product.setImage(image);
		product.setText(detail);
		product.setPrice(price);
		productService.addProduct(product);
		model.addAttribute("product", product);
		return "publicSubmit";
	}
	
	/**
	 * 上传文件功能
	 * @param file, request
	 * @return
	 */
	@RequestMapping(value="/api/upload", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		logger.info("**上传图片**");
		Map<String, Object> map = new HashMap<String, Object>();
		// 1.获取上下文路径
		String path = request.getServletContext().getRealPath("/image");
		// 2.获取不重复文件名
		String fileName = new Date().getTime() + file.getOriginalFilename();
		// 3.获取目标文件路径
		String filePath = path + File.separator + fileName;
		// 4.获取相对路径（用于存于数据库）
		String refPath = File.separator + "image" + File.separator + fileName;
		// 5.上传文件并返回数据信息
		File targetFile = new File(filePath);
		if ( !targetFile.exists() ) {
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile);
			map.put("code", 200);
			map.put("message", "上传成功！");
			map.put("result", refPath);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", 400);
			map.put("message", "上传失败！");
			map.put("result", refPath);
		}
		return map;
	}
	
	/**
	 * 内容编辑页
	 * @param id, model
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(@RequestParam("id") int id, Model model) {
		logger.info("**编辑商品**");
		ProductPo product = productService.getProduct(id);
		model.addAttribute("product", product);
		return "edit";
	}
	
	/**
	 * 内容编辑提交页
	 * @param product, request, model
	 * @return
	 */
	@RequestMapping("/editSubmit")
	public String editSubmit(ProductPo product, HttpServletRequest request, Model model) {
		logger.info("**编辑提交**");
		// 1.获取内容信息
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String summary = request.getParameter("summary");
		String image = request.getParameter("image");
		String detail = request.getParameter("detail");
		int price = Integer.parseInt(request.getParameter("price"));
		// 2.提交内容信息到数据库，并返回相应视图
		product = new ProductPo();
		product.setId(id);
		product.setTitle(title);
		product.setAbs(summary);
		product.setImage(image);
		product.setText(detail);
		product.setPrice(price);
		productService.editProduct(product);
		model.addAttribute("product", product);
		return "editSubmit";
	}
	
	/**
	 * 删除指定内容
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/api/delete", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(@RequestParam(value="id", required=false) Integer id) {
		logger.info("**删除商品**");
		Map<String, Object> map = new HashMap<String, Object>();
		int result = productService.deleteProduct(id);
		if ( result == 1 ) {
			map.put("code", 200);
			map.put("message", "删除成功！");
			map.put("result", true);
		} else {
			map.put("code", 400);
			map.put("message", "删除失败！");
			map.put("result", false);
		}
		return map;
	}

}
