package com.sendtomoon.chopin.data.mongodb;

import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoDAO extends ApplicationObjectSupport implements InitializingBean {

	private MongoTemplate _mongoTemplate;

	private final Object lock = new Object();

	public void add(Object obj) {
		getMongoTemplate();
		_mongoTemplate.insert(obj);
	}

	private void initMongoTemplate() {
		ApplicationContext ac = this.getApplicationContext();
		Object obj = ac.getBean("_mongodbTemplate");
		if (null == obj) {
			throw new FatalBeanException("mongobean is null");
		}
		if (obj instanceof MongoTemplate) {
			_mongoTemplate = (MongoTemplate) obj;
		}
		if (_mongoTemplate == null) {
			throw new FatalBeanException("mongoTemplate can't resolved.");
		}
	}

	private void getMongoTemplate() {
		if (null == _mongoTemplate) {
			synchronized (lock) {
				if (this._mongoTemplate == null) {
					initMongoTemplate();
				}
			}
		}
	}

	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}

}
