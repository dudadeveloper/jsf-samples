package com.dudadeveloper.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Singleton;

import com.dudadeveloper.model.Message;
import com.dudadeveloper.model.User;
import com.dudadeveloper.service.ChatService;

@Singleton
@Local
public class ChatServiceImpl implements ChatService, Serializable {

	private static final long serialVersionUID = 7053290835462198860L;
	
	private List<User> users = new ArrayList<User>();
	
	private List<Message> messages = new ArrayList<Message>();
	
	@Override
	public void addUser(User user) {
		
		if (users.contains(user))
			throw new IllegalStateException("User already in Chat!");
		else
			users.add(user);
		
	}

	@Override
	public List<User> getUsers() {
		return users;
	}

	@Override
	public List<Message> getMessages() {
		return messages;
	}

	@Override
	public void sendMessage(User user, String message) {
		messages.add(new Message(user, message));
	}

}
