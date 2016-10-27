package com.tangwan.mongoutil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * mongodb　基础操作类
 */
public abstract class MongoDBBaseDao<T, ID extends Serializable> {

	/**
	 * spring mongodb　集成操作类　
	 */
	protected MongoTemplate mongoTemplate;
	
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	/**
	 * 获取需要操作的实体类class
	 * 
	 * @return
	 */
	protected abstract Class<T> getEntityClass();

	public int save(T entity) {
		mongoTemplate.insert(entity);
		return 1;
	}

	public int saveAll(Collection<T> entities) {
		List<T> list = new ArrayList<T>();
		Iterator<T> iterator = entities.iterator();
		while(iterator.hasNext()) {
			list.add(iterator.next());
		}
		
		mongoTemplate.insertAll(list); 
		return entities.size();
	}


	public int update(T entity) {
		mongoTemplate.save(entity);
		return 1;
	}


	public int updateAll(Collection<T> entities) {
		Iterator<T> iterator = entities.iterator();
		while(iterator.hasNext()) {
			mongoTemplate.save(iterator.next());
		}
		return entities.size();
	}


	public int delete(T entity) {
		mongoTemplate.remove(entity);
		return 1;
	}


	public int deleteAll(Collection<? extends T> entities) {
		Iterator<?> iterator = entities.iterator();
		while(iterator.hasNext()) {
			mongoTemplate.remove(iterator.next());
		}
		return entities.size();
	}


	public int deleteByID(ID id) {
		Query query =  new Query(Criteria.where("_id").is(id));
		mongoTemplate.remove(query, getEntityClass());
		return 1;
	}


	public int deleteAllByID(Collection<ID> ids) {
		Iterator<?> iterator = ids.iterator();
		while(iterator.hasNext()) {
			Query query =  new Query(Criteria.where("_id").is(iterator.next()));
			mongoTemplate.remove(query, getEntityClass());
		}
		return ids.size();
	}


	public boolean exists(ID id) {
		Query query =  new Query(Criteria.where("_id").is(id));
		return mongoTemplate.count(query, getEntityClass()) > 0;
	}


	public T findOne(ID id) {
		Query query =  new Query(Criteria.where("_id").is(id));
		return mongoTemplate.findOne(query, getEntityClass());
	}


	public long count() {
		return mongoTemplate.count(new Query(), getEntityClass());
	}


	public List<T> findAll() {
		return mongoTemplate.findAll(getEntityClass());
	}
	
}
