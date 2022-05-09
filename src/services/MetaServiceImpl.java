package services;

import models.Meta;
import models.Receita;
import repository.dao.MetaDao;

import java.util.List;

public class MetaServiceImpl implements MetaService {

  private MetaDao metaDao;

  public MetaServiceImpl(MetaDao metaDao) {
    this.metaDao = metaDao;
  }

  @Override
  public void criarMeta(Meta meta) {
    try {
      if (meta.getNome().isEmpty()
              || meta.getDescricao().isEmpty()
              || meta.getDataInicio() == null
              || meta.getValorNecessario() == 0
              || meta.getDataConclusao() == null
      ) {
        System.out.println("Dados obrigatorios não informados");
      } else {
        this.metaDao.insert(meta);
      }
    } catch (Exception e) {
      System.out.println("Não foi possivel atualizar a receita");
    }
  }

  @Override
  public void atualizarMeta(int id,Meta meta) {
    try {
      if (meta.getNome().isEmpty()
              || meta.getDescricao().isEmpty()
              || meta.getDataInicio() == null
              || meta.getValorNecessario() == 0
              || meta.getDataConclusao() == null
      ) {
        System.out.println("Dados obrigatorios não informados");
      }  else if (this.metaDao.findById(meta.getId(), meta.getEmailUsuario()) == null) {
        System.out.println("investimento não encontrada");
      } else {
        meta.setId(id);
        this.metaDao.update(meta);
      }
    } catch (Exception e) {
      System.out.println("Não foi possivel atualizar o investimento");
    }
  }

  @Override
  public List<Meta> listarMeta(String email) {
    try {
      return this.metaDao.findAll(email);
    } catch (Exception e) {
      System.out.println("Não foi possivel listar as metas");
      return null;
    }
  }

  @Override
  public Meta obterMetaById(int id, String email) {
    try {
      Meta meta = this.metaDao.findById(id, email);
      if (meta == null) {
        System.out.println("meta não encontrada");
        return null;
      }
      return meta;
    } catch (Exception e) {
      System.out.println("Não foi possivel listar a meta");
      return null;
    }
  }

  @Override
  public void deletarMeta(int id) {
    try {
      this.metaDao.deleteById(id);
      System.out.println("Deletado com sucesso");
    } catch (Exception e) {
      System.out.println("Não foi possivel deletar a meta");
    }
  }
}
