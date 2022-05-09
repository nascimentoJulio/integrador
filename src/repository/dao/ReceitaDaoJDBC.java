package repository.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import repository.db.DB;
import repository.db.DbException;
import models.Receita;
import models.Usuario;

public class ReceitaDaoJDBC implements ReceitaDao {
  private Connection conn;

  public ReceitaDaoJDBC(Connection conn) {
    this.conn = conn;
  }

  private Receita instanciarReceita(ResultSet rs) throws SQLException {
    Receita obj = new Receita();

    obj.setId(rs.getInt("id"));
    obj.setNome(rs.getString("nome"));
    obj.setDescricao(rs.getString("descricao"));
    obj.setTipoReceita2(rs.getObject("tipo"));
    obj.setValorReceita(rs.getDouble("valor"));
    obj.setDataRecebimento(rs.getDate("data_recebimento"));

    return obj;
  }


  @Override
  public void insert(Receita obj) {
    PreparedStatement st = null;
    try {
      st = conn.prepareStatement(
              "INSERT INTO receita "
                      + "(nome, descricao, tipo, valor, data_recebimento, email_usuario) "
                      + "VALUES "
                      + "(?, ?, ?, ?, ?, ?)",
              Statement.RETURN_GENERATED_KEYS);

      st.setString(1, obj.getNome());

      st.setString(2, obj.getDescricao());

      st.setObject(3, obj.getTipoReceita());

      st.setDouble(4, obj.getValorReceita());

      st.setDate(5, obj.getDataRecebimento());

      st.setString(6, obj.getEmailUsuario());

      int rowsAffected = st.executeUpdate();

      if (rowsAffected > 0) {
        ResultSet rs = st.getGeneratedKeys();
        while (rs.next()) {
          int id = rs.getInt(1);
          obj.setId(id);
        }
        DB.closeResultSet(rs);
      } else {
        throw new DbException("Erro inesperado! Nenhuma rows affected!");
      }
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DB.closeStatement(st);
    }
  }

  @Override
  public void update(Receita obj) {
    PreparedStatement st = null;
    try {
      st = conn.prepareStatement(
              "UPDATE receita "
                      + "SET nome = ?, descricao = ?, tipo = ?, valor = ?, data_recebimento = ?"
                      + "WHERE id = ?");

      st.setString(1, obj.getNome());

      st.setString(2, obj.getDescricao());

      st.setObject(3, obj.getTipoReceita());

      st.setDouble(4, obj.getValorReceita());

      st.setDate(5, obj.getDataRecebimento());

      st.setInt(7, obj.getId());

      st.executeUpdate();
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DB.closeStatement(st);
    }
  }

  @Override
  public void deleteById(Integer id) {
    PreparedStatement st = null;
    try {
      st = conn.prepareStatement("DELETE FROM receita WHERE id = ?");

      st.setInt(1, id);

      st.executeUpdate();
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DB.closeStatement(st);
    }
  }

  @Override
  public Receita findById(Integer id, String email) {
    PreparedStatement st = null;

    ResultSet rs = null;
    try {
      st = conn.prepareStatement(
              "SELECT receita.*"
                      + "FROM receita INNER JOIN usuario "
                      + "ON receita.email_usuario = usuario.email "
                      + "WHERE receita.id = ? AND usuario.email = ?");

      st.setInt(1, id);

      st.setString(2, email);
      rs = st.executeQuery();
      if (rs.next()) {
        return instanciarReceita(rs);
      }
      return null;
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DB.closeStatement(st);
      DB.closeResultSet(rs);
    }
  }

  @Override
  public List<Receita> findAll(String email) {
    PreparedStatement st = null;
    ResultSet rs = null;
    try {
      st = conn.prepareStatement(
              "SELECT receita.*, usuario.nome "
                      + "FROM receita INNER JOIN usuario "
                      + "ON receita.email_usuario = usuario.email "
                      + "WHERE receita.email_usuario = ?"
                      + "ORDER BY nome"
      );

      rs = st.executeQuery();

      List<Receita> listaDeReceitas = new ArrayList<>();


      while (rs.next()) {
        Receita receita = instanciarReceita(rs);
        listaDeReceitas.add(receita);
      }
      return listaDeReceitas;
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DB.closeResultSet(rs);
      DB.closeStatement(st);
    }
  }
}
