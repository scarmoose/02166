 package edu.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import edu.shared.OperatoerDTO;

@RemoteServiceRelativePath("loginservice")
public interface IOperatoerService extends RemoteService{
	
	void createOperatoer(OperatoerDTO p)throws Exception;
	void updateOperatoer(OperatoerDTO p)throws Exception;
	void deleteOperatoer(OperatoerDTO p)throws Exception;
	OperatoerDTO loginVerify(int oprId, String pass)throws Exception;
	List<OperatoerDTO> getOperatoerList() throws Exception;
	OperatoerDTO getOperatoer(int oprID) throws Exception;
	
}
