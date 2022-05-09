package services;

import models.Meta;

import java.util.List;

public interface MetaService {

  void criarMeta(Meta meta);

  void atualizarMeta(Meta meta);

  List<Meta> listarMeta(String email);

  Meta obterMetaById(int id, String email);

  void deletarInvestimento(int id);
}
