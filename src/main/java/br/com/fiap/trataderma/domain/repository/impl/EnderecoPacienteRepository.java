package br.com.fiap.trataderma.domain.repository.impl;

import br.com.fiap.trataderma.domain.entity.Consulta;
import br.com.fiap.trataderma.domain.entity.EnderecoPaciente;
import br.com.fiap.trataderma.domain.repository.Repository;
import br.com.fiap.trataderma.infra.ConnectionFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class EnderecoPacienteRepository implements Repository<EnderecoPaciente, Long> {

    private PacienteRepository pacienteRepository = PacienteRepository.build();

    private ConnectionFactory factory;

    private static final AtomicReference<EnderecoPacienteRepository> instance = new AtomicReference<>();

    private EnderecoPacienteRepository() {
        this.factory = ConnectionFactory.build();
    }

    public static EnderecoPacienteRepository build() {
        instance.compareAndSet( null, new EnderecoPacienteRepository() );
        return instance.get();
    }

    @Override
    public List<EnderecoPaciente> findAll() {
        List<EnderecoPaciente> enderecoPacientes = new ArrayList<>();

        var sql = "SELECT * FROM T_TD_ENDERECO_PACIENTE";

        Connection connection = factory.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    var enderecoPaciente = buildEnderecoPaciente (resultSet);
                    enderecoPacientes.add(enderecoPaciente);
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível realizar a consulta ao banco de dados: " + e.getMessage());
        } finally {
            fecharObjetos(resultSet, statement, connection);
        }

        return enderecoPacientes;
    }

    @Override
    public EnderecoPaciente findById(Long id) {

        EnderecoPaciente enderecoPaciente = null;

        var sql = "SELECT * FROM T_TD_ENDERECO_PACIENTE WHERE ID_ENDERECO = ?";

        Connection connection = factory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    enderecoPaciente = buildEnderecoPaciente(resultSet);
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível realizar a consulta ao banco de dados: " + e.getMessage());
        } finally {
            fecharObjetos(resultSet, preparedStatement, connection);
        }
        return enderecoPaciente;
    }

    @Override
    public EnderecoPaciente persist(EnderecoPaciente enderecoPaciente) {

        var sql = "INSERT INTO T_TD_ENDERECO_PACIENTE (id_endereco, CEP, nr_logradouro, ds_ponto_referencia, id_paciente) values (seq_endereco_paciente.nextval,?,?,?,?)";

        Connection connection = factory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql, new String[]{"id_endereco"});
            preparedStatement.setString(1, enderecoPaciente.getCep());
            preparedStatement.setLong(2, enderecoPaciente.getNumero());
            preparedStatement.setString(3, enderecoPaciente.getPontoReferencia());
            preparedStatement.setLong(4, enderecoPaciente.getPaciente().getId());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                enderecoPaciente.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            System.err.println( "Não foi possível salvar no banco de dados: " + e.getMessage() + "\n" + e.getCause() + "\n" + e.getErrorCode());
        } finally {
            fecharObjetos(resultSet, preparedStatement, connection);
        }

        return enderecoPaciente;
    }

    private EnderecoPaciente buildEnderecoPaciente(ResultSet resultSet) throws SQLException {
        var id = resultSet.getLong("id_endereco");
        var cep = resultSet.getString("CEP");
        var numeroLogradouro = resultSet.getLong("nr_logradouro");
        var pontoReferencia = resultSet.getString("ds_ponto_referencia");
        var idPaciente = resultSet.getLong("id_paciente");

        var paciente = pacienteRepository.findById(idPaciente);


        var enderecoPaciente = new EnderecoPaciente(id, cep, numeroLogradouro, pontoReferencia, paciente);

        return enderecoPaciente;
    }
}
