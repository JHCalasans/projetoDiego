package br.com.motorapido.bo;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.minhaLib.excecao.excecaonegocio.ExcecaoNegocio;
import br.com.motorapido.dao.IFuncionarioDAO;
import br.com.motorapido.dao.IMotoristaDAO;
import br.com.motorapido.dao.IPerfilDAO;
import br.com.motorapido.entity.Funcionario;
import br.com.motorapido.entity.Motorista;
import br.com.motorapido.util.FuncoesUtil;

public class MotoristaBO extends MotoRapidoBO {
	
	private static MotoristaBO instance;

	private MotoristaBO() {

	}

	public static MotoristaBO getInstance() {
		if (instance == null)
			instance = new MotoristaBO();

		return instance;
	}
	
	public List<Motorista> obterMotoristas(String nome, String cpf) throws ExcecaoNegocio {
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			IMotoristaDAO motoristaDAO = fabricaDAO.getPostgresMotoristaDAO();
			List<Motorista> lista = motoristaDAO.obterMotoristas(nome, cpf, em);
			emUtil.commitTransaction(transaction);
			return lista;
		} catch (Exception e) {
			emUtil.rollbackTransaction(transaction);
			throw new ExcecaoNegocio("Falha ao tentar obter motoristas.", e);
		}		finally {
			emUtil.closeEntityManager(em);
		}
	}
	
	public List<Motorista> obterMotoristasExample(Motorista motorista) throws ExcecaoNegocio {
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			IMotoristaDAO motoristaDAO = fabricaDAO.getPostgresMotoristaDAO();
			List<Motorista> lista = motoristaDAO.findByExample(motorista, em);
			emUtil.commitTransaction(transaction);
			return lista;
		} catch (Exception e) {
			emUtil.rollbackTransaction(transaction);
			throw new ExcecaoNegocio("Falha ao tentar obter motoristas.", e);
		}		finally {
			emUtil.closeEntityManager(em);
		}
	}

	
	public Motorista salvarMotorista(Motorista motorista) throws ExcecaoNegocio {
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			IMotoristaDAO motoristaDAO = fabricaDAO.getPostgresMotoristaDAO();
			motorista.setDataCriacao(new Date());
			motorista.setSenha(FuncoesUtil.criptografarSenha(motorista.getSenha()));
			motorista.setAtivo("S");
			motorista.setDisponivel("N");
			motorista = motoristaDAO.save(motorista, em);
		
			emUtil.commitTransaction(transaction);
			return motorista;
		}catch (Exception e) {
			emUtil.rollbackTransaction(transaction);
			throw new ExcecaoNegocio("Falha ao tentar gravar motorista.", e);
		} finally {
			emUtil.closeEntityManager(em);
		}
	}
	
	public Motorista alterarMotorista(Motorista motorista) throws ExcecaoNegocio {
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			IMotoristaDAO motoristaDAO = fabricaDAO.getPostgresMotoristaDAO();
			motorista = motoristaDAO.save(motorista, em);
			emUtil.commitTransaction(transaction);
			return motorista;
		}catch (Exception e) {
			emUtil.rollbackTransaction(transaction);
			throw new ExcecaoNegocio("Falha ao tentar alterar motorista.", e);
		} finally {
			emUtil.closeEntityManager(em);
		}
	}
	
	public Motorista obterMotoristaPorCodigo(Integer codigo) throws ExcecaoNegocio {
		EntityManager em = emUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			IMotoristaDAO motoristaDAO = fabricaDAO.getPostgresMotoristaDAO();
			Motorista motorista = motoristaDAO.findById(codigo, em);
			emUtil.commitTransaction(transaction);
			return motorista;
		} catch (Exception e) {
			emUtil.rollbackTransaction(transaction);
			throw new ExcecaoNegocio("Falha ao tentar obter motorista.", e);
		}finally {
			emUtil.closeEntityManager(em);
		}
	}
	
}
