package com.company.service;

import java.util.List;
import java.util.Map;

import com.company.common.CommandMap;
import com.company.domain.Criteria;

public interface StoreService {
	
	public void RegistProduct(Map<String, Object> map) throws Exception;
	
	public void RegistOpt(Map<String, Object> map) throws Exception;
	
	public void RegistProductImg(Map<String, Object> map) throws Exception;
	
	public void RegistProductDetailImg(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> ListProductImg(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> ListProduct(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> ListProductDetailImg(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> ListPaging(Criteria cri) throws Exception;
	
	public int ListCountCriteria(Criteria cri) throws Exception;
	
	public List<Map<String, Object>> ListBest(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> ListOpt(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> ListOpt1(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> Opt2_title(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> ListOpt2(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> Opt3_title(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> ListOpt3(Map<String, Object> map) throws Exception;
	
	public Map<String, Object> DetailProduct(Map<String, Object> map) throws Exception;
	
	public void ModifyViewCnt(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> ListAllProduct(Criteria cri) throws Exception;
	
	public int countProductPaging(Criteria cri) throws Exception;
	
	public List<Map<String, Object>> ListCategoryProduct(Criteria cri) throws Exception;
	
	public int countCategoryProduct_Paging(Criteria cri) throws Exception;
	
	public Map<String, Object> OrderImg(Map<String, Object> map) throws Exception;
	
	public void RegistOrder(Map<String, Object> map) throws Exception;
	
	public Map<String, Object> OrderDetail_user(Map<String, Object> map) throws Exception;
	
	public Map<String, Object> OrderDetail(Map<String, Object> map) throws Exception;
	
	public void ModifyOptionCnt(Map<String, Object> map) throws Exception;
	
	public int CheckProductCnt(Map<String, Object> map) throws Exception; 
	
	public void ModifyProductCnt(Map<String, Object> map) throws Exception;
	
	public void removeProduct(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> ListOrder(Criteria cri) throws Exception;
	
	public int countOrderPaging(Criteria cri) throws Exception;
	
	public void ModifyOrder(Map<String, Object> map) throws Exception;
	
	// Q&A 등록
	public void AddQnA(Map<String, Object> map) throws Exception;
	
	// Q&A 리스트
	public List<Map<String, Object>> ListQnA(Map<String, Object> map) throws Exception;

	public void update_cartType(Map<String, Object> map)throws Exception;

	public List<Map<String, Object>> product_info(Map<String, Object> map)throws Exception;

	public List<Map<String, Object>> pr_delivery(Map<String, Object> map)throws Exception;

	public void update_cartType_default(Map<String, Object> map)throws Exception;

	public List<Map<String, Object>> payAfterDetailImg(Map<String, Object> map)throws Exception;

	public void deleteCart(Map<String, Object> map)throws Exception;

	public int CheckOptCnt(Map<String, Object> map)throws Exception;
}
