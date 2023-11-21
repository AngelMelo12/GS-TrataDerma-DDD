package br.com.fiap.trataderma.domain.repository.impl;

import br.com.fiap.trataderma.domain.entity.Autentica;
import br.com.fiap.trataderma.domain.repository.Repository;
import br.com.fiap.trataderma.infra.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class AutenticaRepository implements Repository<Autentica, Long> {

    private ConnectionFactory factory;

    private static final AtomicReference<AutenticaRepository> instance = new AtomicReference<>();

    private AutenticaRepository() {
        this.factory = ConnectionFactory.build();
    }
    public static AutenticaRepository build() {
        instance.compareAndSet(null, new AutenticaRepository());
        return instance.get();
    }

    @Override
    public List<Autentica> findAll() {

        List<Autentica> autenticas = new ArrayList<>();

        var sql = "SELECT * FROM T_TD_AUTENTICA";

        Connection connection = factory.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    var autentica = buildAutentica (resultSet);
                    autenticas.add(autentica);
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível realizar a consulta ao banco de dados: " + e.getMessage());
        } finally {
            fecharObjetos(resultSet, statement, connection);
        }
        return autenticas;
    }

    @Override
    public Autentica findById(Long id) {

        Autentica autentica = null;

        var sql = "SELECT * FROM T_TD_AUTENTICA WHERE ID_AUTENTICA = ?";

        Connection connection = factory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    autentica = buildAutentica(resultSet);
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível realizar a consulta ao banco de dados: " + e.getMessage());
        } finally {
            fecharObjetos(resultSet, preparedStatement, connection);
        }
        return autentica;
    }

    @Override
    public Autentica persist(Autentica autentica) {

        var sql = "INSERT INTO T_TD_AUTENTICA (id_autentica, login, senha, st_login) values (seq_autentica.nextval,?,?,?)";

        Connection connection = factory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql, new String[]{"id_autentica"});
            preparedStatement.setString(1, autentica.getLogin());
            preparedStatement.setString(2, autentica.getSenha());
            preparedStatement.setString(3, autentica.getStatus());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                autentica.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            System.err.println( "Não foi possível salvar no banco de dados: " + e.getMessage() + "\n" + e.getCause() + "\n" + e.getErrorCode());
        } finally {
            fecharObjetos(resultSet, preparedStatement, connection);
        }

        return autentica;
    }

    private static Autentica buildAutentica(ResultSet resultSet) throws SQLException {
        var id = resultSet.getLong("id_autentica");
        var login = resultSet.getString("login");
        var senha = resultSet.getString("senha");
        var status = resultSet.getString("st_login");
        var autentica = new Autentica(id, login, senha, status);

        return autentica;
    }
}
