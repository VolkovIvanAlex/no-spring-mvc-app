package laba_5.service;

import laba_5.User;
import laba_5.repository.DefaultUserRepository;


public class DefaultUserService
{
	private DefaultUserRepository userRepository = new DefaultUserRepository();

	public void init(String name)
	{
		userRepository.initialization(name);
	}

	public void createTableUsers()
	{
		userRepository.createTableUsers();
	}

	public void addUser(User user)
	{
		userRepository.addUser(user);
	}

	public User findByLogin(String username)
	{
		return userRepository.getUserByLogin(username);
	}
}
