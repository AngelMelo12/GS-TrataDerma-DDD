package br.com.fiap.trataderma.domain.repository.impl;

import br.com.fiap.trataderma.domain.entity.TelefonePaciente;
import br.com.fiap.trataderma.domain.entity.UnidadeHospitalar;
import br.com.fiap.trataderma.domain.repository.Repository;
import br.com.fiap.trataderma.infra.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class UnidadeHospitalarRepository implements Repository<UnidadeHospitalar, Long> {

    private ConnectionFactory factory;

    private static final AtomicReference<UnidadeHospitalarRepository> instance = new AtomicReference<>();

    private UnidadeHospitalarRepository() {
        this.factory = ConnectionFactory.build();
    }

    public static UnidadeHospitalarRepository build() {
        instance.compareAndSet( null, new UnidadeHospitalarRepository() );
        return instance.get();
    }

    @Override
    public List<UnidadeHospitalar> findAll() {
        List<UnidadeHospitalar> unidadeHospitalars = new ArrayList<>();

        var sql = "SELECT * FROM T_TD_UNID_HOSPITALAR";

        Connection connection = factory.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    var unidadeHospitalar = buildUnidadeHospitalar(resultSet);
                    unidadeHospitalars.add(unidadeHospitalar);
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível realizar a consulta ao banco de dados: " + e.getMessage());
        } finally {
            fecharObjetos(resultSet, statement, connection);
        }
        return unidadeHospitalars;
    }

    @Override
    public UnidadeHospitalar findById(Long id) {

        UnidadeHospitalar unidadeHospitalar = null;

        var sql = "SELECT * FROM T_TD_UNID_HOSPITALAR WHERE id_unid_hospitalar = ?";

        Connection connection = factory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    unidadeHospitalar = buildUnidadeHospitalar(resultSet);
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível realizar a consulta ao banco de dados: " + e.getMessage());
        } finally {
            fecharObjetos(resultSet, preparedStatement, connection);
        }
        return unidadeHospitalar;
    }

    @Override
    public UnidadeHospitalar persist(UnidadeHospitalar unidadeHospitalar) {

        var sql = "INSERT INTO T_TD_UNID_HOSPITALAR(id_unid_hospitalar, razao_social, nr_logradouro, CEP, dt_cadastro) values (seq_unid_hospitalar.nextval,?,?,?,?)";

        Connection connection = factory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql, new String[]{"id_unid_hospitalar"});
            preparedStatement.setString(1, unidadeHospitalar.getRazaoSocial());
            preparedStatement.setLong(2, unidadeHospitalar.getNumero());
            preparedStatement.setLong(3, unidadeHospitalar.getCep());
            preparedStatement.setDate(4, Date.valueOf(unidadeHospitalar.getDataCadastro()));
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                unidadeHospitalar.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            System.err.println( "Não foi possível salvar no banco de dados: " + e.getMessage() + "\n" + e.getCause() + "\n" + e.getErrorCode());
        } finally {
            fecharObjetos(resultSet, preparedStatement, connection);
        }

        return unidadeHospitalar;
    }

    private UnidadeHospitalar buildUnidadeHospitalar(ResultSet resultSet) throws SQLException {
        var id = resultSet.getLong("id_unid_hospitalar");
        var razaoSocial = resultSet.getString("razao_social");
        var numeroLogradouro = resultSet.getLong("nr_logradouro");
        var cep = resultSet.getLong("cep");
        var dataCadastro = resultSet.getDate("dt_cadastro").toLocalDate();

        var unidadeHospitalar = new UnidadeHospitalar(id, razaoSocial, numeroLogradouro, cep, dataCadastro);

        return unidadeHospitalar;
    }
    }

