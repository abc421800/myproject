package com.cost168.costaudit.service.yaohao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cost168.costaudit.mapper.cost.CostContractMapper;
import com.cost168.costaudit.mapper.yaohao.YaohaoWinbidMapper;
import com.cost168.costaudit.pojo.cost.CostContract;
import com.cost168.costaudit.pojo.yaohao.YaohaoWinbid;
import com.cost168.costaudit.pojo.yaohao.YaohaoWinbidExample;
import com.cost168.costaudit.service.yaohao.YaohaoWinbidService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class YaohaoWinbidServiceImpl implements YaohaoWinbidService {
	
	@Autowired
	private YaohaoWinbidMapper yaohaoWinbidMapper;
	@Autowired
	private CostContractMapper costContractMapper;

	@Override
	public List<YaohaoWinbid> selectByExample(YaohaoWinbidExample example) {
		return yaohaoWinbidMapper.selectByExample(example);
	}

	@Override
	public YaohaoWinbid selectByPrimaryKey(String id) {
		return yaohaoWinbidMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertSelective(YaohaoWinbid record) {
		return yaohaoWinbidMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(YaohaoWinbid yaohaoWinbid) {
		return yaohaoWinbidMapper.updateByPrimaryKeySelective(yaohaoWinbid);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return yaohaoWinbidMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<YaohaoWinbid> selectListByMap(Map<String, Object> map) {
		return yaohaoWinbidMapper.selectListByMap(map);
	}

	@Override
	public int selectCountByMap(Map<String, Object> map) {
		return yaohaoWinbidMapper.selectCountByMap(map);
	}

	@Override
	public List<YaohaoWinbid> selectListByOrderCode(String code) {
		return yaohaoWinbidMapper.selectListByOrderCode(code);
	}

	@Override
	public int deleteByOrderCode(String orderCode) {
		return yaohaoWinbidMapper.deleteByOrderCode(orderCode);
	}

	@Override
	public BigDecimal getServiceAmountSum(Map<String, Object> map) {
		return yaohaoWinbidMapper.getServiceAmountSum(map);
	}

	@Override
	public void containContract(HttpServletRequest request)throws Exception{
		String winId=request.getParameter("winId");
		String contractId=request.getParameter("contractId");
		CostContract con=costContractMapper.selectByPrimaryKey(contractId);
		YaohaoWinbid win=yaohaoWinbidMapper.selectByPrimaryKey(winId);
		if(null!=win && null!=con){
			win.setDecideAmount(con.getContractAmount());
			win.setContractId(contractId);
			yaohaoWinbidMapper.updateByPrimaryKeySelective(win);
		}
	}

}
