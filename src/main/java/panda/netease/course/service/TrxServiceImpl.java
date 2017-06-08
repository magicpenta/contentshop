package panda.netease.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import panda.netease.course.dao.TransactionDao;
import panda.netease.course.meta.Transaction;

@Service
public class TrxServiceImpl implements TrxService{

	@Autowired
	private TransactionDao transactionDao;
	
	public List<Transaction> getTrx(int personId) {
		return transactionDao.getTrx(personId);
	}

}
