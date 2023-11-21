package br.com.fiap.trataderma.domain.repository.impl;

import br.com.fiap.trataderma.domain.entity.TelefonePaciente;
import br.com.fiap.trataderma.domain.entity.TelefonePaciente;
import br.com.fiap.trataderma.domain.entity.TelefonePaciente;
import br.com.fiap.trataderma.domain.repository.Repository;
import br.com.fiap.trataderma.infra.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class TelefonePacienteRepository implements Repository<TelefonePaciente, Long> {

    private PacienteRepository pacienteRepository = PacienteRepository.build();

    private ConnectionFactory factory;

    private static final AtomicReference<TelefonePacienteRepository> instance = new AtomicReference<>();

    private TelefonePacienteRepository() {
        this.factory = ConnectionFactory.build();
    }

    public static TelefonePacienteRepository build() {
        instance.compareAndSet( null, new TelefonePacienteRepository() );
        return instance.get();
    }

    @Override
    public List<TelefonePaciente> findAll() {
        List<TelefonePaciente> telefonePacientes = new ArrayList<>();

        var sql = "SELECT * FROM T_TD_TELEFONE_PACIENTE";

        Connection connection = factory.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    var telefonePaciente = buildTelefonePaciente(resultSet);
                    telefonePacientes.add(telefonePaciente);
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível realizar a consulta ao banco de dados: " + e.getMessage());
        } finally {
            fecharObjetos(resultSet, statement, connection);
        }
        return telefonePacientes;
    }

    @Override
    public TelefonePaciente findById(Long id) {
        TelefonePaciente telefonePaciente = null;

        var sql = "SELECT * FROM T_TD_TELEFONE_PACIENTE WHERE id_telefone = ?";

        Connection connection = factory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    telefonePaciente = buildTelefonePaciente(resultSet);
                }
            }
        } catch (SQLException e) {
            System.err.println("Não foi possível realizar a consulta ao banco de dados: " + e.getMessage());
        } finally {
            fecharObjetos(resultSet, preparedStatement, connection);
        }
        return telefonePaciente;
    }

    @Override
    public TelefonePaciente persist(TelefonePaciente telefonePaciente) {
        var sql = "INSERT INTO T_TD_TELEFONE_PACIENTE (id_telefone, nr_ddi, nr_ddd, nr_telefone, tp_telefone, id_paciente) values (seq_telefone_paciente.nextval,?,?,?,?,?)";

        Connection connection = factory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql, new String[]{"id_telefone"});
            preparedStatement.setLong(1, telefonePaciente.getNumeroDdi());
            preparedStatement.setLong(2, telefonePaciente.getNumeroDdd());
            preparedStatement.setLong(3, telefonePaciente.getNumeroTelefone());
            preparedStatement.setString(4, telefonePaciente.getTipoTelefone());
            preparedStatement.setLong(5, telefonePaciente.getPaciente().getId());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                telefonePaciente.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            System.err.println( "Não foi possível salvar no banco de dados: " + e.getMessage() + "\n" + e.getCause() + "\n" + e.getErrorCode());
        } finally {
            fecharObjetos(resultSet, preparedStatement, connection);
        }

        return telefonePaciente;
    }

    private TelefonePaciente buildTelefonePaciente(ResultSet resultSet) throws SQLException {
        var id = resultSet.getLong("id_telefone");
        var numeroDdi = resultSet.getLong("nr_ddi");
        var numeroDdd = resultSet.getLong("nr_ddd");
        var numeroTelefone = resultSet.getLong("nr_telefone");
        var tipoTelefone = resultSet.getString("tp_telefone");
        var idPaciente = resultSet.getLong("id_paciente");
        
        var paciente = pacienteRepository.findById(idPaciente);
        var telefonePaciente = new TelefonePaciente(id, numeroDdi, numeroDdd, numeroTelefone, tipoTelefone, paciente);

        return telefonePaciente;
    }
}
