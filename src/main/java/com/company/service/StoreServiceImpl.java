package com.company.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.company.common.CommandMap;
import com.company.domain.Criteria;
import com.company.persistence.StoreDAO;

@Service
public class StoreServiceImpl implements StoreService {
	
	@Inject
	private StoreDAO dao;
	
	@Override
	public void RegistProduct (Map<String, Object> map) throws Exception {
		dao.insertProduct(map);
	}
	
	@Override
	public void RegistOpt(Map<String, Object> map) throws Exception {
		dao.insertOpt(map);
	}
	
	@Override
	public void RegistProductImg(Map<String, Object> map) throws Exception {
		dao.insertProductImg(map);
	}
	
	@Override
	public void RegistProductDetailImg(Map<String, Object> map) throws Exception {
		dao.insertProductDetailImg(map);
	}
	
	@Override
	public List<Map<String, Object>> ListProduct(Map<String, Object> map) throws Exception {
		return dao.selectProduct(map);
	}
	
	@Override
	public List<Map<String, Object>> ListProductDetailImg(Map<String, Object> map) throws Exception {
		return dao.selectProductDetailImg(map);
	}
	
	@Override
	public List<Map<String, Object>> ListPaging(Criteria cri) throws Exception {
		return dao.listCriteria(cri);
	}
	
	@Override
	public int ListCountCriteria(Criteria cri) throws Exception {
		return dao.countPaging(cri);
	}
	
	@Override
	public List<Map<String, Object>> ListProductImg(Map<String, Object> map) throws Exception {
		return dao.selectProductImg(map);
	}
	
	@Override
	public List<Map<String, Object>> ListBest(Map<String, Object> map) throws Exception {
		return dao.selectBest(map);
	}
	
	@Override
	public List<Map<String, Object>> ListOpt(Map<String, Object> map) throws Exception {
		return dao.selectOpt(map);
	}
	
	@Override
	public List<Map<String, Object>> ListOpt1(Map<String, Object> map) throws Exception {
		return dao.selectOpt1(map);
	}
	
	@Override
	public List<Map<String, Object>> Opt2_title(Map<String, Object> map) throws Exception {
		return dao.selectOpt2_title(map);
	}
	
	@Override
	public List<Map<String, Object>> ListOpt2(Map<String, Object> map) throws Exception {
		return dao.selectOpt2(map);
	}
	
	@Override
	public List<Map<String, Object>> Opt3_title(Map<String, Object> map) throws Exception {
		return dao.selectOpt3_title(map);
	}
	
	@Override
	public List<Map<String, Object>> ListOpt3(Map<String, Object> map) throws Exception {
		return dao.selectOpt3(map);
	}
	
	@Override
	public Map<String, Object> DetailProduct(Map<String, Object> map) throws Exception {
		return dao.selectProductDetail(map);
	}
	
	@Override
	public void ModifyViewCnt(Map<String, Object> map) throws Exception {
		dao.updateViewcnt(map);
	}
	
	@Override
	public List<Map<String, Object>> ListAllProduct(Criteria cri) throws Exception {
		return dao.selectAllProduct(cri);
	}
	
	@Override
	public int countProductPaging(Criteria cri) throws Exception {
		return dao.countProductPaging(cri);
	}
	
	@Override
	public List<Map<String, Object>> ListCategoryProduct(Criteria cri) throws Exception {
		return dao.selectCategoryProduct(cri);
	}
	
	@Override
	public int countCategoryProduct_Paging(Criteria cri) throws Exception {
		return dao.countCategoryProduct_Paging(cri);
	}

	@Override
	public Map<String, Object> OrderImg(Map<String, Object> map) throws Exception {
		return dao.selectOrderImg(map);
	}
	
	@Override
	public void RegistOrder(Map<String, Object> map) throws Exception {
		dao.insertOrder(map);
	}
	
	@Override
	public Map<String, Object> OrderDetail_user(Map<String, Object> map) throws Exception {
		return dao.selectOrderDetail_user(map);
	}
	
	@Override
	public Map<String, Object> OrderDetail(Map<String, Object> map) throws Exception {
		return dao.selectOrderDetail(map);
	}
	
	@Override
	public void ModifyOptionCnt(Map<String, Object> map) throws Exception {
		dao.updateOptCnt(map);
	}
	
	@Override
	public int CheckProductCnt(Map<String, Object> map) throws Exception {
		return dao.checkProductCnt(map);
	}
	
	@Override
	public void ModifyProductCnt(Map<String, Object> map) throws Exception {
		dao.updateProductCnt(map);
	}
	
	@Override
	public void removeProduct(Map<String, Object> map) throws Exception {
		dao.deleteProduct(map);
	}
	
	@Override
	public List<Map<String, Object>> ListOrder(Criteria cri) throws Exception {
		return dao.selectOrder(cri);
	}
	
	@Override
	public int countOrderPaging(Criteria cri) throws Exception {
		return dao.countOrderPaging(cri);
	}
	
	@Override
	public void ModifyOrder(Map<String, Object> map) throws Exception {
		dao.updateOrder(map);
	}
	
	@Override
	public void AddQnA(Map<String, Object> map) throws Exception {
		dao.insertQnA(map);
	}
	
	@Override
	public List<Map<String, Object>> ListQnA(Map<String, Object> map) throws Exception {
		return dao.selectQnA(map);
	}

	@Override
	public void update_cartType(Map<String, Object> map) throws Exception {
		dao.update_cartType(map);
		
	}

	@Override
	public List<Map<String, Object>> product_info(Map<String, Object> map) throws Exception {
		return dao.product_info(map);
	}
	@Override
	public List<Map<String, Object>> pr_delivery(Map<String, Object> map) throws Exception {
		return dao.pr_delivery(map);
	}

	@Override
	public void update_cartType_default(Map<String, Object> map) throws Exception {
		dao.update_cartType_default(map);
	}

	@Override
	public List<Map<String, Object>> payAfterDetailImg(Map<String, Object> map) throws Exception {
		return dao.payAfterDetailImg(map);
	}

	@Override
	public void deleteCart(Map<String, Object> map) throws Exception {
		dao.deleteCart(map);
	}

	@Override
	public int CheckOptCnt(Map<String, Object> map) throws Exception {
		return dao.CheckOptCnt(map);
	}
}
