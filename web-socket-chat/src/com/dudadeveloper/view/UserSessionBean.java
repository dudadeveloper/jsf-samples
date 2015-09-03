package com.dudadeveloper.view;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.dudadeveloper.model.User;

@Named
@SessionScoped
public class UserSessionBean implements Serializable {

	private static final long serialVersionUID = -3663505174863719287L;
	
	private User user = new User();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
