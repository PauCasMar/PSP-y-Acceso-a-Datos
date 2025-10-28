package ceu.dam.ad.tema3.ejercicios.ejercicio05.service;

import ceu.dam.ad.tema3.ejercicios.ejercicio05.model.User;

public interface UserService {
	
	public User createUser(User user) throws DuplicateUserException, UserException;
	
	public void changePassword(Long idUser, String oldPassword, String newPassword) throws UserNotFoundException, UserUnauthorizedException, UserException;
	
	public User login(String login, String password) throws UserNotFoundException, UserUnauthorizedException, UserException;
	
	public User getUser(Long idUser) throws UserNotFoundException, UserException;
	
	
	
	
}
