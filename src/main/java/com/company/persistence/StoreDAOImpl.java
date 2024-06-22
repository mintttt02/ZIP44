package com.company.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.company.common.CommandMap;
import com.company.domain.Criteria;

@Repository
public class StoreDAOImpl implements StoreDAO {
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "com.company.mapper.StoreMapper";
	
	@Override
	public void insertProduct (Map<String, Object> map) throws Exception {
		session.insert(namespace + ".insertProduct", map);
	}
	
	@Override
	public void insertOpt(Map<String, Object> map) throws Exception {
		session.insert(namespace + ".insertOpt", map);
	}
	
	@Override
	public void insertProductImg(Map<String, Object> map) throws Exception {
		session.insert(namespace + ".insertProductImg", map);
	}
	
	@Override
	public void insertProductDetailImg(Map<String, Object> map) throws Exception {
		session.insert(namespace + ".insertProductDetailImg", map);
	}
	
	@Override
	public List<Map<String, Object>> selectProductImg(Map<String, Object> map) throws Exception {
		return session.selectList(namespace + ".selectProductImg", map);
	}
	
	@Override
	public List<Map<String, Object>> selectProduct (Map<String, Object> map) throws Exception {
		return session.selectList(namespace + ".selectProduct", map); 
	}
	
	@Override
	public List<Map<String, Object>> selectProductDetailImg(Map<String, Object> map) throws Exception {
		return session.selectList(namespace + ".selectProductDetailImg", map);
	}
	
	@Override
	public List<Map<String, Object>> listCriteria(Criteria cri) throws Exception {
		return session.selectList(namespace + ".listCriteria", cri);
	}
	
	@Override
	public int countPaging(Criteria cri) throws Exception {
		return session.selectOne(namespace + ".countPaging", cri);
	}
	
	@Override
	public List<Map<String, Object>> selectBest(Map<String, Object> map) throws Exception {
		return session.selectList(namespace + ".selectBest", map);
	}
	
	@Override
	public List<Map<String, Object>> selectOpt(Map<String, Object> map) throws Exception {
		return session.selectList(namespace + ".selectOpt", map);
	}
	
	@Override
	public List<Map<String, Object>> selectOpt1(Map<String, Object> map) throws Exception {
		return session.selectList(namespace + ".selectOpt1", map);
	}
	
	@Override
	public List<Map<String, Object>> selectOpt2_title(Map<String, Object> map) throws Exception {
		return session.selectList(namespace + ".selectOpt2_title", map);
	}
	
	@Override
	public List<Map<String, Object>> selectOpt2(Map<String, Object> map) throws Exception {
		return session.selectList(namespace + ".selectOpt2", map);
	}
	
	@Override
	public List<Map<String, Object>> selectOpt3_title(Map<String, Object> map) throws Exception {
		return session.selectList(namespace + ".selectOpt3_title", map);
	}
	
	@Override
	public List<Map<String, Object>> selectOpt3(Map<String, Object> map) throws Exception {
		return session.selectList(namespace + ".selectOpt3", map);
	}
	
	@Override
	public Map<String, Object> selectProductDetail(Map<String, Object> map) throws Exception {
		return session.selectOne(namespace + ".selectProductDetail", map);
	}
	
	@Override
	public void updateViewcnt(Map<String, Object> map) throws Exception {
		session.update(namespace + ".updateViewCnt", map);
	}
	
	@Override
	public List<Map<String, Object>> selectAllProduct(Criteria cri) throws Exception {
		return session.selectList(namespace + ".selectAllProduct", cri);
	}
	
	@Override
	public int countProductPaging(Criteria cri) throws Exception {
		return session.selectOne(namespace + ".countProductPaging", cri);
	}
	
	@Override
	public List<Map<String, Object>> selectCategoryProduct(Criteria cri) throws Exception {
		return session.selectList(namespace + ".selectCategoryProduct", cri);
	}
	
	@Override
	public int countCategoryProduct_Paging(Criteria cri) throws Exception {
		return session.selectOne(namespace + ".countCategoryProduct_Paging", cri);
	}
	
	@Override
	public Map<String, Object> selectOrderImg(Map<String, Object> map) throws Exception {
		return session.selectOne(namespace + ".selectOrderImg", map);
	}

	@Override
	public void insertOrder(Map<String, Object> map) throws Exception {
		session.insert(namespace + ".insertOrder", map);
	}
	
	@Override
	public Map<String, Object> selectOrderDetail_user(Map<String, Object> map) throws Exception {
		return session.selectOne(namespace + ".selectOrderDetail_user", map);
	}
	
	@Override
	public Map<String, Object> selectOrderDetail(Map<String, Object> map) throws Exception {
		return session.selectOne(namespace + ".selectOrderDetail", map);
	}
	
	@Override
	public void updateOptCnt(Map<String, Object> map) throws Exception {
		session.update(namespace + ".updateOptCnt", map);
	}
	
	@Override
	public int checkProductCnt(Map<String, Object> map) throws Exception {
		return session.selectOne(namespace + ".checkProductCnt", map);
	}
	
	@Override
	public void updateProductCnt(Map<String, Object> map) throws Exception {
		session.update(namespace + ".updateProductCnt", map);
	}
	
	@Override
	public void deleteProduct(Map<String, Object> map) throws Exception {
		session.delete(namespace + ".deleteProduct", map);
	}
	
	@Override
	public List<Map<String, Object>> selectOrder(Criteria cri) throws Exception {
		return session.selectList(namespace + ".selectOrder", cri);
	}
	
	@Override
	public int countOrderPaging(Criteria cri) throws Exception {
		return session.selectOne(namespace + ".countOrderPaging", cri);
	}
	
	@Override
	public void updateOrder(Map<String, Object> map) throws Exception {
		session.update(namespace + ".updateOrder", map);
	}
	
	@Override
	public void insertQnA(Map<String, Object> map) throws Exception {
		session.insert(namespace + ".insertQnA", map);
	}
	
	@Override
	public List<Map<String, Object>> selectQnA(Map<String, Object> map) throws Exception {
		return session.selectList(namespace + ".selectQnA", map);
	}

	@Override
	public void update_cartType(Map<String, Object> map) throws Exception {
		session.update(namespace + ".update_cartType", map);
		
	}

	@Override
	public List<Map<String, Object>> product_info(Map<String, Object> map) throws Exception {
		return session.selectList(namespace + ".product_info", map);
	}

	@Override
	public List<Map<String, Object>> pr_delivery(Map<String, Object> map) throws Exception {
		return session.selectList(namespace + ".pr_delivery", map);
	}

	@Override
	public void update_cartType_default(Map<String, Object> map) throws Exception {
		session.update(namespace + ".update_cartType_default", map);
		
	}

	@Override
	public List<Map<String, Object>> payAfterDetailImg(Map<String, Object> map) throws Exception {
		return session.selectList(namespace + ".payAfterDetailImg", map);
	}

	@Override
	public void deleteCart(Map<String, Object> map) {
		session.delete(namespace + ".deleteCart", map);
		
	}

	@Override
	public int CheckOptCnt(Map<String, Object> map) throws Exception {
		return session.selectOne(namespace + ".CheckOptCnt", map);
	}
}
