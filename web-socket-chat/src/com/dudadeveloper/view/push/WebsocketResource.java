package com.dudadeveloper.view.push;

import java.io.Serializable;
import java.util.logging.Logger;

import org.primefaces.push.EventBus;
import org.primefaces.push.RemoteEndpoint;
import org.primefaces.push.annotation.OnClose;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.OnOpen;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.annotation.Singleton;
import org.primefaces.push.impl.JSONEncoder;

@PushEndpoint("/room")
@Singleton
public class WebsocketResource implements Serializable {

	private static final long serialVersionUID = -4578485462678562627L;
	
	private static final Logger LOGGER = Logger.getLogger(WebsocketResource.class.getName());

	@OnMessage(encoders = {JSONEncoder.class})
    public String onMessage(String message) {
		return message;
    }
	
	@OnOpen
    public void onOpen(RemoteEndpoint r, EventBus eventBus) {
		LOGGER.info("Socket opened for user");
    }
	
	@OnClose
    public void onClose(RemoteEndpoint r, EventBus eventBus) {
		LOGGER.info("Socket closed for user");
    }

}
