package panda.netease.course.service;

import java.util.List;

import panda.netease.course.meta.Transaction;

public interface TrxService {

	/**
	 * 通过用户ID查询订单信息
	 * @param personId
	 * @return
	 */
	public List<Transaction> getTrx(int personId);
	
}
