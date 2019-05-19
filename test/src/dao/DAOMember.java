package dao;


import java.util.ArrayList;

import beans.Member;

/**
 * <b>L'interface DAOMember défini les méthodes obligatoires de DAOMemberImpl</b>
 * 
 * @see DAO
 * @see DAOMemberImpl
 * 
 * @author 
 * 
 * @param <Member>
 */
public interface DAOMember extends DAO<Member> {
    void create( Member member ) throws DAOException;
    Member find( String email ) throws DAOException;
	Member find2connect(String email, String password) throws DAOException;
	ArrayList<Member> findAll() throws DAOException;
}
