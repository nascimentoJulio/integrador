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
import models.Investimento;
import models.Usuario;

public class InvestimentoDaoJDBC implements InvestimentoDao {
  private Connection conn;

  public InvestimentoDaoJDBC(Connection conn) {
    this.conn = conn;
  }

  public Investimento instanciarInvestimento(ResultSet rs) throws SQLException {
    Investimento obj = new Investimento();

    obj.setId(rs.getInt("id"));
    obj.setNome(rs.getString("nome"));
    obj.setDescricao(rs.getString("descricao"));
    obj.setTipoInvestimento2(rs.getObject("tipo_investimento"));
    obj.setValorInvestido(rs.getDouble("valor_investido"));
    obj.setTaxaRendimento(rs.getDouble("taxa_investimento"));
    obj.setDataInvestimento(rs.getDate("data_investimento"));
    obj.setEmailUsuario(rs.getString("email_usuario"));

    return obj;
  }

  @Override
  public void insert(Investimento obj) {
    PreparedStatement st = null;
    try {
      st = conn.prepareStatement(
              "INSERT INTO investimento "
                      + "(nome, descricao, tipo_investimento, valor_investido, taxa_rendimento, data_investimento, email_usuario) "
                      + "VALUES "
                      + "(?, ?, ?, ?, ?, ?, ?",
              Statement.RETURN_GENERATED_KEYS);

      st.setString(1, obj.getNome());

      st.setString(2, obj.getDescricao());

      st.setObject(3, obj.getTipoInvestimento());

      st.setDouble(4, obj.getValorInvestido());

      st.setDouble(5, obj.getTaxaRendimento());

      st.setDate(6, obj.getDataInvestimento());

      st.setString(7, obj.getEmailUsuario());

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
  public void update(Investimento obj) {
    PreparedStatement st = null;
    try {
      st = conn.prepareStatement(
              "UPDATE investimento "
                      + "SET nome = ?, descricao = ?, tipo_investimento = ?, valor_investido = ?, taxa_rendimento = ?, "
                      + "data_investimento = ?, email_usuario = ? WHERE id = ?");

      st.setString(1, obj.getNome());

      st.setString(2, obj.getDescricao());

      st.setObject(3, obj.getTipoInvestimento());

      st.setDouble(4, obj.getValorInvestido());

      st.setDouble(5, obj.getTaxaRendimento());

      st.setDate(6, obj.getDataInvestimento());

      st.setString(7, obj.getEmailUsuario());

      st.setInt(8, obj.getId());

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
      st = conn.prepareStatement("DELETE FROM investimento WHERE id = ?");

      st.setInt(1, id);

      st.executeUpdate();
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DB.closeStatement(st);
    }
  }

  @Override
  public Investimento findById(Integer id, String email) {
    PreparedStatement st = null;
    ResultSet rs = null;
    try {
      st = conn.prepareStatement(
              "SELECT investimento.*, usuario.nome "
                      + "FROM investimento INNER JOIN usuario "
                      + "ON investimento.email_usuario = usuario.email "
                      + "WHERE investimento.id = ? AND usuario.email = ?");

      st.setInt(1, id);
      st.setString(1, email);
      rs = st.executeQuery();

      if (rs.next()) {

        Investimento investimento = instanciarInvestimento(rs);

        return investimento;
      }
      return null;
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DB.closeResultSet(rs);
      DB.closeStatement(st);
    }
  }

  @Override
  public List<Investimento> findAll(String email) {
    PreparedStatement st = null;
    ResultSet rs = null;
    try {
      st = conn.prepareStatement(
              "SELECT investimento.*"
                      + "FROM investimento INNER JOIN usuario "
                      + "ON investimento.email_usuario = usuario.email "
                      + "WHERE usuario.email = ?"
                      + "ORDER BY nome");

      st.setString(1, email);

      rs = st.executeQuery();

      List<Investimento> listaDeInvestimentos = new ArrayList<>();

      while (rs.next()) {
        Investimento obj = instanciarInvestimento(rs);
        listaDeInvestimentos.add(obj);
      }
      return listaDeInvestimentos;
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DB.closeStatement(st);
      DB.closeResultSet(rs);
    }
  }
}
