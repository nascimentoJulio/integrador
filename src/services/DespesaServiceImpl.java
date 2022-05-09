package services;

import models.Despesa;
import repository.dao.DespesaDao;
import repository.dao.UsuarioDao;
import utils.enuns.TipoDespesa;

import java.sql.Date;
import java.util.List;

public class DespesaServiceImpl implements DespesaService {

    private DespesaDao despesaDao;

    public DespesaServiceImpl(DespesaDao despesaDao) {
        this.despesaDao = despesaDao;
    }

    @Override
    public void criarDespesa(Despesa despesa) {
        try {
            if (despesa.getNome().isEmpty()
                    || despesa.getDescricao().isEmpty()
                    || despesa.getDataDespesa() == null
                    || despesa.getValorDespesa() == 0) {
                System.out.println("Dados obrigatorios não iformados");
            }
            despesaDao.insert(despesa);
            System.out.println("Criado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Não foi possivel criar a despesa!");
        }
    }

    @Override
    public void atualizarDespesa(int id, Despesa despesa) {
        try {
            if (despesa.getNome().isEmpty()
                    || despesa.getDescricao().isEmpty()
                    || despesa.getDataDespesa() == null
                    || despesa.getValorDespesa() == 0) {
                System.out.println("Dados obrigatorios não iformados");
            } else if (this.despesaDao.findById(despesa.getId()) == null) {
                System.out.println("despesa não encontrada");
            } else {
                despesa.setId(id);
                this.despesaDao.update(despesa);
            }
        } catch (Exception e) {
            System.out.println("Não foi possivel atualizar a tarefa");
        }
    }

    @Override
    public List<Despesa> listarDespesas() {
        try {
            return despesaDao.findAll();
        } catch (Exception e) {
            System.out.println("Não foi possivel listar as despesas");
            return null;
        }
    }

    @Override
    public Despesa obterDespesaById(int id) {
        try {
            Despesa despesa = despesaDao.findById(id);
            if (despesa == null) {
                System.out.println("Despesa não encontrada");
                return null;
            }
            return despesa;
        } catch (Exception e) {
            System.out.println("Não foi possivel listar a despesa");
            return null;
        }
    }

    @Override
    public void deletarDespesa(int id) {
        try {
            despesaDao.deleteById(id);
            System.out.println("Deletado com sucesso");
        } catch (Exception e) {
            System.out.println("Não foi possivel deletar a despesa");
        }
    }
}
