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
    public void CriarDespesa(Despesa despesa) {
        try {
            despesaDao.insert(despesa);
            System.out.println("Criado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Não foi possivel criar a despesa!");
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
    public void DeletarDespesa(int id) {
        try {
            despesaDao.deleteById(id);
        } catch (Exception e) {
            System.out.println("Não foi possivel deletar a despesa");
        }
    }
}
