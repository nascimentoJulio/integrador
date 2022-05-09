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
import models.Meta;
import models.Usuario;

public class MetaDaoJDBC implements MetaDao {
  private Connection conn;

  public MetaDaoJDBC(Connection conn) {
    this.conn = conn;
  }

  public Meta instanciarMeta(ResultSet rs) throws SQLException {
    Meta obj = new Meta();

    obj.setId(rs.getInt("id"));
    obj.setNome(rs.getString("nome"));
    obj.setDescricao(rs.getString("descricao"));
    obj.setTipo2(rs.getObject("tipo"));
    obj.setValorNecessario(rs.getDouble("valor_necessario"));
    obj.setDataInicio(rs.getDate("data_comeco"));
    obj.setDataConclusao(rs.getDate("data_conclusao"));
    return obj;
  }


  @Override
  public void insert(Meta obj) {
    PreparedStatement st = null;
    try {
      st = conn.prepareStatement(
              "INSERT INTO meta "
                      + "(nome, descricao, tipo, valor_necessario, data_comeco, data_conclusao) "
                      + "VALUES "
                      + "(?, ?, ?, ?, ?, ?, ?)",
              Statement.RETURN_GENERATED_KEYS);

      st.setString(1, obj.getNome());
      st.setString(2, obj.getDescricao());
      st.setObject(3, obj.getTipo());
      st.setDouble(4, obj.getValorNecessario());
      st.setDate(5, obj.getDataInicio());
      st.setDate(6, obj.getDataConclusao());

      int linhasAfetadas = st.executeUpdate();
      if (linhasAfetadas > 0) {
        ResultSet rs = st.getGeneratedKeys();
        if (rs.next()) {
          int id = rs.getInt(1);
          obj.setId(id);
        }
        DB.closeResultSet(rs);
      } else {
        throw new DbException("Erro inesperado! Nenhuma linha afetada!");
      }
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DB.closeStatement(st);
    }
  }

  @Override
  public void update(Meta obj) {
    PreparedStatement st = null;
    try {
      st = conn.prepareStatement(
              "UPDATE meta "
                      + "SET nome = ?, descricao = ?, tipo = ?, valor_necessario = ?, data_comeco = ?, data_conclusao = ?"
                      + "WHERE id = ? AND meta.email_usuario = ?");

      st.setString(1, obj.getNome());
      st.setString(2, obj.getDescricao());
      st.setObject(3, obj.getTipo());
      st.setDouble(4, obj.getValorNecessario());
      st.setDate(5, obj.getDataInicio());
      st.setDate(6, obj.getDataConclusao());
      st.setInt(7, obj.getId());
      st.setString(8, obj.getEmailUsuario());

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
      st = conn.prepareStatement("DELETE FROM meta WHERE id = ?");

      st.setInt(1, id);

      st.executeUpdate();
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DB.closeStatement(st);
    }
  }

  @Override
  public Meta findById(Integer id, String email) {
    PreparedStatement st = null;
    ResultSet rs = null;
    try {
      st = conn.prepareStatement(
              "SELECT meta.*, usuario.nome "
                      + "FROM meta INNER JOIN usuario "
                      + "ON meta.email_usuario = usuario.email "
                      + "WHERE meta.id = ? AND usuario.email = ?");

      st.setInt(1, id);
      st.setString(2, email);
      rs = st.executeQuery();

      if (rs.next()) {
        return instanciarMeta(rs);
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
  public List<Meta> findAll(String email) {
    PreparedStatement st = null;
    ResultSet rs = null;
    try {
      st = conn.prepareStatement(
              "SELECT meta.*,usuario.nome "
                      + "FROM meta INNER JOIN usuario "
                      + "ON meta.email_usuario = usuario.email "
                      + "WHERE usuario.email = ?"
                      + "ORDER BY nome"
      );
      st.setString(1, email );
      rs = st.executeQuery();

      List<Meta> listaDeMetas = new ArrayList<>();

      while (rs.next()) {
        Meta obj = instanciarMeta(rs);
        listaDeMetas.add(obj);
      }
      return listaDeMetas;
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DB.closeStatement(st);
      DB.closeResultSet(rs);
    }
  }
}
