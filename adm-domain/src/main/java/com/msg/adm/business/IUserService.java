package com.msg.adm.business;

import java.util.List;
import com.msg.adm.business.data.User;
import com.msg.adm.common.exception.AdministrationEntityNotFoundException;
import com.msg.adm.common.exception.AdministrationValidationException;

public interface IUserService {

		public List<User> getAllUsers();

		public User getUser(User user) throws AdministrationEntityNotFoundException;
		
		public void addUser(User user) throws AdministrationValidationException ;
		
		public void deletUser(User user) throws AdministrationEntityNotFoundException ;

}
