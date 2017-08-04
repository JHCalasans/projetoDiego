package br.com.motorapido.dao;

import br.com.minhaLib.dao.CriterioOrdenacao;
import br.com.minhaLib.dao.GenericDAO;
import br.com.motorapido.entity.Perfil;

public interface IPerfilDAO extends GenericDAO<Perfil, Integer> {
	
	static CriterioOrdenacao BY_DSC_ASC = CriterioOrdenacao.asc("descricao");
	

}
