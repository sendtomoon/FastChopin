package com.sendtomoon.chopin.beans;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
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
		// TODO Auto-generated method stub

	}

	public void destroy() throws Exception {
		if (client != null) {
			client.close();
			client = null;
		}
	}

	public MongoTemplate getObject() throws Exception {
		if (null == template) {
			client = new MongoClient(new ServerAddress(servers, port), this.getCredential(),
					this.getMongoClientOptions());
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

	private List<String> getAddress() {
		String[] arr = servers.split(",");
		return Arrays.asList(arr);
	}

	private MongoCredential getCredential() {
		return MongoCredential.createCredential(user, dbname, password.toCharArray());
	}

	private MongoClientOptions getMongoClientOptions() {
		MongoClientOptions options = MongoClientOptions.builder().build();
		return options;
	}

}
