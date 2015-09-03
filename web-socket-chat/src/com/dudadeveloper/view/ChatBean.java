package com.dudadeveloper.view;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import com.dudadeveloper.model.Message;
import com.dudadeveloper.model.User;
import com.dudadeveloper.service.ChatService;

@Named
@ViewScoped
public class ChatBean implements Serializable {

	private static final long serialVersionUID = -4673451471285437940L;
	
	private static final Logger LOGGER = Logger.getLogger(ChatBean.class.getName());

	private final static String CHANNEL = "/room";
	private final EventBus eventBus = EventBusFactory.getDefault().eventBus();
	
	@Inject
	private UserSessionBean userSessionBean;
	
	@EJB
	private ChatService chatService;
	
	private String message;

	@PostConstruct
	public void reset() {
		message = new String();
	}
	
	public void connect() {
		try {
			chatService.addUser(userSessionBean.getUser());
			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.execute("PF('subscriber').connect('" + CHANNEL + "')");
		} 
		catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
	public void disconnect() {
		RequestContext requestContext = RequestContext.getCurrentInstance();		
		requestContext.execute("close()");
	}
	
	public void send() {
		eventBus.publish(CHANNEL, message);
		chatService.sendMessage(userSessionBean.getUser(), message);
		reset();
	}

	public List<User> getUsers() {
		return chatService.getUsers();
	}

	public List<Message> getMessages() {
		return chatService.getMessages();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
