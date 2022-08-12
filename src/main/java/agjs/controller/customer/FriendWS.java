package agjs.controller.customer;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import agjs.bean.customer.ChatMessage;
import agjs.bean.customer.State;
import agjs.common.jedis.JedisHandleMessage;

@ServerEndpoint("/FriendWS/{userName}")
public class FriendWS {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	Gson gson = new Gson();
	
//===================================
	//測試宣告儲存管理員session用
//	private static Session managerSession;
//===================================
	
	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		
		//===============================
		//判定是否為管理員,然後儲存他的session
//		if (userName.equals("manager")) {
//			managerSession = userSession;
//		}
		//===============================
		
		
		
		/* save the new user in the map */
		sessionsMap.put(userName, userSession);
		/* Sends all the connected users to the new user */
		Set<String> userNames = sessionsMap.keySet();
		State stateMessage = new State("open", userName, userNames);
		String stateMessageJson = gson.toJson(stateMessage);
		Collection<Session> sessions = sessionsMap.values();
		for (Session session : sessions) {
			if (session.isOpen()) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}

		String text = String.format("Session ID = %s, connected; userName = %s%nusers: %s", userSession.getId(),
				userName, userNames);
//		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
		String sender = chatMessage.getSender();
		String receiver = chatMessage.getReceiver();
		//================================ 測試A區 ====================================
		// 解決了非管理員只連接管理員,但是變成了所有人同時都連在管理員上(a,管理員)=>> (a,b,管理員) =>> (a,b,c,管理員)
//		ChatMessage aaa = new ChatMessage();
//		aaa.setMessage(chatMessage.getMessage());
//		if (Objects.equals("manager", receiver)) {
//			managerSession.getAsyncRemote().sendText(gson.toJson(aaa));
//			userSession.getAsyncRemote().sendText(gson.toJson(aaa));
//		} else {
//			managerSession.getAsyncRemote().sendText(gson.toJson(aaa));
//			Session session = sessionsMap.get(receiver);
//			session.getAsyncRemote().sendText(gson.toJson(aaa));
//		}
		//====================================================================
		if ("history".equals(chatMessage.getType())) {
			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);
			String historyMsg = gson.toJson(historyData);
			ChatMessage cmHistory = new ChatMessage("history", sender, receiver, historyMsg);
			if (userSession != null && userSession.isOpen()) {
				userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
//				System.out.println("history = " + gson.toJson(cmHistory));
				return;
			}
		}
		
		
		Session receiverSession = sessionsMap.get(receiver);
		if (receiverSession != null && receiverSession.isOpen()) {
//=============================== 測試B ====================================
// 沒有解決成功,仍需手動連接管理員
//			if(managerSession == null) {

				receiverSession.getAsyncRemote().sendText(message);
//			}else {
//				managerSession.getAsyncRemote().sendText(message);
//			}
//=========================================================================
			userSession.getAsyncRemote().sendText(message);
			JedisHandleMessage.saveChatMessage(sender, receiver, message);
		}
//		System.out.println("Message received: " + message);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e);
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String userNameClose = null;
		Set<String> userNames = sessionsMap.keySet();
		for (String userName : userNames) {
			if (sessionsMap.get(userName).equals(userSession)) {
				userNameClose = userName;
				sessionsMap.remove(userName);
				break;
			}
		}

		if (userNameClose != null) {
			State stateMessage = new State("close", userNameClose, userNames);
			String stateMessageJson = gson.toJson(stateMessage);
			Collection<Session> sessions = sessionsMap.values();
			for (Session session : sessions) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}

		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userNames);
//		System.out.println(text);
	}
}
