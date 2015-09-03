package com.dudadeveloper.service;

import java.util.List;

import com.dudadeveloper.model.Message;
import com.dudadeveloper.model.User;

public interface ChatService {

	void addUser(User user) throws Exception;
	
	List<User> getUsers();
	
	List<Message> getMessages();

	void sendMessage(User user, String message);
	
}
