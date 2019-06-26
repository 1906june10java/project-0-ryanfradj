package com.revature.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.revature.controller.Accounts;
import com.revature.controller.ControllerTwo;
import com.revature.exception.InvalidAccountException;
import com.revature.util.ConnectionUtil;

public class BankRepoJdbc implements BankRepo{
	
	private static final Logger LOGGER = Logger.getLogger(BankRepoJdbc.class);

	@Override
	public boolean create(Accounts acct)  {
		LOGGER.setLevel(Level.TRACE);
		LOGGER.trace("entering create method with paramaters "+  acct );
		try(Connection connection = ConnectionUtil.getConnection()){
			int parameterIndex =1;
			
			String sql="{MY_INC(?,?,?)}";
			CallableStatement cs = connection.prepareCall(sql);
			cs.setString(parameterIndex, acct.getName());
			cs.setString(parameterIndex, acct.getPass());
			cs.setDouble(parameterIndex++, acct.getbalance());
			if(cs.executeUpdate()>0) {
				return true;
			}
		}catch(SQLException e) {
			LOGGER.error("could not insert account", e);
		}
		return false;
	}
	
	public static boolean UpdateBalance(String name, double newBalance) {
		LOGGER.setLevel(Level.TRACE);
		LOGGER.trace("entering Update Balance by name method with paramaters "+  name );
		try(Connection connection = ConnectionUtil.getConnection()){
			String sql ="UPDATE BANK_ACCTS SET BALANCE = ? WHERE USER_NAME = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setDouble(1, newBalance);
			statement.setString(2, name);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			LOGGER.error("Could not update",e);
		}
		return false;
	}
	public static double getBalance(String name) {
		LOGGER.setLevel(Level.TRACE);
		LOGGER.trace("entering get Balance by name method with paramaters "+  name );
		try(Connection connection = ConnectionUtil.getConnection()){
			String sql ="SELECT BALANCE FROM BANK_ACCTS WHERE USER_NAME=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet result=statement.executeQuery();
			if(result.next()) {
			return result.getDouble("BALANCE");
			}
		} catch (SQLException e) {
			LOGGER.error("Could not get balance",e);
		}
		return 0;
	}


	@Override
	public Accounts findByName(String name) throws InvalidAccountException {
		LOGGER.setLevel(Level.TRACE);
		LOGGER.trace("entering find by name method with paramaters "+  name );
		try(Connection connection = ConnectionUtil.getConnection()){
		
			String sql ="SELECT * FROM BANK_ACCTS WHERE USER_NAME = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1,name );
			
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return new Accounts(result.getString("USER_NAME"),result.getString("PASS"),result.getLong("ACCT_ID"),result.getDouble("BALANCE"));
			}else {
				throw new InvalidAccountException("Could not find account with username: "+ name);
			}
			
		} catch (SQLException e) {
			LOGGER.error("could not find account", e);
			
		}
		return null;
	}

	@Override
	public List<Accounts> findAll() {
		LOGGER.setLevel(Level.TRACE);
		LOGGER.trace("entering find all method with paramaters " );
		try(Connection connection = ConnectionUtil.getConnection()){
		
			String sql ="SELECT * FROM BANK_ACCTS ORDER BY ACCT_ID = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet result = statement.executeQuery();
			List<Accounts> accts = new ArrayList<>();
			
			while(result.next()){
				accts.add(new Accounts(result.getString("USER_NAME"),result.getString("PASS"),result.getLong("ACCT_ID"),result.getDouble("BALANCE")));
			}
			return accts;
		} catch (SQLException e) {
			LOGGER.error("could not find account", e);
		}
		return null;
	}
	

}
