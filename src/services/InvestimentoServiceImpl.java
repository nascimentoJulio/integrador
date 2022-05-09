package services;

import models.Despesa;
import models.Investimento;
import repository.dao.InvestimentoDao;

import java.util.List;

public class InvestimentoServiceImpl implements InvestimentoService {

    private  InvestimentoDao investimentoDao;

    public InvestimentoServiceImpl(InvestimentoDao investimentoDao) {
        this.investimentoDao = investimentoDao;
    }


    @Override
    public void criarInvestimento(Investimento investimento) {
        try {
            if (investimento.getNome().isEmpty()
                    || investimento.getDescricao().isEmpty()
                    || investimento.getValorInvestido() == 0
                    || investimento.getTaxaRendimento() == 0
                    || investimento.getDataInvestimento() == null
            ) {
                System.out.println("Dados obrigatorios não informados");
            }
            investimentoDao.insert(investimento);
            System.out.println("Criado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Não foi possivel criar o investimento!");
        }
    }

    @Override
    public void atualizarInvestimento(Investimento investimento) {
        try {
            if (investimento.getNome().isEmpty()
                    || investimento.getDescricao().isEmpty()
                    || investimento.getValorInvestido() == 0
                    || investimento.getTaxaRendimento() == 0
                    || investimento.getDataInvestimento() == null
            ) {
                System.out.println("Dados obrigatorios não informados");
            } else if (this.investimentoDao.findById(investimento.getId(), investimento.getEmailUsuario()) == null) {
                System.out.println("investimento não encontrada");
            } else {
                this.investimentoDao.update(investimento);
            }
        } catch (Exception e) {
            System.out.println("Não foi possivel atualizar o investimento");
        }
    }

    @Override
    public List<Investimento> listarInvestimento(String email) {
        try {
            return this.investimentoDao.findAll(email);
        } catch (Exception e) {
            System.out.println("Não foi possivel listar os investimentos");
            return null;
        }
    }

    @Override
    public Investimento obterInvestimentoById(int id, String email) {
        try {
            Investimento investimento = this.investimentoDao.findById(id,email);
            if (investimento == null) {
                System.out.println("investimento não encontrada");
                return null;
            }
            return investimento;
        } catch (Exception e) {
            System.out.println("Não foi possivel listar o investimento");
            return null;
        }
    }

    @Override
    public void deletarInvestimento(int id) {
        try {
            this.investimentoDao.deleteById(id);
            System.out.println("Deletado com sucesso");
        } catch (Exception e) {
            System.out.println("Não foi possivel deletar o investimento");
        }
    }
}
