package com.sendtomoon.chopin.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class MongoDBFactoryBean implements FactoryBean<MongoTemplate>, DisposableBean, InitializingBean {

	private MongoTemplate template;

	private MongoClient client;

	@Value("${mongodb.servers}")
	private String servers;
	@Value("${mongodb.port}")
	private Integer port;
	@Value("${mongodb.dbname}")
	private String dbname;
	@Value("${mongodb.user}")
	private String user;
	@Value("${mongodb.password}")
	private String password;

	public void afterPropertiesSet() throws Exception {
		getObject();
	}

	public void destroy() throws Exception {
		if (client != null) {
			client.close();
			client = null;
		}
	}

	public MongoTemplate getObject() throws Exception {
		if (null == template) {
			client = new MongoClient(this.getAddress(), this.getCredential());
			this.template = new MongoTemplate(client, dbname);
		}
		return template;
	}

	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}

	private List<ServerAddress> getAddress() {
		List<ServerAddress> list = new ArrayList<ServerAddress>();
		String[] arr = servers.split(",");
		for (String str : arr) {
			list.add(new ServerAddress(str.split(":")[0], Integer.valueOf(str.split(":")[1])));
		}
		return list;
	}

	private List<MongoCredential> getCredential() {
		List<MongoCredential> list = new ArrayList<MongoCredential>();
		list.add(MongoCredential.createCredential(user, dbname, password.toCharArray()));
		return list;
	}

}
