package panda.netease.course.dao;

import java.util.List;

import panda.netease.course.meta.Transaction;

/**
 * 交易订单数据访问对象
 * @author panda
 *
 */
public interface TransactionDao {

	/**
	 * 获取买家的所有订单信息
	 * @param personId
	 * @return
	 */
	public List<Transaction> getTrx(int personId);
}
