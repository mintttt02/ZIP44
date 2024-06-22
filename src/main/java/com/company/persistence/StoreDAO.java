package com.company.persistence;

import java.util.List;
import java.util.Map;

import com.company.common.CommandMap;
import com.company.domain.Criteria;

public interface StoreDAO {
	
	public void insertProduct(Map<String, Object> map) throws Exception;
	
	public void insertOpt(Map<String, Object> map) throws Exception;
	
	public void insertProductImg(Map<String, Object> map) throws Exception;
	
	public void insertProductDetailImg(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> selectProductImg(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> selectProduct(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> selectProductDetailImg(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> listCriteria(Criteria cri) throws Exception;
	
	public int countPaging(Criteria cri) throws Exception;
	
	// 베스트 상품리스트
	public List<Map<String, Object>> selectBest(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> selectOpt(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> selectOpt1(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> selectOpt2_title(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> selectOpt2(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> selectOpt3_title(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> selectOpt3(Map<String, Object> map) throws Exception;
	
	public Map<String, Object> selectProductDetail(Map<String, Object> map) throws Exception;
	
	public void updateViewcnt(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> selectAllProduct(Criteria cri) throws Exception;
	
	public int countProductPaging(Criteria cri) throws Exception;
	
	// 카테고리별 상품리스트
	public List<Map<String, Object>> selectCategoryProduct(Criteria cri) throws Exception;
	
	public int countCategoryProduct_Paging(Criteria cri) throws Exception;
	
	public Map<String, Object> selectOrderImg(Map<String, Object> map) throws Exception;
	
	public void insertOrder(Map<String, Object> map) throws Exception;
	
	public Map<String, Object> selectOrderDetail_user(Map<String, Object> map) throws Exception;
	
	public Map<String, Object> selectOrderDetail(Map<String, Object> map) throws Exception;
	
	public void updateOptCnt(Map<String, Object> map) throws Exception;
	
	public int checkProductCnt(Map<String, Object> map) throws Exception;
	
	public void updateProductCnt(Map<String, Object> map) throws Exception;
	
	public void deleteProduct(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> selectOrder(Criteria cri) throws Exception;
	
	public int countOrderPaging(Criteria cri) throws Exception;
	
	public void updateOrder(Map<String, Object> map) throws Exception;
	
	// Q&A 등록
	public void insertQnA(Map<String, Object> map) throws Exception;
	
	// Q&A 리스트
	public List<Map<String, Object>> selectQnA(Map<String, Object> map) throws Exception;

	public void update_cartType(Map<String, Object> map)throws Exception;

	public List<Map<String, Object>> product_info(Map<String, Object> map)throws Exception;

	public List<Map<String, Object>> pr_delivery(Map<String, Object> map)throws Exception;

	public void update_cartType_default(Map<String, Object> map)throws Exception;

	public List<Map<String, Object>> payAfterDetailImg(Map<String, Object> map)throws Exception;

	public void deleteCart(Map<String, Object> map)throws Exception;

	public int CheckOptCnt(Map<String, Object> map)throws Exception;

}
