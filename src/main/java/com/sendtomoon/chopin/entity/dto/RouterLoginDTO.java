package com.sendtomoon.chopin.entity.dto;

public class RouterLoginDTO {

	private String group_id = "";
	private String action_mode = "";
	private String action_script = "";
	private String action_wait = "";
	private String current_page = "";
	private String next_page = "";
	private String login_authorization = "";

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getAction_mode() {
		return action_mode;
	}

	public void setAction_mode(String action_mode) {
		this.action_mode = action_mode;
	}

	public String getAction_script() {
		return action_script;
	}

	public void setAction_script(String action_script) {
		this.action_script = action_script;
	}

	public String getAction_wait() {
		return action_wait;
	}

	public void setAction_wait(String action_wait) {
		this.action_wait = action_wait;
	}

	public String getCurrent_page() {
		return current_page;
	}

	public void setCurrent_page(String current_page) {
		this.current_page = current_page;
	}

	public String getNext_page() {
		return next_page;
	}

	public void setNext_page(String next_page) {
		this.next_page = next_page;
	}

	public String getLogin_authorization() {
		return login_authorization;
	}

	public void setLogin_authorization(String login_authorization) {
		this.login_authorization = login_authorization;
	}
}
