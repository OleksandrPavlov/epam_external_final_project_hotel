Hotel webapp.
The app represents hotel web application. 
Details:
	The application suppose access segregation by two main roles(client, manager), and dividing on two massive functional segments respectively. 
	But there is common part of application that visible for all users in spite of role he has, even if have not it(common).
		common segment:
			-To execute searching for free room in specified date range. System will return the list of all acceptable rooms 	
			in this range and provide user with description of each;	
			-To order one of them*1.
		client segment:
			The user under client role have access to the next actions:
				-To observe all booked rooms list with detailed description.
				-To generate new booking request to the manager*2.
				-To observe booking offers from manager*3.
				-To reject offers that not fits into client needs.
				-To book a room.
				-To pay for a booked room*4.
		manager segment:
			The user under manager role have very wide and flexible toolse for managing by web hotel:
				-To add new room;
				-To edit certain room;
				-To delete certain room;
				-To enable\disable certain room*5;
				-To observe waiting users;
				-To enable\disable waiting users*7;
				-To observe client request list;
				-To find rooms that fits client request needs;
				-To send offer to the client;
				-To observe room list*11;
	Also the app equipped by access security system:
		-Already logined user is not able to go to enter page again(he will be redirected to the room search page)
		-The system distinctful segregate access based on user role. By this way user with role A 
		can not get access for app segment intended for users under role B;
		-The user can not to log out not being logged.
	Registration:
		The user can registrate in the system filing fields on registration form and choosing the role he would like to have.
	Login:
		The user can enter to the system filing login and password . Also he can choose 'remember me' option that will guarantee that
		during the next entering process he will be logged automatically(without input login and password).
	Notification: The app equipped by notification system that afford to send some messages about operation 
	executing state during application execution.
	Explanations:
		*1 -> The room ordering process acceptable only for client users.
		*2 -> Client able to create some request to the manager. The request contains describing of wishes to the room. 
		Service created for comfortable of client.
		*3 -> The manager review all client's requests manually to maximize human factor and choose the most relevant 
		room according to client's wishes. Then he generate offer as room and send it to the client.
		*4 -> Payment process has been emulated in the system. The system not supports real appeal to the payment services at the internet.
		*5 -> The manager able to enable or disable certain room . Disabling room means preventing it from displaying in
		the room list of the search segment, in another words, rooms become invisible for customer.
 		*7 -> The manager also can manage accounts in the system. All users that asking for registration tranfers to the list of
		'waiting for registration users' from where the manager can browse them and to deside which account looks suspitios or 
		has invalid registration data. Eventually he can just click 'enable' and from this moment user will be granted for access to 
		his account.
		*11 -> The room observer view equipped by sorting function(in different ways) and some primitive editing tools. 
		This page provides manager with comfortable user interface to overviewing rooms and managing.

